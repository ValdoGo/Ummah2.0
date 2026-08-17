package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Brightness2
import androidx.compose.material.icons.filled.Brightness5
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.PrayerTimesData
import com.example.data.repository.CityLocation
import com.example.ui.theme.IslamicEmeraldPrimary
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldLight
import com.example.util.AppLanguage
import com.example.util.TranslationHelper

data class PrayerRowItem(
    val id: String,
    val arabicName: String,
    val displayName: String,
    val englishName: String,
    val time: String,
    val isCustom: Boolean,
    val icon: ImageVector,
    val isPrimary: Boolean = true
)

@Composable
fun PrayerTimesScreen(
    city: CityLocation,
    prayerData: PrayerTimesData?,
    isLoading: Boolean,
    customOverrides: Map<String, String> = emptyMap(),
    lang: AppLanguage = AppLanguage.PORTUGUESE,
    onUpdateCustomTime: (String, String) -> Unit = { _, _ -> },
    onTestAdhan: (String) -> Unit = {},
    onRefresh: () -> Unit
) {
    val context = LocalContext.current
    val times = prayerData?.prayer_times
    val nextPrayer = prayerData?.current_status?.next_prayer?.lowercase() ?: "fajr"
    val timeRemaining = prayerData?.current_status?.time_until_next ?: "..."

    var editingPrayer by remember { mutableStateOf<PrayerRowItem?>(null) }
    var editedTimeText by remember { mutableStateOf("") }

    val rawImsak = customOverrides["imsak"] ?: times?.imsak ?: "04:20"
    val rawFajr = customOverrides["fajr"] ?: times?.fajr ?: "04:35"
    val rawSunrise = customOverrides["sunrise"] ?: times?.sunrise ?: "06:00"
    val rawDhuhr = customOverrides["dhuhr"] ?: times?.dhuhr ?: "12:15"
    val rawAsr = customOverrides["asr"] ?: times?.asr ?: "15:30"
    val rawMaghrib = customOverrides["maghrib"] ?: times?.maghrib ?: "18:25"
    val rawIsha = customOverrides["isha"] ?: times?.isha ?: "19:45"

    val prayerList = listOf(
        PrayerRowItem("imsak", "الإمساك", TranslationHelper.translatePrayerName("Imsak", lang), "Imsak", rawImsak, customOverrides.containsKey("imsak"), Icons.Default.Nightlight, false),
        PrayerRowItem("fajr", "الفجر", TranslationHelper.translatePrayerName("Fajr", lang), "Dawn Prayer", rawFajr, customOverrides.containsKey("fajr"), Icons.Default.Brightness2, true),
        PrayerRowItem("sunrise", "الشروq", TranslationHelper.translatePrayerName("Sunrise", lang), "Sunrise", rawSunrise, customOverrides.containsKey("sunrise"), Icons.Default.WbSunny, false),
        PrayerRowItem("dhuhr", "الظهر", TranslationHelper.translatePrayerName("Dhuhr", lang), "Noon Prayer", rawDhuhr, customOverrides.containsKey("dhuhr"), Icons.Default.Brightness5, true),
        PrayerRowItem("asr", "العصر", TranslationHelper.translatePrayerName("Asr", lang), "Afternoon Prayer", rawAsr, customOverrides.containsKey("asr"), Icons.Default.Brightness6, true),
        PrayerRowItem("maghrib", "المغرب", TranslationHelper.translatePrayerName("Maghrib", lang), "Sunset Prayer", rawMaghrib, customOverrides.containsKey("maghrib"), Icons.Default.Brightness2, true),
        PrayerRowItem("isha", "العشاء", TranslationHelper.translatePrayerName("Isha", lang), "Night Prayer", rawIsha, customOverrides.containsKey("isha"), Icons.Default.Nightlight, true)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("prayer_times_screen"),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            // Auto Detected Location Bar (No manual city / method selection buttons as requested)
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(18.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = city.name,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Surface(
                                    color = IslamicEmeraldPrimary.copy(alpha = 0.2f),
                                    shape = RoundedCornerShape(6.dp)
                                ) {
                                    Text(
                                        text = if (lang == AppLanguage.ENGLISH) "Auto" else if (lang == AppLanguage.FRENCH) "Auto" else if (lang == AppLanguage.SPANISH) "Auto" else if (lang == AppLanguage.ARABIC) "تلقائي" else "Automático",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = IslamicEmeraldPrimary,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                            Text(
                                text = "${city.country} • Muslim World League",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    IconButton(
                        onClick = onRefresh,
                        modifier = Modifier.testTag("refresh_prayer_times_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Atualizar",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        // Countdown / Next Prayer Card
        item {
            val localizedNextPrayer = TranslationHelper.translatePrayerName(nextPrayer, lang)
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1E1E1E)
                ),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(1.5.dp, IslamicGold.copy(alpha = 0.5f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (lang == AppLanguage.ENGLISH) "NEXT PRAYER" else if (lang == AppLanguage.FRENCH) "PROCHAINE PRIÈRE" else if (lang == AppLanguage.SPANISH) "PRÓXIMA ORACIÓN" else if (lang == AppLanguage.ARABIC) "الصلاة القادمة" else "PRÓXIMA ORAÇÃO",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = IslamicGoldLight,
                        letterSpacing = 1.5.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = localizedNextPrayer.replaceFirstChar { it.uppercase() },
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = if (lang == AppLanguage.ENGLISH) "Time remaining: $timeRemaining" else if (lang == AppLanguage.FRENCH) "Temps restant : $timeRemaining" else if (lang == AppLanguage.SPANISH) "Tiempo restante: $timeRemaining" else if (lang == AppLanguage.ARABIC) "الوقت المتبقي: $timeRemaining" else "Tempo restante: $timeRemaining",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = IslamicGoldLight
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Método: Muslim World League (Liga Mundial Islâmica)",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF94A3B8),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // Test Adhan Notification & Sound Action Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B).copy(alpha = 0.6f)),
                border = BorderStroke(1.dp, IslamicGold.copy(alpha = 0.35f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(IslamicGold.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.NotificationsActive,
                                contentDescription = "Testar Adhan",
                                tint = IslamicGoldLight,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = when (lang) {
                                    AppLanguage.PORTUGUESE -> "Testar Horário do Adhan"
                                    AppLanguage.ENGLISH -> "Test Adhan Alert & Sound"
                                    AppLanguage.FRENCH -> "Tester l'Adhan & Alerte"
                                    AppLanguage.SPANISH -> "Probar Horario de Adhan"
                                    AppLanguage.ARABIC -> "تجربة تنبيه الأذان الفوري"
                                },
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = when (lang) {
                                    AppLanguage.PORTUGUESE -> "Toca o áudio e envia notificação de alerta imediata"
                                    AppLanguage.ENGLISH -> "Plays Adhan sound and fires instant push alert"
                                    AppLanguage.FRENCH -> "Joue l'Adhan et envoie la notification immédiate"
                                    AppLanguage.SPANISH -> "Reproduce audio y envía notificación de alarma"
                                    AppLanguage.ARABIC -> "تشغيل صوت الأذان وإرسال إشعار التنبيه الفوري"
                                },
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF94A3B8)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            onTestAdhan(nextPrayer)
                            val msg = when (lang) {
                                AppLanguage.PORTUGUESE -> "🔔 Alerta de teste do Adhan acionado! Verifique as notificações e áudio."
                                AppLanguage.ENGLISH -> "🔔 Adhan test alarm triggered! Check notifications and sound."
                                AppLanguage.FRENCH -> "🔔 Test de l'Adhan déclenché ! Vérifiez les notifications et le son."
                                AppLanguage.SPANISH -> "🔔 ¡Alerta de prueba del Adhan activada! Revise notificaciones y audio."
                                AppLanguage.ARABIC -> "🔔 تم تفعيل تجربة الأذان والإشعار بنجاح!"
                            }
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = IslamicGold),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp),
                        modifier = Modifier.testTag("test_adhan_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "Testar",
                            tint = Color(0xFF121212),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = when (lang) {
                                AppLanguage.PORTUGUESE -> "Testar"
                                AppLanguage.ENGLISH -> "Test"
                                AppLanguage.FRENCH -> "Tester"
                                AppLanguage.SPANISH -> "Probar"
                                AppLanguage.ARABIC -> "تجربة"
                            },
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF121212)
                        )
                    }
                }
            }
        }

        // Timeline header
        item {
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (lang == AppLanguage.ENGLISH) "Daily Prayer Schedule" else if (lang == AppLanguage.FRENCH) "Horaires quotidiens de prière" else if (lang == AppLanguage.SPANISH) "Horario Diario de Oraciones" else if (lang == AppLanguage.ARABIC) "جدول الصلوات اليومي" else "Cronograma Diário de Orações",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = if (lang == AppLanguage.ENGLISH) "Tap to edit time" else if (lang == AppLanguage.FRENCH) "Toucher pour modifier" else if (lang == AppLanguage.SPANISH) "Toca para editar" else if (lang == AppLanguage.ARABIC) "اضغط للتعديل" else "Toque p/ editar",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        // List of Prayers with Manual Editing
        items(prayerList) { prayer ->
            val isNext = prayer.id == nextPrayer
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        editingPrayer = prayer
                        editedTimeText = prayer.time
                    }
                    .testTag("prayer_item_${prayer.id}"),
                colors = CardDefaults.cardColors(
                    containerColor = if (isNext) Color(0xFF252015) else MaterialTheme.colorScheme.surface
                ),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(
                    1.5.dp,
                    if (isNext) IslamicGold else MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = if (isNext) 3.dp else 1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isNext) IslamicGold.copy(alpha = 0.2f)
                                    else MaterialTheme.colorScheme.surfaceVariant
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = prayer.icon,
                                contentDescription = null,
                                tint = if (isNext) IslamicGold else MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = prayer.displayName,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = if (isNext) FontWeight.Bold else FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                if (isNext) {
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Surface(
                                        color = IslamicGold,
                                        shape = RoundedCornerShape(6.dp)
                                    ) {
                                        Text(
                                            text = if (lang == AppLanguage.ENGLISH) "NEXT" else if (lang == AppLanguage.FRENCH) "SUIVANTE" else if (lang == AppLanguage.SPANISH) "PRÓXIMA" else if (lang == AppLanguage.ARABIC) "القادمة" else "PRÓXIMA",
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                                if (prayer.isCustom) {
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Surface(
                                        color = IslamicEmeraldPrimary.copy(alpha = 0.2f),
                                        shape = RoundedCornerShape(6.dp)
                                    ) {
                                        Text(
                                            text = if (lang == AppLanguage.ENGLISH) "Edited" else if (lang == AppLanguage.FRENCH) "Modifié" else if (lang == AppLanguage.SPANISH) "Editado" else if (lang == AppLanguage.ARABIC) "معدل" else "Manual",
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = IslamicEmeraldPrimary,
                                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                            }
                            Text(
                                text = prayer.arabicName,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                fontFamily = androidx.compose.ui.text.font.FontFamily.Serif
                            )
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = prayer.time,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isNext) IslamicEmeraldPrimary else MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(
                            onClick = {
                                editingPrayer = prayer
                                editedTimeText = prayer.time
                            },
                            modifier = Modifier.size(32.dp).testTag("edit_btn_${prayer.id}")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Editar Horário",
                                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }

    // Manual Edit Prayer Time Dialog
    if (editingPrayer != null) {
        val prayer = editingPrayer!!
        AlertDialog(
            onDismissRequest = { editingPrayer = null },
            title = {
                Text(
                    text = "${if (lang == AppLanguage.ENGLISH) "Edit Time" else if (lang == AppLanguage.FRENCH) "Modifier l'heure" else if (lang == AppLanguage.SPANISH) "Editar Horario" else if (lang == AppLanguage.ARABIC) "تعديل الوقت" else "Editar Horário"} - ${prayer.displayName}",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column {
                    Text(
                        text = if (lang == AppLanguage.ENGLISH) "Enter custom time (HH:MM in 24h format):" else if (lang == AppLanguage.FRENCH) "Entrez l'heure personnalisée (HH:MM en 24h) :" else if (lang == AppLanguage.SPANISH) "Ingresa el horario personalizado (HH:MM en 24h):" else if (lang == AppLanguage.ARABIC) "أدخل الوقت المخصص (HH:MM بتنسيق 24 ساعة):" else "Insira o horário personalizado (HH:MM formato 24h):",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = editedTimeText,
                        onValueChange = { editedTimeText = it },
                        placeholder = { Text("05:30") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("edit_prayer_time_input")
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val trimmed = editedTimeText.trim()
                        if (trimmed.isNotEmpty()) {
                            onUpdateCustomTime(prayer.id, trimmed)
                        }
                        editingPrayer = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = IslamicGold)
                ) {
                    Text(if (lang == AppLanguage.ENGLISH) "Save" else if (lang == AppLanguage.FRENCH) "Enregistrer" else if (lang == AppLanguage.SPANISH) "Guardar" else if (lang == AppLanguage.ARABIC) "حفظ" else "Salvar", color = Color.White, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { editingPrayer = null }) {
                    Text(if (lang == AppLanguage.ENGLISH) "Cancel" else if (lang == AppLanguage.FRENCH) "Annuler" else if (lang == AppLanguage.SPANISH) "Cancelar" else if (lang == AppLanguage.ARABIC) "إلغاء" else "Cancelar")
                }
            }
        )
    }
}

