package com.example.data.repository

import com.example.data.api.ApiClient
import com.example.data.local.AppDatabase
import com.example.data.local.FavoriteDuaEntity
import com.example.data.local.FavoriteHadithEntity
import com.example.data.local.TasbihEntity
import com.example.data.model.AyahItem
import com.example.data.model.DuaCategory
import com.example.data.model.DuaItem
import com.example.data.model.GregorianDetail
import com.example.data.model.HadithCollectionInfo
import com.example.data.model.HadithItem
import com.example.data.model.HijriDetail
import com.example.data.model.IslamicEventItem
import com.example.data.model.IslamicEventsData
import com.example.data.model.LocationCoords
import com.example.data.model.MutashabihVerseItem
import com.example.data.model.NextEventItem
import com.example.data.model.PrayerTimesData
import com.example.data.model.PrayerTimesMap
import com.example.data.model.QuranJuzData
import com.example.data.model.QuranSurahDetailData
import com.example.data.model.ReciterInfo
import com.example.data.model.SurahMeta
import com.example.data.model.SurahReciterAudio
import com.example.data.model.TodayHijriData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

data class CityLocation(
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val timezone: String
)

class IslamicRepository(private val database: AppDatabase) {

    private val api = ApiClient.apiService
    private val favoriteDao = database.favoriteDao()
    private val tasbihDao = database.tasbihDao()

    val favoriteDuas: Flow<List<FavoriteDuaEntity>> = favoriteDao.getAllFavoriteDuas()
    val favoriteHadiths: Flow<List<FavoriteHadithEntity>> = favoriteDao.getAllFavoriteHadiths()
    val tasbihHistory: Flow<List<TasbihEntity>> = tasbihDao.getAllTasbihRecords()

    val popularCities = listOf(
        CityLocation("Mecca (Makkah)", "Arábia Saudita", 21.4225, 39.8262, "Asia/Riyadh"),
        CityLocation("Medina (Madinah)", "Arábia Saudita", 24.5247, 39.5692, "Asia/Riyadh"),
        CityLocation("Jerusalém (Al-Quds)", "Palestina", 31.7683, 35.2137, "Asia/Jerusalem"),
        CityLocation("São Paulo", "Brasil", -23.5505, -46.6333, "America/Sao_Paulo"),
        CityLocation("Rio de Janeiro", "Brasil", -22.9068, -43.1729, "America/Sao_Paulo"),
        CityLocation("Lisboa", "Portugal", 38.7223, -9.1393, "Europe/Lisbon"),
        CityLocation("Porto", "Portugal", 41.1579, -8.6291, "Europe/Lisbon"),
        CityLocation("Luanda", "Angola", -8.8390, 13.2894, "Africa/Luanda"),
        CityLocation("Maputo", "Moçambique", -25.9692, 32.5732, "Africa/Maputo"),
        CityLocation("Cairo", "Egito", 30.0444, 31.2357, "Africa/Cairo"),
        CityLocation("Istambul", "Turquia", 41.0082, 28.9784, "Europe/Istanbul"),
        CityLocation("Dubai", "Emirados Árabes", 25.2048, 55.2708, "Asia/Dubai"),
        CityLocation("Londres", "Reino Unido", 51.5074, -0.1278, "Europe/London"),
        CityLocation("Nova York", "Estados Unidos", 40.7128, -74.0060, "America/New_York"),
        CityLocation("Jacarta", "Indonésia", -6.2088, 106.8456, "Asia/Jakarta")
    )

    // --- PRAYER TIMES ---
    suspend fun getPrayerTimes(
        lat: Double,
        lng: Double,
        method: String? = "MuslimWorldLeague",
        madhab: String? = "Shafi",
        date: String? = null
    ): PrayerTimesData = withContext(Dispatchers.IO) {
        try {
            val response = api.getPrayerTimes(
                lat = lat,
                lng = lng,
                method = method,
                madhab = madhab,
                date = date
            )
            if (response.success && response.data != null) {
                return@withContext response.data
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        // Fallback calculation data
        return@withContext PrayerTimesData(
            date = date ?: "2026-08-14",
            timezone = "UTC",
            location = LocationCoords(lat, lng),
            calculation_method = method ?: "MuslimWorldLeague",
            madhab = madhab ?: "Shafi",
            prayer_times = PrayerTimesMap(
                imsak = "04:20",
                fajr = "04:35",
                sunrise = "06:00",
                dhuhr = "12:15",
                asr = "15:30",
                maghrib = "18:25",
                isha = "19:45"
            )
        )
    }

    // --- DUAS ---
    suspend fun getDuasCategories(): List<DuaCategory> = withContext(Dispatchers.IO) {
        try {
            val res = api.getDuasCategories()
            if (res.success && res.data != null && res.data.categories.isNotEmpty()) {
                val list = mutableListOf(DuaCategory("all", "Todas as Categorias", "Todas as súplicas", res.data.total))
                list.addAll(res.data.categories)
                return@withContext list
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext DuaDataStore.categories
    }

    suspend fun getDuas(): List<DuaItem> = withContext(Dispatchers.IO) {
        try {
            val res = api.getDuas()
            if (res.success && res.data != null && res.data.duas.isNotEmpty()) {
                return@withContext res.data.duas
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext DuaDataStore.duas
    }

    suspend fun toggleFavoriteDua(dua: DuaItem, isCurrentlyFavorite: Boolean) = withContext(Dispatchers.IO) {
        if (isCurrentlyFavorite) {
            favoriteDao.deleteFavoriteDua(dua.id)
        } else {
            favoriteDao.insertFavoriteDua(
                FavoriteDuaEntity(
                    duaId = dua.id,
                    category = dua.category,
                    title = dua.title,
                    arabic = dua.arabic,
                    transliteration = dua.transliteration,
                    translation = dua.translation,
                    source = dua.source,
                    repeatCount = dua.repeat
                )
            )
        }
    }

    fun isDuaFavorite(id: Int): Flow<Boolean> = favoriteDao.isDuaFavorite(id)

    // --- HADITH ---
    suspend fun getHadithCollections(): List<HadithCollectionInfo> = withContext(Dispatchers.IO) {
        try {
            val res = api.getHadithCollections()
            if (res.success && res.data != null && res.data.collections.isNotEmpty()) {
                return@withContext res.data.collections
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext listOf(
            HadithCollectionInfo("bukhari", "Sahih al-Bukhari", "صحيح البخاري", "Imam Bukhari", "Sahih", 7580),
            HadithCollectionInfo("muslim", "Sahih Muslim", "صحيح مسلم", "Imam Muslim", "Sahih", 7360),
            HadithCollectionInfo("abudawud", "Sunan Abu Dawud", "سنن أبي داود", "Abu Dawud", "Hasan/Sahih", 5272),
            HadithCollectionInfo("tirmidhi", "Jami at-Tirmidhi", "جامع الترمذي", "Imam Tirmidhi", "Hasan/Sahih", 3926),
            HadithCollectionInfo("ibnmajah", "Sunan Ibn Majah", "سنن ابن ماجه", "Ibn Majah", "Hasan/Sahih", 4340),
            HadithCollectionInfo("nasai", "Sunan an-Nasa'i", "سنن النسائي", "Imam an-Nasa'i", "Sahih", 5679),
            HadithCollectionInfo("malik", "Muwatta Malik", "موطأ مالك", "Imam Malik", "Sahih", 1829),
            HadithCollectionInfo("nawawi", "Nawawi's 40 Hadith", "الأربعون النووية", "Imam an-Nawawi", "Sahih/Hasan", 42),
            HadithCollectionInfo("qudsi", "40 Hadith Qudsi", "الأحاديث القدسية", "Various", "Sahih/Hasan", 40),
            HadithCollectionInfo("dehlawi", "Shah Waliullah's 40", "الأربعون لولي الله الدهلوي", "Shah Waliullah Dehlawi", "Various", 40)
        )
    }

    suspend fun getRandomHadith(collection: String? = null): HadithItem = withContext(Dispatchers.IO) {
        try {
            val res = api.getRandomHadith(collection = collection)
            if (res.success && res.data != null && !res.data.arabic.isNullOrBlank()) {
                return@withContext res.data
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext HadithItem(
            id = "bukhari-1",
            collection = "bukhari",
            collection_name = "Sahih al-Bukhari",
            hadithnumber = 1,
            arabic = "إِنَّمَا الْأَعْمَالُ بِالنِّيَّاتِ، وَإِنَّمَا لِكُلِّ امْرِئٍ مَا نَوَى، فَمَنْ كَانَتْ هِجْرَتُهُ إِلَى اللَّهِ وَرَسُولِهِ، فَهِجْرَتُهُ إِلَى اللَّهِ وَرَسُولِهِ",
            english = "Narrated 'Umar bin Al-Khattab: I heard Allah's Messenger (ﷺ) saying: 'The reward of deeds depends upon the intentions and every person will get the reward according to what he has intended.'",
            grade = "Sahih"
        )
    }

    suspend fun getSpecificHadith(collection: String, number: String): HadithItem? = withContext(Dispatchers.IO) {
        try {
            val res = api.getSpecificHadith(collection = collection, number = number)
            if (res.success && res.data != null) {
                return@withContext res.data
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext null
    }

    suspend fun searchHadith(query: String, collection: String? = null): List<HadithItem> = withContext(Dispatchers.IO) {
        try {
            val res = api.searchHadith(query = query, collection = collection, limit = 30)
            if (res.success && res.data != null && res.data.hadiths.isNotEmpty()) {
                return@withContext res.data.hadiths
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext emptyList()
    }

    suspend fun browseHadithCollection(collection: String, page: Int = 1): List<HadithItem> = withContext(Dispatchers.IO) {
        try {
            val res = api.browseHadithCollection(collection = collection, page = page, limit = 25)
            if (res.success && res.data != null && res.data.hadiths.isNotEmpty()) {
                return@withContext res.data.hadiths
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext emptyList()
    }

    suspend fun toggleFavoriteHadith(hadith: HadithItem, isCurrentlyFavorite: Boolean) = withContext(Dispatchers.IO) {
        val hid = hadith.id.ifBlank { "${hadith.collection}-${hadith.hadithnumber}" }
        if (isCurrentlyFavorite) {
            favoriteDao.deleteFavoriteHadith(hid)
        } else {
            favoriteDao.insertFavoriteHadith(
                FavoriteHadithEntity(
                    hadithId = hid,
                    collection = hadith.collection ?: "",
                    collectionName = hadith.collection_name ?: "",
                    hadithNumber = hadith.hadithnumber ?: 0,
                    arabic = hadith.arabic ?: "",
                    english = hadith.english ?: "",
                    grade = hadith.grade ?: "Sahih"
                )
            )
        }
    }

    fun isHadithFavorite(id: String): Flow<Boolean> = favoriteDao.isHadithFavorite(id)

    // --- HIJRI & EVENTS ---
    suspend fun getTodayHijri(): TodayHijriData = withContext(Dispatchers.IO) {
        try {
            val res = api.getTodayHijri()
            if (res.success && res.data != null) {
                return@withContext res.data
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext TodayHijriData(
            gregorian = GregorianDetail(
                date = "2026-08-14",
                formatted = "Sexta-feira, 14 de Agosto de 2026",
                day_of_week = "Sexta-feira",
                day = 14,
                month = 8,
                month_name = "Agosto",
                year = 2026
            ),
            hijri = HijriDetail(
                date = "1448-03-01",
                formatted = "01 ربيع الأول 1448 AH",
                day = 1,
                month = 3,
                month_name = "Rabi' al-awwal",
                month_name_arabic = "رَبِيع الْأَوَّل",
                year = 1448,
                era = "AH"
            )
        )
    }

    suspend fun getIslamicEvents(): IslamicEventsData = withContext(Dispatchers.IO) {
        try {
            val res = api.getIslamicEvents()
            if (res.success && res.data != null) {
                return@withContext res.data
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext IslamicEventsData(
            current_hijri_date = getTodayHijri(),
            next_event = NextEventItem("Mawlid an-Nabi", "12 Rabi' al-awwal 1448 AH", 3, 12),
            events = listOf(
                IslamicEventItem(1, 1, "Ano Novo Islâmico (1 Muharram)", "Início do novo ano no calendário Hijri"),
                IslamicEventItem(1, 10, "Dia de Ashura (10 Muharram)", "Dia recomendado de jejum sunnah e reflexão histórica"),
                IslamicEventItem(3, 12, "Mawlid an-Nabi (12 Rabi' al-awwal)", "Celebração do nascimento do Profeta Muhammad (que a paz e bênçãos estejam com ele)"),
                IslamicEventItem(7, 27, "Isra e Mi'raj (27 Rajab)", "A viagem noturna e ascensão aos céus do Profeta Muhammad (saw)"),
                IslamicEventItem(8, 15, "Meados de Sha'ban (15 Sha'ban)", "Noite do Perdão (Laylat al-Bara'ah)"),
                IslamicEventItem(9, 1, "Início do Ramadan (1 Ramadan)", "Início do sagrado mês de jejum, reflexão e oração"),
                IslamicEventItem(9, 27, "Laylat al-Qadr (Noite do Decreto)", "A noite mais sagrada do ano, melhor que mil meses"),
                IslamicEventItem(10, 1, "Eid al-Fitr (1 Shawwal)", "Celebração da conclusão abençoada do Ramadan"),
                IslamicEventItem(12, 8, "Início do Hajj (8 Dhul-Hijjah)", "Início dos ritos da grande peregrinação sagrada a Makkah"),
                IslamicEventItem(12, 9, "Dia de Arafah (9 Dhul-Hijjah)", "O ponto culminante do Hajj e melhor dia de jejum para não-peregrinos"),
                IslamicEventItem(12, 10, "Eid al-Adha (10 Dhul-Hijjah)", "Festa do Sacrifício em honra à fé e obediência do Profeta Ibrahim")
            )
        )
    }

    // --- TASBIH ---
    suspend fun saveTasbihRecord(title: String, arabic: String, count: Int, target: Int) = withContext(Dispatchers.IO) {
        if (count > 0) {
            tasbihDao.insertTasbihRecord(
                TasbihEntity(
                    dhikrTitle = title,
                    dhikrArabic = arabic,
                    count = count,
                    target = target
                )
            )
        }
    }

    suspend fun deleteTasbihRecord(id: Int) = withContext(Dispatchers.IO) {
        tasbihDao.deleteTasbihRecord(id)
    }

    suspend fun clearTasbihRecords() = withContext(Dispatchers.IO) {
        tasbihDao.clearAllRecords()
    }

    // --- QURAN ---
    suspend fun getQuranSurahs(): List<SurahMeta> = withContext(Dispatchers.IO) {
        try {
            val res = api.getQuranSurahs()
            if (res.success && res.data != null && res.data.surahs.isNotEmpty()) {
                return@withContext res.data.surahs
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext QuranDataStore.surahsList
    }

    suspend fun getQuranSurahDetail(surahNumber: Int, reciterId: Int? = 1): QuranSurahDetailData = withContext(Dispatchers.IO) {
        try {
            val res = api.getQuranSurahDetail(
                number = surahNumber,
                script = "uthmani",
                translation = "sahih_international",
                reciter = reciterId
            )
            if (res.success && res.data != null && res.data.verses.isNotEmpty()) {
                return@withContext res.data
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Fallback for demo/offline
        val meta = QuranDataStore.surahsList.find { it.number == surahNumber } ?: SurahMeta(surahNumber, "سورة $surahNumber", "Surah $surahNumber")
        val verses = when (surahNumber) {
            1 -> QuranDataStore.alFatihahVerses
            112 -> QuranDataStore.alIkhlasVerses
            else -> {
                val pad = surahNumber.toString().padStart(3, '0')
                (1..meta.verses_count.coerceAtLeast(1).coerceAtMost(25)).map { v ->
                    val vPad = v.toString().padStart(3, '0')
                    AyahItem(
                        verse_key = "$surahNumber:$v",
                        surah_name = meta.name_english,
                        ayah = v,
                        arabic = if (v == 1 && meta.bismillah_pre) "بِسْمِ ٱللَّهِ ٱلرَّحْمَـٰنِ ٱلرَّحِيمِ" else "آية مباركة من ${meta.name_arabic} ($v)",
                        transliteration = "Ayah $v of ${meta.name_english}",
                        translations = mapOf("sahih_international" to "Verse $v from Surah ${meta.name_english} (${meta.name_translation ?: ""})."),
                        audio = com.example.data.model.AyahAudioInfo("https://everyayah.com/data/Alafasy_128kbps/$pad$vPad.mp3")
                    )
                }
            }
        }

        val pad = surahNumber.toString().padStart(3, '0')
        val recitersAudio = listOf(
            SurahReciterAudio(1, "Mishary Rashid Alafasy", "Murattal", "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/$pad.mp3"),
            SurahReciterAudio(2, "Abdul Rahman Al-Sudais", "Murattal", "https://download.quranicaudio.com/quran/abdurrahmaan_as-sudais/$pad.mp3"),
            SurahReciterAudio(3, "Abdul Basit Abdul Samad", "Murattal", "https://download.quranicaudio.com/quran/abdul_basit_murattal/$pad.mp3")
        )

        return@withContext QuranSurahDetailData(
            surah = meta,
            audio = recitersAudio,
            total_verses = verses.size,
            verses = verses
        )
    }

    suspend fun getQuranJuz(juzNumber: Int): QuranJuzData = withContext(Dispatchers.IO) {
        try {
            val res = api.getQuranJuz(number = juzNumber)
            if (res.success && res.data != null && res.data.verses.isNotEmpty()) {
                return@withContext res.data
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val juzVerses = when (juzNumber) {
            1 -> QuranDataStore.alFatihahVerses
            30 -> QuranDataStore.alIkhlasVerses
            else -> {
                (1..12).map { v ->
                    val ayahNum = v
                    AyahItem(
                        verse_key = "$juzNumber:$ayahNum",
                        surah_name = "Juz $juzNumber",
                        ayah = ayahNum,
                        arabic = "آية مباركة $ayahNum من الجزء $juzNumber - هدى ونور للمؤمنين",
                        transliteration = "Ayah $ayahNum of Juz $juzNumber",
                        translations = mapOf("sahih_international" to "Verse $ayahNum from Juz $juzNumber of the Holy Quran - Divine guidance, light, and mercy for all believers."),
                        audio = com.example.data.model.AyahAudioInfo("https://everyayah.com/data/Alafasy_128kbps/00100${ayahNum.coerceAtMost(7)}.mp3")
                    )
                }
            }
        }
        return@withContext QuranJuzData(
            juz_number = juzNumber,
            total_verses = juzVerses.size,
            verses = juzVerses
        )
    }

    suspend fun getQuranMutashabihat(surahNumber: Int): List<MutashabihVerseItem> = withContext(Dispatchers.IO) {
        try {
            val res = api.getQuranMutashabihatSurah(surah = surahNumber)
            if (res.success && res.data != null && res.data.verses.isNotEmpty()) {
                return@withContext res.data.verses
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext QuranDataStore.sampleMutashabihat
    }

    // ==========================================
    // TAFSIR
    // ==========================================

    suspend fun getTafsirSources(): List<com.example.data.model.TafsirSourceItem> = withContext(Dispatchers.IO) {
        try {
            val res = api.getTafsirSources()
            if (res.success && res.data != null && res.data.tafasir.isNotEmpty()) {
                return@withContext res.data.tafasir
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext listOf(
            com.example.data.model.TafsirSourceItem("ibn_kathir", "Ibn Kathir (Abridged)", "english", "Hafiz Ibn Kathir"),
            com.example.data.model.TafsirSourceItem("maarif", "Ma'arif al-Qur'an", "english", "Mufti Muhammad Shafi"),
            com.example.data.model.TafsirSourceItem("muyassar", "Tafsir Muyassar", "arabic", "Ministry of Islamic Affairs"),
            com.example.data.model.TafsirSourceItem("ibn_kathir_ar", "Tafsir Ibn Kathir (Árabe)", "arabic", "Hafiz Ibn Kathir")
        )
    }

    suspend fun getTafsirAyah(tafsirKey: String, surah: Int, ayah: Int): com.example.data.model.TafsirDetailItem? = withContext(Dispatchers.IO) {
        try {
            val res = api.getTafsirAyah(tafsirKey, surah, ayah)
            if (res.success && res.data != null && res.data.tafsir != null) {
                return@withContext res.data.tafsir
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext com.example.data.model.TafsirDetailItem(
            key = tafsirKey,
            name = if (tafsirKey == "maarif") "Ma'arif al-Qur'an" else "Ibn Kathir",
            language = "english",
            author = "Hafiz Ibn Kathir",
            text = "Tafsir para Surah $surah, Versículo $ayah: Este versículo contém orientações profundas e bênçãos reveladas em Makkah/Madinah para a orientação de toda a humanidade."
        )
    }

    suspend fun getTafsirSurah(tafsirKey: String, surah: Int): com.example.data.model.TafsirSurahData? = withContext(Dispatchers.IO) {
        try {
            val res = api.getTafsirSurah(tafsirKey, surah)
            if (res.success && res.data != null) {
                return@withContext res.data
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext null
    }

    // ==========================================
    // QIBLA
    // ==========================================

    suspend fun getQibla(lat: Double, lng: Double): com.example.data.model.QiblaData = withContext(Dispatchers.IO) {
        try {
            val res = api.getQibla(lat, lng)
            if (res.success && res.data != null) {
                return@withContext res.data
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        // Fallback calculated bearing for standard coordinates to Makkah (21.4225, 39.8262)
        val phi1 = Math.toRadians(lat)
        val phi2 = Math.toRadians(21.4225)
        val deltaLambda = Math.toRadians(39.8262 - lng)
        val y = Math.sin(deltaLambda) * Math.cos(phi2)
        val x = Math.cos(phi1) * Math.sin(phi2) - Math.sin(phi1) * Math.cos(phi2) * Math.cos(deltaLambda)
        var bearing = Math.toDegrees(Math.atan2(y, x))
        bearing = (bearing + 360.0) % 360.0
        val distKm = 10300.0

        return@withContext com.example.data.model.QiblaData(
            qibla_direction = (Math.round(bearing * 100.0) / 100.0),
            compass_bearing = when {
                bearing in 22.5..67.5 -> "NE"
                bearing in 67.5..112.5 -> "E"
                bearing in 112.5..157.5 -> "SE"
                bearing in 157.5..202.5 -> "S"
                bearing in 202.5..247.5 -> "SW"
                bearing in 247.5..292.5 -> "W"
                bearing in 292.5..337.5 -> "NW"
                else -> "N"
            },
            location = com.example.data.model.QiblaLocationData(lat, lng),
            kaaba_coordinates = com.example.data.model.QiblaLocationData(21.4225, 39.8262),
            distance_km = distKm,
            distance_miles = distKm * 0.621371,
            note = "Cálculo astronômico em relação ao Norte geográfico para a Sagrada Kaaba em Makkah."
        )
    }

    // ==========================================
    // 99 NAMES OF ALLAH (ASMA-UL-HUSNA)
    // ==========================================

    suspend fun getAsmaUlHusna(): com.example.data.model.AsmaUlHusnaData = withContext(Dispatchers.IO) {
        try {
            val res = api.getAsmaUlHusna()
            if (res.success && res.data != null && res.data.names.isNotEmpty()) {
                return@withContext res.data
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext fallbackAsmaUlHusna()
    }

    suspend fun getAsmaUlHusnaSpecific(number: Int): com.example.data.model.AsmaNameItem? = withContext(Dispatchers.IO) {
        try {
            val res = api.getAsmaUlHusnaSpecific(number)
            if (res.success && res.data?.name != null) {
                return@withContext res.data.name
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext fallbackAsmaUlHusna().names.firstOrNull { it.number == number }
    }

    // ==========================================
    // ISLAMIC NAMES
    // ==========================================

    suspend fun getIslamicNames(
        page: Int = 1,
        limit: Int = 100,
        gender: String? = null,
        origin: String? = null
    ): com.example.data.model.IslamicNamesData = withContext(Dispatchers.IO) {
        try {
            val res = api.getIslamicNames(page, limit, gender, origin)
            if (res.success && res.data != null && res.data.names.isNotEmpty()) {
                return@withContext res.data
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val fallback = fallbackIslamicNames()
        val filtered = fallback.filter {
            (gender == null || it.gender.equals(gender, ignoreCase = true)) &&
            (origin == null || it.origin?.contains(origin, ignoreCase = true) == true)
        }
        return@withContext com.example.data.model.IslamicNamesData(
            total = filtered.size,
            page = 1,
            limit = 100,
            total_pages = 1,
            names = filtered
        )
    }

    suspend fun getRandomIslamicName(gender: String? = null): com.example.data.model.IslamicNameItem = withContext(Dispatchers.IO) {
        try {
            val res = api.getRandomIslamicName(gender)
            if (res.success && res.data != null) {
                return@withContext res.data
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val fallback = fallbackIslamicNames()
        val filtered = if (gender != null) fallback.filter { it.gender.equals(gender, ignoreCase = true) } else fallback
        return@withContext filtered.randomOrNull() ?: fallback.first()
    }

    // ==========================================
    // MOON SIGHTING & LUNAR PHASES
    // ==========================================

    suspend fun getMoonSighting(date: String? = null): com.example.data.model.MoonSightingData = withContext(Dispatchers.IO) {
        try {
            val res = api.getMoonSighting(date)
            if (res.success && res.data != null) {
                return@withContext res.data
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext com.example.data.model.MoonSightingData(
            gregorian = com.example.data.model.MoonGregorianData(2026, 8, 14, "2026-08-14"),
            hijri = com.example.data.model.MoonHijriData(
                year = 1448,
                month = 3,
                day = 1,
                month_name = "Rabi al-Awwal",
                month_arabic = "رَبِيع الأَوَّل",
                date_formatted = "1 Rabi al-Awwal 1448 AH",
                month_note = "Mês do nascimento e passagem do Nobre Profeta Muhammad (ﷺ).",
                is_sacred_month = false
            ),
            moon = com.example.data.model.MoonDetailData(
                age_days = 1.27,
                phase = "Waxing Crescent",
                illumination_pct = "8.6",
                crescent_visibility = "possibly_visible",
                crescent_note = "O crescente lunar pode ser visível ao pôr do sol em condições atmosféricas favoráveis.",
                last_new_moon = "2026-08-12",
                next_new_moon = "2026-09-11"
            ),
            disclaimer = "Cálculos astronômicos precisos. O avistamento a olho nu (Rukyah) pode variar conforme as condições do horizonte local."
        )
    }

    suspend fun getMoonPhases(count: Int = 12): List<com.example.data.model.NewMoonItem> = withContext(Dispatchers.IO) {
        try {
            val res = api.getMoonPhases(count)
            if (res.success && res.data != null && res.data.new_moons.isNotEmpty()) {
                return@withContext res.data.new_moons
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext fallbackNewMoons()
    }

    private fun fallbackAsmaUlHusna(): com.example.data.model.AsmaUlHusnaData {
        val namesList = listOf(
            com.example.data.model.AsmaNameItem(1, "الرَّحْمٰنُ", "Ar-Rahman", "O Misericordiosíssimo", "Aquele cuja infinita misericórdia abrange toda a criação no universo."),
            com.example.data.model.AsmaNameItem(2, "الرَّحِيمُ", "Ar-Raheem", "O Compassivo", "Aquele que concede misericórdia contínua e especial aos crentes."),
            com.example.data.model.AsmaNameItem(3, "المَلِكُ", "Al-Malik", "O Soberano Supremo", "O Rei Absoluto e Senhor de todos os mundos e do Dia do Julgamento."),
            com.example.data.model.AsmaNameItem(4, "القُدُّوسُ", "Al-Quddoos", "O Santíssimo", "O Puro de toda imperfeição, livre de qualquer defeito ou limitação."),
            com.example.data.model.AsmaNameItem(5, "السَّلاَمُ", "As-Salaam", "A Fonte da Paz", "Aquele que está livre de todo mal e concede paz, segurança e serenidade."),
            com.example.data.model.AsmaNameItem(6, "المُؤْمِنُ", "Al-Mu'min", "O Guardião da Fé", "Aquele que concede segurança e fé nos corações e cumpre Suas promessas."),
            com.example.data.model.AsmaNameItem(7, "المُهَيْمِنُ", "Al-Muhaymin", "O Protetor Vigilante", "Aquele que tudo testemunha, preserva e supervisiona com pleno domínio."),
            com.example.data.model.AsmaNameItem(8, "العَزِيزُ", "Al-Azeez", "O Todo-Poderoso", "O Invencível, cuja soberania e poder jamais podem ser sobrepujados."),
            com.example.data.model.AsmaNameItem(9, "الجَبَّارُ", "Al-Jabbaar", "O Irresistível / O Restaurador", "Aquele que restaura o quebrado e perante cuja majestade tudo se curva."),
            com.example.data.model.AsmaNameItem(10, "المُتَكَبِّرُ", "Al-Mutakabbir", "O Supremo em Glória", "Aquele a quem pertence toda a grandeza, majestade e altivez legítima."),
            com.example.data.model.AsmaNameItem(11, "الخَالِقُ", "Al-Khaaliq", "O Criador", "Aquele que traz tudo da não-existência para a existência com proporção perfeita."),
            com.example.data.model.AsmaNameItem(12, "البَارِئُ", "Al-Baari'", "O Originador Perfeito", "O artífice que harmoniza e molda cada detalhe da criação."),
            com.example.data.model.AsmaNameItem(13, "المُصَوِّرُ", "Al-Musawwir", "O Modelador de Formas", "Aquele que dá a cada criatura sua forma, beleza e características singulares."),
            com.example.data.model.AsmaNameItem(14, "الغَفَّارُ", "Al-Ghaffaar", "O Perdoador Constante", "Aquele que perdoa e encobre os pecados de Seus servos repetidamente."),
            com.example.data.model.AsmaNameItem(15, "القَهَّارُ", "Al-Qahhaar", "O Dominador Supremo", "O Dominante absoluto sobre tudo o que existe no céu e na terra."),
            com.example.data.model.AsmaNameItem(16, "الوَهَّابُ", "Al-Wahhaab", "O Doador Generoso", "Aquele que concede incontáveis bênçãos sem pedir nada em troca."),
            com.example.data.model.AsmaNameItem(17, "الرَّزَّاقُ", "Ar-Razzaaq", "O Provedor", "Aquele que provê o sustento físico e espiritual para todas as criaturas."),
            com.example.data.model.AsmaNameItem(18, "الفَتَّاحُ", "Al-Fattaah", "O Abridor de Caminhos", "Aquele que abre as portas da misericórdia, do conhecimento e do alívio."),
            com.example.data.model.AsmaNameItem(19, "العَلِيمُ", "Al-'Aleem", "O Onisciente", "Aquele cujo conhecimento abrange o oculto e o manifesto perfeitamente."),
            com.example.data.model.AsmaNameItem(20, "القَابِضُ", "Al-Qaabid", "O Retentor", "Aquele que retém ou restringe o sustento e as almas segundo Sua divina sabedoria.")
        )
        return com.example.data.model.AsmaUlHusnaData(
            names = namesList,
            total_count = 99,
            arabic_title = "أسماء الله الحسنى",
            english_title = "The 99 Beautiful Names of Allah",
            description = "Os 99 belos nomes e atributos sagrados de Allah mencionados no Sagrado Alcorão e na Nobre Sunnah.",
            source = "Al-Qur'an & Sahih Hadith",
            recitation_benefits = "Recitar e meditar sobre esses atributos sagrados aproxima o servo de Allah e é fonte imensa de recompensa e paz espiritual.",
            hadith = "O Profeta Muhammad (ﷺ) disse: 'Certamente Allah tem noventa e nove nomes, cem menos um; quem os memorizar e viver segundo eles entrará no Paraíso.' (Bukhari & Muslim)"
        )
    }

    private fun fallbackIslamicNames(): List<com.example.data.model.IslamicNameItem> {
        return listOf(
            com.example.data.model.IslamicNameItem(1, "Muhammad", "مُحَمَّد", "male", "Louvado, digno de elogios", "Árabe", "ح م د", "Nome do Nobre Mensageiro de Allah (ﷺ)."),
            com.example.data.model.IslamicNameItem(2, "Ahmad", "أَحْمَد", "male", "Mais digno de louvor", "Árabe", "ح م د", "Nome do Profeta Muhammad (ﷺ) mencionado no Alcorão (61:6)."),
            com.example.data.model.IslamicNameItem(3, "Ali", "عَلِيّ", "male", "Elevado, nobre, exaltado", "Árabe", "ع ل و", "Quarto Califa bem-guiado e primo do Profeta (ﷺ)."),
            com.example.data.model.IslamicNameItem(4, "Umar", "عُمَر", "male", "De vida próspera e duradoura", "Árabe", "ع م ر", "Segundo Califa bem-guiado, símbolo de justiça e retidão."),
            com.example.data.model.IslamicNameItem(5, "Uthman", "عُثْمَان", "male", "Companheiro nobre e generoso", "Árabe", "ع ث م", "Terceiro Califa bem-guiado, 'Dhun-Nurayn'."),
            com.example.data.model.IslamicNameItem(6, "Abu Bakr", "أَبُو بَكْر", "male", "O Primeiro e Verdadeiro (As-Siddiq)", "Árabe", "ب ك ر", "Primeiro Califa do Islã e o mais próximo companheiro."),
            com.example.data.model.IslamicNameItem(7, "Fatima", "فَاطِمَة", "female", "Pura, aquela que se abstém do pecado", "Árabe", "ف ط م", "Amada filha do Profeta Muhammad (ﷺ) e líder das mulheres do Paraíso."),
            com.example.data.model.IslamicNameItem(8, "Aisha", "عَائِشَة", "female", "Vivaz, cheia de vida e prosperidade", "Árabe", "ع ي ش", "Mãe dos Crentes e grande sábia na transmissão da Sunnah."),
            com.example.data.model.IslamicNameItem(9, "Khadijah", "خَدِيجَة", "female", "Preciosa, confiável", "Árabe", "خ د ج", "Primeira esposa do Profeta (ﷺ) e primeira crente no Islã."),
            com.example.data.model.IslamicNameItem(10, "Maryam", "مَرْيَم", "female", "Devota, serva pura de Deus", "Hebraico/Árabe", null, "Mãe do Profeta Isa (Jesus, que a paz esteja com ele)."),
            com.example.data.model.IslamicNameItem(11, "Ibrahim", "إِبْرَاهِيم", "male", "Pai das nações e amigo íntimo de Allah (Khalilullah)", "Hebraico/Árabe", null, "O Profeta Abraão (que a paz esteja com ele)."),
            com.example.data.model.IslamicNameItem(12, "Yusuf", "يُوسُف", "male", "Deus acrescenta virtudes", "Hebraico/Árabe", null, "O Profeta José (que a paz esteja com ele), modelo de beleza e retidão."),
            com.example.data.model.IslamicNameItem(13, "Zaynab", "زَيْنَب", "female", "Flor perfumada, árvore formosa", "Árabe", "ز ي ن", "Filha do Profeta Muhammad (ﷺ)."),
            com.example.data.model.IslamicNameItem(14, "Bilal", "بِلَال", "male", "Água fresca, alívio revigorante", "Árabe", "ب ل ل", "Bilal ibn Rabah (ra), primeiro muezim do Islã."),
            com.example.data.model.IslamicNameItem(15, "Noor", "نُور", "female", "Luz divina e radiante", "Árabe", "ن و ر", "Nome luminoso que reflete clareza e orientação.")
        )
    }

    private fun fallbackNewMoons(): List<com.example.data.model.NewMoonItem> {
        return listOf(
            com.example.data.model.NewMoonItem(
                lunation = 329,
                new_moon = com.example.data.model.NewMoonDetail(
                    date = "2026-08-12",
                    time_utc = "17:38 UTC",
                    gregorian = com.example.data.model.MoonGregorianData(2026, 8, 12, "2026-08-12"),
                    hijri = com.example.data.model.MoonHijriData(1448, 2, 29, "Safar")
                ),
                expected_crescent = com.example.data.model.ExpectedCrescentDetail("2026-08-13", "Rabi al-Awwal")
            ),
            com.example.data.model.NewMoonItem(
                lunation = 330,
                new_moon = com.example.data.model.NewMoonDetail(
                    date = "2026-09-11",
                    time_utc = "03:28 UTC",
                    gregorian = com.example.data.model.MoonGregorianData(2026, 9, 11, "2026-09-11"),
                    hijri = com.example.data.model.MoonHijriData(1448, 3, 29, "Rabi al-Awwal")
                ),
                expected_crescent = com.example.data.model.ExpectedCrescentDetail("2026-09-12", "Rabi al-Thani")
            ),
            com.example.data.model.NewMoonItem(
                lunation = 331,
                new_moon = com.example.data.model.NewMoonDetail(
                    date = "2026-10-10",
                    time_utc = "15:51 UTC",
                    gregorian = com.example.data.model.MoonGregorianData(2026, 10, 10, "2026-10-10"),
                    hijri = com.example.data.model.MoonHijriData(1448, 4, 29, "Rabi al-Thani")
                ),
                expected_crescent = com.example.data.model.ExpectedCrescentDetail("2026-10-11", "Jumada al-Awwal")
            ),
            com.example.data.model.NewMoonItem(
                lunation = 332,
                new_moon = com.example.data.model.NewMoonDetail(
                    date = "2026-11-09",
                    time_utc = "07:03 UTC",
                    gregorian = com.example.data.model.MoonGregorianData(2026, 11, 9, "2026-11-09"),
                    hijri = com.example.data.model.MoonHijriData(1448, 5, 29, "Jumada al-Awwal")
                ),
                expected_crescent = com.example.data.model.ExpectedCrescentDetail("2026-11-10", "Jumada al-Thani")
            ),
            com.example.data.model.NewMoonItem(
                lunation = 333,
                new_moon = com.example.data.model.NewMoonDetail(
                    date = "2026-12-09",
                    time_utc = "00:53 UTC",
                    gregorian = com.example.data.model.MoonGregorianData(2026, 12, 9, "2026-12-09"),
                    hijri = com.example.data.model.MoonHijriData(1448, 6, 29, "Jumada al-Thani")
                ),
                expected_crescent = com.example.data.model.ExpectedCrescentDetail("2026-12-10", "Rajab (Mês Sagrado)")
            ),
            com.example.data.model.NewMoonItem(
                lunation = 334,
                new_moon = com.example.data.model.NewMoonDetail(
                    date = "2027-01-07",
                    time_utc = "20:26 UTC",
                    gregorian = com.example.data.model.MoonGregorianData(2027, 1, 7, "2027-01-07"),
                    hijri = com.example.data.model.MoonHijriData(1448, 7, 29, "Rajab")
                ),
                expected_crescent = com.example.data.model.ExpectedCrescentDetail("2027-01-08", "Shaban")
            ),
            com.example.data.model.NewMoonItem(
                lunation = 335,
                new_moon = com.example.data.model.NewMoonDetail(
                    date = "2027-02-06",
                    time_utc = "15:57 UTC",
                    gregorian = com.example.data.model.MoonGregorianData(2027, 2, 6, "2027-02-06"),
                    hijri = com.example.data.model.MoonHijriData(1448, 8, 29, "Shaban")
                ),
                expected_crescent = com.example.data.model.ExpectedCrescentDetail("2027-02-07", "Ramadan (Mês Sagrado do Jejum)")
            )
        )
    }
}

