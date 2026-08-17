package com.example.data.api

import com.example.data.model.AsmaUlHusnaResponse
import com.example.data.model.AsmaUlHusnaSpecificResponse
import com.example.data.model.DuasCategoriesResponse
import com.example.data.model.DuasResponse
import com.example.data.model.HadithCollectionBrowseResponse
import com.example.data.model.HadithCollectionsResponse
import com.example.data.model.HadithRandomResponse
import com.example.data.model.HadithSearchResponse
import com.example.data.model.IslamicEventsResponse
import com.example.data.model.IslamicNamesRandomResponse
import com.example.data.model.IslamicNamesResponse
import com.example.data.model.MoonPhasesResponse
import com.example.data.model.MoonSightingResponse
import com.example.data.model.PrayerTimesResponse
import com.example.data.model.QiblaResponse
import com.example.data.model.QuranAudioSurahResponse
import com.example.data.model.QuranJuzResponse
import com.example.data.model.QuranMutashabihatResponse
import com.example.data.model.QuranSurahDetailResponse
import com.example.data.model.QuranSurahsResponse
import com.example.data.model.TafsirAyahResponse
import com.example.data.model.TafsirSourcesResponse
import com.example.data.model.TafsirSurahResponse
import com.example.data.model.TodayHijriResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface UmmahApiService {

    @GET("api/prayer-times")
    suspend fun getPrayerTimes(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("date") date: String? = null,
        @Query("method") method: String? = null,
        @Query("madhab") madhab: String? = null,
        @Query("timezone") timezone: String? = null,
        @Query("apikey") apiKey: String? = null
    ): PrayerTimesResponse

    @GET("api/duas/categories")
    suspend fun getDuasCategories(
        @Query("apikey") apiKey: String? = null
    ): DuasCategoriesResponse

    @GET("api/duas")
    suspend fun getDuas(
        @Query("apikey") apiKey: String? = null
    ): DuasResponse

    @GET("api/today-hijri")
    suspend fun getTodayHijri(
        @Query("apikey") apiKey: String? = null
    ): TodayHijriResponse

    @GET("api/islamic-events")
    suspend fun getIslamicEvents(
        @Query("apikey") apiKey: String? = null
    ): IslamicEventsResponse

    @GET("api/hadith/collections")
    suspend fun getHadithCollections(
        @Query("apikey") apiKey: String? = null
    ): HadithCollectionsResponse

    @GET("api/hadith/random")
    suspend fun getRandomHadith(
        @Query("collection") collection: String? = null,
        @Query("apikey") apiKey: String? = null
    ): HadithRandomResponse

    @GET("api/hadith/search")
    suspend fun searchHadith(
        @Query("q") query: String,
        @Query("collection") collection: String? = null,
        @Query("limit") limit: Int? = 30,
        @Query("apikey") apiKey: String? = null
    ): HadithSearchResponse

    @GET("api/hadith/{collection}")
    suspend fun browseHadithCollection(
        @Path("collection") collection: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 30,
        @Query("apikey") apiKey: String? = null
    ): HadithCollectionBrowseResponse

    @GET("api/hadith/{collection}/{number}")
    suspend fun getSpecificHadith(
        @Path("collection") collection: String,
        @Path("number") number: String,
        @Query("apikey") apiKey: String? = null
    ): HadithRandomResponse

    // --- QURAN ENDPOINTS ---

    @GET("api/quran/surahs")
    suspend fun getQuranSurahs(
        @Query("apikey") apiKey: String? = null
    ): QuranSurahsResponse

    @GET("api/quran/surah/{number}")
    suspend fun getQuranSurahDetail(
        @Path("number") number: Int,
        @Query("script") script: String = "uthmani",
        @Query("translation") translation: String = "sahih_international",
        @Query("reciter") reciter: Int? = null,
        @Query("apikey") apiKey: String? = null
    ): QuranSurahDetailResponse

    @GET("api/quran/juz/{number}")
    suspend fun getQuranJuz(
        @Path("number") number: Int,
        @Query("apikey") apiKey: String? = null
    ): QuranJuzResponse

    @GET("api/quran/audio/{surah}")
    suspend fun getQuranSurahAudio(
        @Path("surah") surah: Int,
        @Query("reciter") reciter: Int? = null,
        @Query("apikey") apiKey: String? = null
    ): QuranAudioSurahResponse

    @GET("api/quran/mutashabihat/{surah}")
    suspend fun getQuranMutashabihatSurah(
        @Path("surah") surah: Int,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 30,
        @Query("apikey") apiKey: String? = null
    ): QuranMutashabihatResponse

    // --- TAFSIR ENDPOINTS ---

    @GET("api/tafsir")
    suspend fun getTafsirSources(
        @Query("apikey") apiKey: String? = null
    ): TafsirSourcesResponse

    @GET("api/tafsir/{tafsir}/surah/{surah}/ayah/{ayah}")
    suspend fun getTafsirAyah(
        @Path("tafsir") tafsirKey: String,
        @Path("surah") surah: Int,
        @Path("ayah") ayah: Int,
        @Query("apikey") apiKey: String? = null
    ): TafsirAyahResponse

    @GET("api/tafsir/{tafsir}/surah/{surah}")
    suspend fun getTafsirSurah(
        @Path("tafsir") tafsirKey: String,
        @Path("surah") surah: Int,
        @Query("apikey") apiKey: String? = null
    ): TafsirSurahResponse

    // --- QIBLA ENDPOINT ---

    @GET("api/qibla")
    suspend fun getQibla(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("apikey") apiKey: String? = null
    ): QiblaResponse

    // --- ASMA-UL-HUSNA (99 NAMES) ---

    @GET("api/asma-ul-husna")
    suspend fun getAsmaUlHusna(
        @Query("apikey") apiKey: String? = null
    ): AsmaUlHusnaResponse

    @GET("api/asma-ul-husna/{number}")
    suspend fun getAsmaUlHusnaSpecific(
        @Path("number") number: Int,
        @Query("apikey") apiKey: String? = null
    ): AsmaUlHusnaSpecificResponse

    // --- ISLAMIC NAMES ---

    @GET("api/names")
    suspend fun getIslamicNames(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 100,
        @Query("gender") gender: String? = null,
        @Query("origin") origin: String? = null,
        @Query("apikey") apiKey: String? = null
    ): IslamicNamesResponse

    @GET("api/names/random")
    suspend fun getRandomIslamicName(
        @Query("gender") gender: String? = null,
        @Query("apikey") apiKey: String? = null
    ): IslamicNamesRandomResponse

    // --- MOON SIGHTING & LUNAR PHASES ---

    @GET("api/moon")
    suspend fun getMoonSighting(
        @Query("date") date: String? = null,
        @Query("apikey") apiKey: String? = null
    ): MoonSightingResponse

    @GET("api/moon/phases")
    suspend fun getMoonPhases(
        @Query("count") count: Int = 12,
        @Query("apikey") apiKey: String? = null
    ): MoonPhasesResponse
}

object ApiClient {
    private const val BASE_URL = "https://ummahapi.com/"
    const val DEFAULT_API_KEY = "umh_c45f3e3c845a39040471ae03b59bc684c74c3f73"

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val authHeaderInterceptor = Interceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("Accept", "application/json")
            .header("X-API-Key", DEFAULT_API_KEY)
        val request = requestBuilder.build()
        chain.proceed(request)
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authHeaderInterceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    val apiService: UmmahApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(UmmahApiService::class.java)
    }
}
