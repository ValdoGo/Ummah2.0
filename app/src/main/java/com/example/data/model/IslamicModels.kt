package com.example.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// --- PRAYER TIMES ---

@JsonClass(generateAdapter = true)
data class PrayerTimesResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: PrayerTimesData? = null,
    @Json(name = "timestamp") val timestamp: String? = null
)

@JsonClass(generateAdapter = true)
data class PrayerTimesData(
    @Json(name = "date") val date: String? = null,
    @Json(name = "timezone") val timezone: String? = null,
    @Json(name = "location") val location: LocationCoords? = null,
    @Json(name = "calculation_method") val calculation_method: String? = null,
    @Json(name = "madhab") val madhab: String? = null,
    @Json(name = "high_latitude_rule") val high_latitude_rule: String? = null,
    @Json(name = "prayer_times") val prayer_times: PrayerTimesMap? = null,
    @Json(name = "prayer_datetimes") val prayer_datetimes: PrayerDateTimesMap? = null,
    @Json(name = "current_status") val current_status: CurrentPrayerStatus? = null
)

@JsonClass(generateAdapter = true)
data class LocationCoords(
    @Json(name = "latitude") val latitude: Double = 0.0,
    @Json(name = "longitude") val longitude: Double = 0.0
)

@JsonClass(generateAdapter = true)
data class PrayerTimesMap(
    @Json(name = "imsak") val imsak: String? = "04:30",
    @Json(name = "fajr") val fajr: String? = "04:45",
    @Json(name = "sunrise") val sunrise: String? = "06:10",
    @Json(name = "dhuhr") val dhuhr: String? = "12:30",
    @Json(name = "asr") val asr: String? = "15:45",
    @Json(name = "maghrib") val maghrib: String? = "18:40",
    @Json(name = "isha") val isha: String? = "20:00"
)

@JsonClass(generateAdapter = true)
data class PrayerDateTimesMap(
    @Json(name = "imsak") val imsak: String? = null,
    @Json(name = "fajr") val fajr: String? = null,
    @Json(name = "sunrise") val sunrise: String? = null,
    @Json(name = "dhuhr") val dhuhr: String? = null,
    @Json(name = "asr") val asr: String? = null,
    @Json(name = "maghrib") val maghrib: String? = null,
    @Json(name = "isha") val isha: String? = null
)

@JsonClass(generateAdapter = true)
data class CurrentPrayerStatus(
    @Json(name = "current_prayer") val current_prayer: String? = null,
    @Json(name = "next_prayer") val next_prayer: String? = null,
    @Json(name = "time_until_next") val time_until_next: String? = null,
    @Json(name = "minutes_until_next") val minutes_until_next: Int? = null
)

// --- DUAS & ADHKAR ---

@JsonClass(generateAdapter = true)
data class DuasCategoriesResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: DuasCategoriesData? = null
)

@JsonClass(generateAdapter = true)
data class DuasCategoriesData(
    @Json(name = "total") val total: Int = 0,
    @Json(name = "categories") val categories: List<DuaCategory> = emptyList()
)

@JsonClass(generateAdapter = true)
data class DuaCategory(
    @Json(name = "id") val id: String = "",
    @Json(name = "name") val name: String = "",
    @Json(name = "description") val description: String = "",
    @Json(name = "count") val count: Int = 0
)

@JsonClass(generateAdapter = true)
data class DuasResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: DuasData? = null
)

@JsonClass(generateAdapter = true)
data class DuasData(
    @Json(name = "total") val total: Int = 0,
    @Json(name = "categories") val categories: List<DuaCategory>? = null,
    @Json(name = "duas") val duas: List<DuaItem> = emptyList()
)

@JsonClass(generateAdapter = true)
data class DuaItem(
    @Json(name = "id") val id: Int = 0,
    @Json(name = "category") val category: String = "",
    @Json(name = "title") val title: String = "",
    @Json(name = "arabic") val arabic: String = "",
    @Json(name = "transliteration") val transliteration: String = "",
    @Json(name = "translation") val translation: String = "",
    @Json(name = "source") val source: String = "",
    @Json(name = "repeat") val repeat: Int = 1
)

// --- HADITH ---

@JsonClass(generateAdapter = true)
data class HadithCollectionsResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: HadithCollectionsData? = null
)

@JsonClass(generateAdapter = true)
data class HadithCollectionsData(
    @Json(name = "collections") val collections: List<HadithCollectionInfo> = emptyList(),
    @Json(name = "total_hadiths") val total_hadiths: Int? = 0
)

@JsonClass(generateAdapter = true)
data class HadithCollectionInfo(
    @Json(name = "key") val key: String = "",
    @Json(name = "name") val name: String = "",
    @Json(name = "arabic_name") val arabic_name: String? = null,
    @Json(name = "author") val author: String? = null,
    @Json(name = "reliability") val reliability: String? = null,
    @Json(name = "total_hadiths") val total_hadiths: Int = 0
)

@JsonClass(generateAdapter = true)
data class HadithRandomResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: HadithItem? = null
)

@JsonClass(generateAdapter = true)
data class HadithItem(
    @Json(name = "id") val id: String = "",
    @Json(name = "collection") val collection: String? = null,
    @Json(name = "collection_name") val collection_name: String? = null,
    @Json(name = "hadithnumber") val hadithnumber: Int? = null,
    @Json(name = "arabic") val arabic: String? = null,
    @Json(name = "english") val english: String? = null,
    @Json(name = "grade") val grade: String? = null
)

@JsonClass(generateAdapter = true)
data class HadithSearchResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: HadithSearchData? = null
)

@JsonClass(generateAdapter = true)
data class HadithSearchData(
    @Json(name = "query") val query: String? = null,
    @Json(name = "collection") val collection: String? = null,
    @Json(name = "limit") val limit: Int? = 25,
    @Json(name = "total_found") val total_found: Int? = 0,
    @Json(name = "hadiths") val hadiths: List<HadithItem> = emptyList()
)

@JsonClass(generateAdapter = true)
data class HadithCollectionBrowseResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: HadithCollectionBrowseData? = null
)

@JsonClass(generateAdapter = true)
data class HadithCollectionBrowseData(
    @Json(name = "collection") val collection: String? = null,
    @Json(name = "collection_name") val collection_name: String? = null,
    @Json(name = "page") val page: Int = 1,
    @Json(name = "limit") val limit: Int = 50,
    @Json(name = "total") val total: Int = 0,
    @Json(name = "total_pages") val total_pages: Int = 0,
    @Json(name = "hadiths") val hadiths: List<HadithItem> = emptyList()
)

// --- HIJRI DATE & ISLAMIC EVENTS ---

@JsonClass(generateAdapter = true)
data class TodayHijriResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: TodayHijriData? = null
)

@JsonClass(generateAdapter = true)
data class TodayHijriData(
    @Json(name = "gregorian") val gregorian: GregorianDetail? = null,
    @Json(name = "hijri") val hijri: HijriDetail? = null
)

@JsonClass(generateAdapter = true)
data class GregorianDetail(
    @Json(name = "date") val date: String? = null,
    @Json(name = "formatted") val formatted: String? = null,
    @Json(name = "day_of_week") val day_of_week: String? = null,
    @Json(name = "day") val day: Int = 1,
    @Json(name = "month") val month: Int = 1,
    @Json(name = "month_name") val month_name: String? = null,
    @Json(name = "year") val year: Int = 2026
)

@JsonClass(generateAdapter = true)
data class HijriDetail(
    @Json(name = "date") val date: String? = null,
    @Json(name = "formatted") val formatted: String? = null,
    @Json(name = "day") val day: Int = 1,
    @Json(name = "month") val month: Int = 1,
    @Json(name = "month_name") val month_name: String? = null,
    @Json(name = "month_name_arabic") val month_name_arabic: String? = null,
    @Json(name = "year") val year: Int = 1448,
    @Json(name = "era") val era: String? = "AH"
)

@JsonClass(generateAdapter = true)
data class IslamicEventsResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: IslamicEventsData? = null
)

@JsonClass(generateAdapter = true)
data class IslamicEventsData(
    @Json(name = "current_hijri_date") val current_hijri_date: TodayHijriData? = null,
    @Json(name = "next_event") val next_event: NextEventItem? = null,
    @Json(name = "events") val events: List<IslamicEventItem> = emptyList(),
    @Json(name = "note") val note: String? = null
)

@JsonClass(generateAdapter = true)
data class NextEventItem(
    @Json(name = "name") val name: String = "",
    @Json(name = "hijri_date") val hijri_date: String = "",
    @Json(name = "month") val month: Int = 0,
    @Json(name = "day") val day: Int = 0
)

@JsonClass(generateAdapter = true)
data class IslamicEventItem(
    @Json(name = "month") val month: Int = 1,
    @Json(name = "day") val day: Int = 1,
    @Json(name = "name") val name: String = "",
    @Json(name = "description") val description: String = ""
)

// --- QURAN DATA MODELS ---

@JsonClass(generateAdapter = true)
data class QuranSurahsResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: QuranSurahsData? = null
)

@JsonClass(generateAdapter = true)
data class QuranSurahsData(
    @Json(name = "total") val total: Int = 114,
    @Json(name = "surahs") val surahs: List<SurahMeta> = emptyList()
)

@JsonClass(generateAdapter = true)
data class SurahMeta(
    @Json(name = "number") val number: Int = 1,
    @Json(name = "name_arabic") val name_arabic: String = "",
    @Json(name = "name_english") val name_english: String = "",
    @Json(name = "name_complex") val name_complex: String? = null,
    @Json(name = "name_translation") val name_translation: String? = null,
    @Json(name = "revelation_place") val revelation_place: String = "makkah",
    @Json(name = "revelation_order") val revelation_order: Int = 1,
    @Json(name = "bismillah_pre") val bismillah_pre: Boolean = true,
    @Json(name = "verses_count") val verses_count: Int = 7,
    @Json(name = "pages") val pages: List<Int>? = null,
    @Json(name = "audio") val audio: SurahAudioMeta? = null
)

@JsonClass(generateAdapter = true)
data class SurahAudioMeta(
    @Json(name = "reciters_available") val reciters_available: Int? = 13,
    @Json(name = "example_audio") val example_audio: String? = null
)

@JsonClass(generateAdapter = true)
data class QuranSurahDetailResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: QuranSurahDetailData? = null
)

@JsonClass(generateAdapter = true)
data class QuranSurahDetailData(
    @Json(name = "surah") val surah: SurahMeta? = null,
    @Json(name = "audio") val audio: List<SurahReciterAudio> = emptyList(),
    @Json(name = "total_verses") val total_verses: Int = 0,
    @Json(name = "verses") val verses: List<AyahItem> = emptyList()
)

@JsonClass(generateAdapter = true)
data class SurahReciterAudio(
    @Json(name = "reciter_id") val reciter_id: Int = 1,
    @Json(name = "reciter") val reciter: String = "",
    @Json(name = "style") val style: String? = "Murattal",
    @Json(name = "surah_audio") val surah_audio: String? = null,
    @Json(name = "ayah_audio") val ayah_audio: String? = null
)

@JsonClass(generateAdapter = true)
data class AyahItem(
    @Json(name = "verse_key") val verse_key: String = "",
    @Json(name = "surah_name") val surah_name: String? = null,
    @Json(name = "ayah") val ayah: Int = 1,
    @Json(name = "arabic") val arabic: String = "",
    @Json(name = "transliteration") val transliteration: String? = null,
    @Json(name = "translations") val translations: Map<String, String>? = null,
    @Json(name = "audio") val audio: AyahAudioInfo? = null
) {
    val englishTranslation: String
        get() = translations?.get("sahih_international")
            ?: translations?.get("pickthall")
            ?: translations?.get("yusuf_ali")
            ?: translations?.values?.firstOrNull()
            ?: ""
}

@JsonClass(generateAdapter = true)
data class AyahAudioInfo(
    @Json(name = "ayah_audio") val ayah_audio: String? = null,
    @Json(name = "all_reciters") val all_reciters: String? = null
) {
    val url: String? get() = ayah_audio
}

@JsonClass(generateAdapter = true)
data class QuranJuzResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: QuranJuzData? = null
)

@JsonClass(generateAdapter = true)
data class QuranJuzData(
    @Json(name = "juz_number") val juz_number: Int = 1,
    @Json(name = "verses_mapping") val verses_mapping: Map<String, String>? = null,
    @Json(name = "total_verses") val total_verses: Int = 0,
    @Json(name = "verses") val verses: List<AyahItem> = emptyList()
)

@JsonClass(generateAdapter = true)
data class QuranAudioSurahResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: QuranAudioSurahData? = null
)

@JsonClass(generateAdapter = true)
data class QuranAudioSurahData(
    @Json(name = "surah") val surah: SurahMeta? = null,
    @Json(name = "reciters") val reciters: List<ReciterInfo> = emptyList()
)

@JsonClass(generateAdapter = true)
data class ReciterInfo(
    @Json(name = "id") val id: Int = 1,
    @Json(name = "name") val name: String = "",
    @Json(name = "name_arabic") val name_arabic: String? = null,
    @Json(name = "style") val style: String? = "Murattal",
    @Json(name = "full_surah_audio") val full_surah_audio: String? = null,
    @Json(name = "audio_url") val audio_url: String? = null
) {
    val name_english: String get() = name
    val audioUrl: String? get() = full_surah_audio ?: audio_url
}

@JsonClass(generateAdapter = true)
data class QuranMutashabihatResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: QuranMutashabihatData? = null
)

@JsonClass(generateAdapter = true)
data class QuranMutashabihatData(
    @Json(name = "surah") val surah: Int = 1,
    @Json(name = "surah_name_arabic") val surah_name_arabic: String? = null,
    @Json(name = "surah_name_english") val surah_name_english: String? = null,
    @Json(name = "total") val total: Int = 0,
    @Json(name = "page") val page: Int = 1,
    @Json(name = "limit") val limit: Int = 20,
    @Json(name = "total_pages") val total_pages: Int = 1,
    @Json(name = "verses") val verses: List<MutashabihVerseItem> = emptyList()
)

@JsonClass(generateAdapter = true)
data class MutashabihVerseItem(
    @Json(name = "verse_key") val verse_key: String = "",
    @Json(name = "surah") val surah: Int = 1,
    @Json(name = "ayah") val ayah: Int = 1,
    @Json(name = "surah_name_arabic") val surah_name_arabic: String? = null,
    @Json(name = "surah_name_english") val surah_name_english: String? = null,
    @Json(name = "arabic") val arabic: String = "",
    @Json(name = "translation") val translation: String? = null,
    @Json(name = "similar_verses") val similar_verses: List<SimilarVerseItem> = emptyList()
)

@JsonClass(generateAdapter = true)
data class SimilarVerseItem(
    @Json(name = "verse_key") val verse_key: String = "",
    @Json(name = "surah") val surah: Int = 1,
    @Json(name = "ayah") val ayah: Int = 1,
    @Json(name = "surah_name_arabic") val surah_name_arabic: String? = null,
    @Json(name = "surah_name_english") val surah_name_english: String? = null,
    @Json(name = "arabic") val arabic: String = "",
    @Json(name = "translation") val translation: String? = null
)

// ==========================================
// TAFSIR MODELS
// ==========================================

@JsonClass(generateAdapter = true)
data class TafsirSourcesResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: TafsirSourcesData? = null
)

@JsonClass(generateAdapter = true)
data class TafsirSourcesData(
    @Json(name = "total") val total: Int = 0,
    @Json(name = "tafasir") val tafasir: List<TafsirSourceItem> = emptyList(),
    @Json(name = "note") val note: String? = null
)

@JsonClass(generateAdapter = true)
data class TafsirSourceItem(
    @Json(name = "key") val key: String = "",
    @Json(name = "name") val name: String = "",
    @Json(name = "language") val language: String = "",
    @Json(name = "author") val author: String = "",
    @Json(name = "usage") val usage: String? = null
)

@JsonClass(generateAdapter = true)
data class TafsirAyahResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: TafsirAyahData? = null
)

@JsonClass(generateAdapter = true)
data class TafsirAyahData(
    @Json(name = "verse_key") val verse_key: String = "",
    @Json(name = "tafsir") val tafsir: TafsirDetailItem? = null
)

@JsonClass(generateAdapter = true)
data class TafsirDetailItem(
    @Json(name = "key") val key: String = "",
    @Json(name = "name") val name: String = "",
    @Json(name = "language") val language: String = "",
    @Json(name = "author") val author: String = "",
    @Json(name = "text") val text: String = ""
)

@JsonClass(generateAdapter = true)
data class TafsirSurahResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: TafsirSurahData? = null
)

@JsonClass(generateAdapter = true)
data class TafsirSurahData(
    @Json(name = "surah") val surah: Int = 1,
    @Json(name = "tafsir") val tafsir: TafsirSourceItem? = null,
    @Json(name = "total_verses") val total_verses: Int = 0,
    @Json(name = "verses") val verses: List<TafsirVerseItem> = emptyList()
)

@JsonClass(generateAdapter = true)
data class TafsirVerseItem(
    @Json(name = "verse_key") val verse_key: String = "",
    @Json(name = "ayah") val ayah: Int = 1,
    @Json(name = "text") val text: String = ""
)

// ==========================================
// QIBLA MODELS
// ==========================================

@JsonClass(generateAdapter = true)
data class QiblaResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: QiblaData? = null
)

@JsonClass(generateAdapter = true)
data class QiblaData(
    @Json(name = "qibla_direction") val qibla_direction: Double = 0.0,
    @Json(name = "compass_bearing") val compass_bearing: String = "N",
    @Json(name = "location") val location: QiblaLocationData? = null,
    @Json(name = "kaaba_coordinates") val kaaba_coordinates: QiblaLocationData? = null,
    @Json(name = "distance_km") val distance_km: Double = 0.0,
    @Json(name = "distance_miles") val distance_miles: Double = 0.0,
    @Json(name = "note") val note: String? = null
)

@JsonClass(generateAdapter = true)
data class QiblaLocationData(
    @Json(name = "latitude") val latitude: Double = 0.0,
    @Json(name = "longitude") val longitude: Double = 0.0
)

// ==========================================
// 99 NAMES OF ALLAH (ASMA-UL-HUSNA)
// ==========================================

@JsonClass(generateAdapter = true)
data class AsmaUlHusnaResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: AsmaUlHusnaData? = null
)

@JsonClass(generateAdapter = true)
data class AsmaUlHusnaData(
    @Json(name = "names") val names: List<AsmaNameItem> = emptyList(),
    @Json(name = "total_count") val total_count: Int? = 99,
    @Json(name = "arabic_title") val arabic_title: String? = null,
    @Json(name = "english_title") val english_title: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "source") val source: String? = null,
    @Json(name = "recitation_benefits") val recitation_benefits: String? = null,
    @Json(name = "hadith") val hadith: String? = null
)

@JsonClass(generateAdapter = true)
data class AsmaNameItem(
    @Json(name = "number") val number: Int = 1,
    @Json(name = "arabic") val arabic: String = "",
    @Json(name = "transliteration") val transliteration: String = "",
    @Json(name = "english") val english: String = "",
    @Json(name = "meaning") val meaning: String = ""
)

@JsonClass(generateAdapter = true)
data class AsmaUlHusnaSpecificResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: AsmaUlHusnaSpecificData? = null
)

@JsonClass(generateAdapter = true)
data class AsmaUlHusnaSpecificData(
    @Json(name = "name") val name: AsmaNameItem? = null,
    @Json(name = "context") val context: AsmaContextData? = null
)

@JsonClass(generateAdapter = true)
data class AsmaContextData(
    @Json(name = "position") val position: String? = null,
    @Json(name = "arabic_title") val arabic_title: String? = null,
    @Json(name = "english_title") val english_title: String? = null
)

// ==========================================
// ISLAMIC NAMES MODELS
// ==========================================

@JsonClass(generateAdapter = true)
data class IslamicNamesResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: IslamicNamesData? = null
)

@JsonClass(generateAdapter = true)
data class IslamicNamesData(
    @Json(name = "total") val total: Int = 0,
    @Json(name = "page") val page: Int = 1,
    @Json(name = "limit") val limit: Int = 100,
    @Json(name = "total_pages") val total_pages: Int = 1,
    @Json(name = "names") val names: List<IslamicNameItem> = emptyList()
)

@JsonClass(generateAdapter = true)
data class IslamicNameItem(
    @Json(name = "id") val id: Int = 0,
    @Json(name = "name") val name: String = "",
    @Json(name = "arabic") val arabic: String? = null,
    @Json(name = "gender") val gender: String = "male",
    @Json(name = "meaning") val meaning: String = "",
    @Json(name = "origin") val origin: String? = "Arabic",
    @Json(name = "root") val root: String? = null,
    @Json(name = "note") val note: String? = null
)

@JsonClass(generateAdapter = true)
data class IslamicNamesRandomResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: IslamicNameItem? = null
)

// ==========================================
// MOON SIGHTING & LUNAR PHASES MODELS
// ==========================================

@JsonClass(generateAdapter = true)
data class MoonSightingResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: MoonSightingData? = null
)

@JsonClass(generateAdapter = true)
data class MoonSightingData(
    @Json(name = "gregorian") val gregorian: MoonGregorianData? = null,
    @Json(name = "hijri") val hijri: MoonHijriData? = null,
    @Json(name = "moon") val moon: MoonDetailData? = null,
    @Json(name = "disclaimer") val disclaimer: String? = null
)

@JsonClass(generateAdapter = true)
data class MoonGregorianData(
    @Json(name = "year") val year: Int = 2026,
    @Json(name = "month") val month: Int = 8,
    @Json(name = "day") val day: Int = 14,
    @Json(name = "date") val date: String? = null
)

@JsonClass(generateAdapter = true)
data class MoonHijriData(
    @Json(name = "year") val year: Int = 1448,
    @Json(name = "month") val month: Int = 3,
    @Json(name = "day") val day: Int = 1,
    @Json(name = "month_name") val month_name: String = "Rabi al-Awwal",
    @Json(name = "month_arabic") val month_arabic: String? = null,
    @Json(name = "date_formatted") val date_formatted: String? = null,
    @Json(name = "month_note") val month_note: String? = null,
    @Json(name = "is_sacred_month") val is_sacred_month: Boolean? = false
)

@JsonClass(generateAdapter = true)
data class MoonDetailData(
    @Json(name = "age_days") val age_days: Double? = 0.0,
    @Json(name = "phase") val phase: String? = "Waxing Crescent",
    @Json(name = "illumination_pct") val illumination_pct: String? = "0.0",
    @Json(name = "crescent_visibility") val crescent_visibility: String? = null,
    @Json(name = "crescent_note") val crescent_note: String? = null,
    @Json(name = "last_new_moon") val last_new_moon: String? = null,
    @Json(name = "next_new_moon") val next_new_moon: String? = null
)

@JsonClass(generateAdapter = true)
data class MoonPhasesResponse(
    @Json(name = "success") val success: Boolean = true,
    @Json(name = "service") val service: String? = null,
    @Json(name = "data") val data: MoonPhasesData? = null
)

@JsonClass(generateAdapter = true)
data class MoonPhasesData(
    @Json(name = "count") val count: Int? = 12,
    @Json(name = "note") val note: String? = null,
    @Json(name = "new_moons") val new_moons: List<NewMoonItem> = emptyList()
)

@JsonClass(generateAdapter = true)
data class NewMoonItem(
    @Json(name = "lunation") val lunation: Int? = 0,
    @Json(name = "new_moon") val new_moon: NewMoonDetail? = null,
    @Json(name = "expected_crescent") val expected_crescent: ExpectedCrescentDetail? = null
)

@JsonClass(generateAdapter = true)
data class NewMoonDetail(
    @Json(name = "date") val date: String? = "",
    @Json(name = "time_utc") val time_utc: String? = "",
    @Json(name = "gregorian") val gregorian: MoonGregorianData? = null,
    @Json(name = "hijri") val hijri: MoonHijriData? = null
)

@JsonClass(generateAdapter = true)
data class ExpectedCrescentDetail(
    @Json(name = "date") val date: String? = "",
    @Json(name = "hijri_month_starting") val hijri_month_starting: String? = ""
)

