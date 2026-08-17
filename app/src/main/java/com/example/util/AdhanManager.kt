package com.example.util

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

data class AdhanReciterOption(
    val id: String,
    val name: String,
    val origin: String,
    val audioUrl: String,
    val localFileName: String
)

object AdhanAudioData {
    val options = listOf(
        AdhanReciterOption("abdul_basit", "Abdul Basit", "Egito / Clássico", "https://praytimes.org/audio/sunni/Abdul-Basit.mp3", "adhan_abdul_basit.mp3"),
        AdhanReciterOption("abdul_ghaffar", "Abdul Ghaffar", "Makkah / Tradicional", "https://praytimes.org/audio/sunni/Abdul-Ghaffar.mp3", "adhan_abdul_ghaffar.mp3"),
        AdhanReciterOption("abdul_hakam", "Abdul Hakam", "Egito / Melódico", "https://praytimes.org/audio/sunni/Abdul-Hakam.mp3", "adhan_abdul_hakam.mp3"),
        AdhanReciterOption("adhan_alaqsa", "Adhan Al-Aqsa", "Jerusalém / Al-Quds", "https://praytimes.org/audio/sunni/Adhan-Alaqsa.mp3", "adhan_alaqsa.mp3"),
        AdhanReciterOption("adhan_egypt", "Adhan Egypt", "Cairo / Al-Azhar", "https://praytimes.org/audio/sunni/Adhan-Egypt.mp3", "adhan_egypt.mp3"),
        AdhanReciterOption("adhan_halab", "Adhan Halab", "Aleppo / Sham", "https://praytimes.org/audio/sunni/Adhan-Halab.mp3", "adhan_halab.mp3"),
        AdhanReciterOption("adhan_madinah", "Adhan Madinah", "Al-Masjid An-Nabawi", "https://praytimes.org/audio/sunni/Adhan-Madinah.mp3", "adhan_madinah.mp3"),
        AdhanReciterOption("adhan_mecca", "Adhan Mecca", "Al-Masjid Al-Haram", "https://praytimes.org/audio/sunni/Adhan-Makkah.mp3", "adhan_mecca.mp3"),
        AdhanReciterOption("al_hussaini", "Al-Hussaini", "Egito / Clássico", "https://praytimes.org/audio/sunni/Al-Hussaini.mp3", "adhan_al_hussaini.mp3"),
        AdhanReciterOption("bakir_bash", "Bakir Bash", "Turquia / Otomano", "https://praytimes.org/audio/sunni/Bakir-Bash.mp3", "adhan_bakir_bash.mp3"),
        AdhanReciterOption("hafez", "Hafez", "Turquia / Istambul", "https://praytimes.org/audio/sunni/Hafez.mp3", "adhan_hafez.mp3"),
        AdhanReciterOption("hafiz_murad", "Hafiz Murad", "Turquia / Melódico", "https://praytimes.org/audio/sunni/Hafiz-Murad.mp3", "adhan_hafiz_murad.mp3"),
        AdhanReciterOption("minshawi", "Minshawi", "Muhammad Siddiq Al-Minshawi", "https://praytimes.org/audio/sunni/Menshawi.mp3", "adhan_minshawi.mp3"),
        AdhanReciterOption("naghshbandi", "Naghshbandi", "Sayyid Naqshbandi", "https://praytimes.org/audio/sunni/Naghshbandi.mp3", "adhan_naghshbandi.mp3"),
        AdhanReciterOption("saber", "Saber", "Mustafa Saber", "https://praytimes.org/audio/sunni/Saber.mp3", "adhan_saber.mp3"),
        AdhanReciterOption("sharif_doman", "Sharif Doman", "Damasco / Sham", "https://praytimes.org/audio/sunni/Sharif-Doman.mp3", "adhan_sharif_doman.mp3"),
        AdhanReciterOption("yusuf_islam", "Yusuf Islam", "Cat Stevens / UK", "https://praytimes.org/audio/sunni/Yusuf-Islam.mp3", "adhan_yusuf_islam.mp3")
    )

    fun getOptionById(id: String): AdhanReciterOption {
        return options.firstOrNull { it.id.equals(id, ignoreCase = true) || it.name.equals(id, ignoreCase = true) }
            ?: options[7] // Default to Mecca
    }
}

object AdhanOfflineManager {
    private const val TAG = "AdhanOfflineManager"

    fun getAdhanFile(context: Context, option: AdhanReciterOption): File {
        val dir = File(context.filesDir, "adhans")
        if (!dir.exists()) dir.mkdirs()
        return File(dir, option.localFileName)
    }

    fun isAdhanCached(context: Context, option: AdhanReciterOption): Boolean {
        val file = getAdhanFile(context, option)
        return file.exists() && file.length() > 5000
    }

    suspend fun downloadAdhan(context: Context, option: AdhanReciterOption): Boolean = withContext(Dispatchers.IO) {
        try {
            val destFile = getAdhanFile(context, option)
            if (destFile.exists() && destFile.length() > 5000) {
                return@withContext true
            }
            val tempFile = File(destFile.parentFile, "${destFile.name}.tmp")
            val url = URL(option.audioUrl)
            val connection = url.openConnection()
            connection.connectTimeout = 15000
            connection.readTimeout = 25000
            connection.getInputStream().use { input ->
                FileOutputStream(tempFile).use { output ->
                    input.copyTo(output)
                }
            }
            if (tempFile.length() > 5000) {
                if (destFile.exists()) destFile.delete()
                tempFile.renameTo(destFile)
                Log.d(TAG, "Adhan downloaded successfully: ${option.name}")
                true
            } else {
                tempFile.delete()
                false
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to download adhan: ${option.name}", e)
            false
        }
    }

    fun prefetchSelectedAndAllAdhans(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val prefReciter = context.getSharedPreferences("ummah_prefs", Context.MODE_PRIVATE)
                .getString("adhan_sound", "adhan_mecca") ?: "adhan_mecca"
            val selectedOption = AdhanAudioData.getOptionById(prefReciter)
            downloadAdhan(context, selectedOption)

            // Download remaining adhans in background so all 17 become available offline
            for (option in AdhanAudioData.options) {
                if (option.id != selectedOption.id && !isAdhanCached(context, option)) {
                    downloadAdhan(context, option)
                }
            }
        }
    }
}

object AdhanAlarmScheduler {
    private const val TAG = "AdhanAlarmScheduler"
    const val CHANNEL_ID = "prayer_alarm_channel"
    private const val PREFS_NAME = "ummah_prefs"

    fun scheduleAlarms(
        context: Context,
        prayerTimes: Map<String, String>, // e.g. "fajr" -> "05:15", "dhuhr" -> "12:30", etc.
        advanceMinutes: Int = 0,
        selectedAdhanId: String = "adhan_mecca",
        isAdhanEnabled: Boolean = true
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit()
            .putBoolean("adhan_enabled", isAdhanEnabled)
            .putInt("adhan_advance_min", advanceMinutes)
            .putString("adhan_sound", selectedAdhanId)
            .apply()

        if (!isAdhanEnabled) {
            cancelAllAlarms(context)
            return
        }

        // Make sure notification channel is created
        createNotificationChannel(context)

        val prayersToSchedule = listOf("fajr", "sunrise", "dhuhr", "asr", "maghrib", "isha")
        val now = Calendar.getInstance()

        prayersToSchedule.forEachIndexed { index, prayerKey ->
            val timeStr = prayerTimes[prayerKey]
            if (!timeStr.isNullOrBlank()) {
                val alarmCalendar = parseTimeToCalendar(timeStr, advanceMinutes)
                
                // If today's time has already passed, schedule for tomorrow
                if (alarmCalendar.before(now)) {
                    alarmCalendar.add(Calendar.DAY_OF_YEAR, 1)
                }

                val intent = Intent(context, AdhanAlarmReceiver::class.java).apply {
                    action = "com.example.ACTION_PRAYER_ALARM"
                    putExtra("prayer_key", prayerKey)
                    putExtra("prayer_time", timeStr)
                    putExtra("advance_minutes", advanceMinutes)
                    putExtra("adhan_sound", selectedAdhanId)
                }

                val requestCode = 1000 + index
                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    requestCode,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        alarmManager.setExactAndAllowWhileIdle(
                            AlarmManager.RTC_WAKEUP,
                            alarmCalendar.timeInMillis,
                            pendingIntent
                        )
                    } else {
                        alarmManager.setExact(
                            AlarmManager.RTC_WAKEUP,
                            alarmCalendar.timeInMillis,
                            pendingIntent
                        )
                    }
                    Log.d(TAG, "Scheduled alarm for $prayerKey at ${alarmCalendar.time}")
                } catch (e: SecurityException) {
                    Log.e(TAG, "Permission error scheduling exact alarm", e)
                }
            }
        }
    }

    fun cancelAllAlarms(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
        for (i in 0..6) {
            val intent = Intent(context, AdhanAlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                1000 + i,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            alarmManager.cancel(pendingIntent)
        }
    }

    private fun parseTimeToCalendar(timeStr: String, advanceMinutes: Int): Calendar {
        val calendar = Calendar.getInstance()
        val parts = timeStr.trim().split(":", " ")
        if (parts.size >= 2) {
            val hour = parts[0].toIntOrNull() ?: 12
            val min = parts[1].toIntOrNull() ?: 0
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, min)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            if (advanceMinutes > 0) {
                calendar.add(Calendar.MINUTE, -advanceMinutes)
            }
        }
        return calendar
    }

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Horários de Salah e Adhan",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notificações e chamados à oração islâmica (Adhan)"
                enableVibration(true)
                vibrationPattern = longArrayOf(0, 500, 200, 500, 200, 500)
                setSound(null, null) // Sound is played directly by MediaPlayer
                setShowBadge(true)
                lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun triggerTestAdhanNow(
        context: Context,
        prayerKey: String = "dhuhr",
        prayerTime: String = "12:30",
        soundId: String = "adhan_mecca"
    ) {
        try {
            val intent = Intent(context, AdhanAlarmReceiver::class.java).apply {
                action = "com.example.ACTION_PRAYER_ALARM"
                putExtra("prayer_key", prayerKey)
                putExtra("prayer_time", prayerTime)
                putExtra("advance_minutes", 0)
                putExtra("adhan_sound", soundId)
            }
            val receiver = AdhanAlarmReceiver()
            receiver.onReceive(context, intent)
        } catch (e: Exception) {
            Log.e("AdhanAlarmScheduler", "Error triggering test adhan", e)
        }
    }
}

class AdhanAlarmReceiver : BroadcastReceiver() {
    companion object {
        private const val TAG = "AdhanAlarmReceiver"
        var activeMediaPlayer: MediaPlayer? = null
    }

    override fun onReceive(context: Context, intent: Intent?) {
        var wakeLock: PowerManager.WakeLock? = null
        try {
            val powerManager = context.getSystemService(Context.POWER_SERVICE) as? PowerManager
            wakeLock = powerManager?.newWakeLock(
                PowerManager.PARTIAL_WAKE_LOCK,
                "Ummah:AdhanAlarmWakeLock"
            )?.apply {
                setReferenceCounted(false)
                try {
                    acquire(180000L) // 3 minutes max
                } catch (_: Exception) {}
            }
        } catch (e: Exception) {
            Log.w(TAG, "WakeLock acquisition failed", e)
        }

        try {
            val prayerKey = intent?.getStringExtra("prayer_key") ?: "salah"
            val prayerTime = intent?.getStringExtra("prayer_time") ?: ""
            val advanceMinutes = intent?.getIntExtra("advance_minutes", 0) ?: 0
            val adhanSound = intent?.getStringExtra("adhan_sound") ?: "adhan_mecca"

            val prefs = context.getSharedPreferences("ummah_prefs", Context.MODE_PRIVATE)
            val langPref = prefs.getString("app_language", null) ?: prefs.getString("selected_language", "PORTUGUESE") ?: "PORTUGUESE"
            val lang = try {
                AppLanguage.valueOf(langPref)
            } catch (_: Exception) {
                AppLanguage.fromCode(langPref)
            }
            val translatedPrayer = TranslationHelper.translatePrayerName(prayerKey, lang)

            val title = if (advanceMinutes > 0) {
                when (lang) {
                    AppLanguage.PORTUGUESE -> "Faltam $advanceMinutes min para $translatedPrayer"
                    AppLanguage.ENGLISH -> "$advanceMinutes min left until $translatedPrayer"
                    AppLanguage.FRENCH -> "Plus que $advanceMinutes min avant $translatedPrayer"
                    AppLanguage.ARABIC -> "بقي $advanceMinutes دقائق على صلاة $translatedPrayer"
                    AppLanguage.SPANISH -> "Faltan $advanceMinutes min para $translatedPrayer"
                }
            } else {
                when (lang) {
                    AppLanguage.PORTUGUESE -> "Está na hora da oração de $translatedPrayer"
                    AppLanguage.ENGLISH -> "It is time for $translatedPrayer Prayer"
                    AppLanguage.FRENCH -> "C'est l'heure de la prière de $translatedPrayer"
                    AppLanguage.ARABIC -> "حان الآن موعد صلاة $translatedPrayer"
                    AppLanguage.SPANISH -> "Es hora de la oración de $translatedPrayer"
                }
            }

            val body = when (lang) {
                AppLanguage.PORTUGUESE -> "Horário: $prayerTime • \"A oração é prescrita aos crentes em horários determinados.\""
                AppLanguage.ENGLISH -> "Time: $prayerTime • \"Indeed, prayer has been decreed upon believers a prescribed time.\""
                AppLanguage.FRENCH -> "Heure: $prayerTime • \"La prière demeure pour les croyants une prescription à des temps déterminés.\""
                AppLanguage.ARABIC -> "الوقت: $prayerTime • «إِنَّ الصَّلَاةَ كَانَتْ عَلَى الْمُؤْمِنِينَ كِتَابًا مَوْقُوتًا»"
                AppLanguage.SPANISH -> "Hora: $prayerTime • \"La oración ha sido prescrita a los creyentes en horarios determinados.\""
            }

            // Show High Priority Push Notification
            try {
                AdhanAlarmScheduler.createNotificationChannel(context)
                val notificationIntent = Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                val pendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                val stopIntent = Intent(context, AdhanStopReceiver::class.java)
                val stopPendingIntent = PendingIntent.getBroadcast(
                    context,
                    1,
                    stopIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                val notification = NotificationCompat.Builder(context, AdhanAlarmScheduler.CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setStyle(NotificationCompat.BigTextStyle().bigText(body))
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setCategory(NotificationCompat.CATEGORY_ALARM)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .addAction(android.R.drawable.ic_media_pause, "Parar Adhan", stopPendingIntent)
                    .setVibrate(longArrayOf(0, 500, 200, 500, 200, 500))
                    .build()

                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
                notificationManager?.notify(786, notification)
            } catch (ne: Exception) {
                Log.w(TAG, "Could not post adhan notification", ne)
            }

            // Play Adhan Audio safely (from offline local cache if available, or streaming fallback)
            playAdhanSound(context, adhanSound)

        } catch (e: Exception) {
            Log.e(TAG, "Error processing prayer alarm", e)
        } finally {
            try {
                if (wakeLock?.isHeld == true) {
                    wakeLock.release()
                }
            } catch (_: Exception) {}
        }
    }

    private fun playAdhanSound(context: Context, adhanSoundId: String) {
        try {
            try {
                activeMediaPlayer?.stop()
            } catch (_: Exception) {}
            try {
                activeMediaPlayer?.release()
            } catch (_: Exception) {}
            activeMediaPlayer = null

            val option = AdhanAudioData.getOptionById(adhanSoundId)
            val localFile = AdhanOfflineManager.getAdhanFile(context, option)

            val mp = MediaPlayer()
            mp.setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
            )

            mp.setOnErrorListener { player, what, extra ->
                Log.e(TAG, "MediaPlayer error occurred: what=$what, extra=$extra")
                try {
                    player.reset()
                    player.release()
                } catch (_: Exception) {}
                if (activeMediaPlayer == player) {
                    activeMediaPlayer = null
                }
                // Fallback to system ringtone if adhan playback fails offline
                try {
                    val alertUri = android.media.RingtoneManager.getDefaultUri(android.media.RingtoneManager.TYPE_ALARM)
                        ?: android.media.RingtoneManager.getDefaultUri(android.media.RingtoneManager.TYPE_NOTIFICATION)
                    val r = android.media.RingtoneManager.getRingtone(context, alertUri)
                    r?.play()
                } catch (_: Exception) {}
                true
            }

            if (localFile.exists() && localFile.length() > 5000) {
                mp.setDataSource(localFile.absolutePath)
            } else {
                try {
                    mp.setDataSource(context, Uri.parse(option.audioUrl))
                    // Also trigger background download for next time
                    CoroutineScope(Dispatchers.IO).launch {
                        AdhanOfflineManager.downloadAdhan(context, option)
                    }
                } catch (e: Exception) {
                    val alertUri = android.media.RingtoneManager.getDefaultUri(android.media.RingtoneManager.TYPE_ALARM)
                        ?: android.media.RingtoneManager.getDefaultUri(android.media.RingtoneManager.TYPE_NOTIFICATION)
                    val r = android.media.RingtoneManager.getRingtone(context, alertUri)
                    r?.play()
                    return
                }
            }

            mp.setOnPreparedListener { player ->
                try {
                    player.start()
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to start media player", e)
                }
            }
            mp.setOnCompletionListener { player ->
                try {
                    player.release()
                } catch (_: Exception) {}
                if (activeMediaPlayer == player) {
                    activeMediaPlayer = null
                }
            }
            mp.prepareAsync()
            activeMediaPlayer = mp
        } catch (e: Exception) {
            Log.e(TAG, "Failed to play Adhan audio", e)
            try {
                val alertUri = android.media.RingtoneManager.getDefaultUri(android.media.RingtoneManager.TYPE_ALARM)
                    ?: android.media.RingtoneManager.getDefaultUri(android.media.RingtoneManager.TYPE_NOTIFICATION)
                val r = android.media.RingtoneManager.getRingtone(context, alertUri)
                r?.play()
            } catch (_: Exception) {}
        }
    }
}

class AdhanStopReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        try {
            AdhanAlarmReceiver.activeMediaPlayer?.stop()
            AdhanAlarmReceiver.activeMediaPlayer?.release()
            AdhanAlarmReceiver.activeMediaPlayer = null

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.cancel(786)
        } catch (_: Exception) {}
    }
}

class AdhanBootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED || intent?.action == "android.intent.action.QUICKBOOT_POWERON") {
            val prefs = context.getSharedPreferences("ummah_prefs", Context.MODE_PRIVATE)
            val isEnabled = prefs.getBoolean("adhan_enabled", true)
            if (isEnabled) {
                val advanceMin = prefs.getInt("adhan_advance_min", 0)
                val sound = prefs.getString("adhan_sound", "adhan_mecca") ?: "adhan_mecca"
                // Reschedule with saved or default prayer times
                val defaultTimes = mapOf(
                    "fajr" to (prefs.getString("fajr_time", "05:00") ?: "05:00"),
                    "sunrise" to (prefs.getString("sunrise_time", "06:20") ?: "06:20"),
                    "dhuhr" to (prefs.getString("dhuhr_time", "12:15") ?: "12:15"),
                    "asr" to (prefs.getString("asr_time", "15:30") ?: "15:30"),
                    "maghrib" to (prefs.getString("maghrib_time", "18:25") ?: "18:25"),
                    "isha" to (prefs.getString("isha_time", "19:45") ?: "19:45")
                )
                AdhanAlarmScheduler.scheduleAlarms(context, defaultTimes, advanceMin, sound, true)
            }
        }
    }
}
