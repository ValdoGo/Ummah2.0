package com.example.ui

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.FavoriteDuaEntity
import com.example.data.local.FavoriteHadithEntity
import com.example.data.local.TasbihEntity
import com.example.data.model.AsmaNameItem
import com.example.data.model.AsmaUlHusnaData
import com.example.data.model.AyahItem
import com.example.data.model.DuaCategory
import com.example.data.model.DuaItem
import com.example.data.model.HadithCollectionInfo
import com.example.data.model.HadithItem
import com.example.data.model.IslamicEventsData
import com.example.data.model.IslamicNameItem
import com.example.data.model.MoonDetailData
import com.example.data.model.MoonSightingData
import com.example.data.model.MutashabihVerseItem
import com.example.data.model.NewMoonItem
import com.example.data.model.PrayerTimesData
import com.example.data.model.QiblaData
import com.example.data.model.QuranJuzData
import com.example.data.model.QuranSurahDetailData
import com.example.data.model.SurahMeta
import com.example.data.model.TafsirDetailItem
import com.example.data.model.TafsirSourceItem
import com.example.data.model.TafsirSurahData
import com.example.data.model.TodayHijriData
import com.example.data.repository.CityLocation
import com.example.data.repository.IslamicRepository
import com.example.data.repository.QuranDataStore
import com.example.util.AppLanguage
import com.example.util.AudioPlayerManager
import com.example.util.AudioTrackInfo
import com.example.util.PlaybackState
import com.example.util.LocationHelper
import com.example.util.AdhanOfflineManager
import com.example.util.AdhanAlarmScheduler
import com.example.util.AdhanAudioData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class NavTab(val title: String) {
    PRAYER("Salat"),
    QURAN("Qur'an"),
    TASBIH("Tasbih"),
    CALENDAR("Calendário"),
    SETTINGS("Ajustes")
}

enum class GeneralSubScreen {
    HUB,
    QURAN,
    TASBIH,
    EVENTS,
    TAFSIR,
    QIBLA,
    ASMA_UL_HUSNA,
    ISLAMIC_NAMES,
    MOON_CALENDAR,
    SETTINGS
}

enum class QuranViewMode {
    SURAHS,
    JUZ
}

data class DhikrPreset(
    val title: String,
    val arabic: String,
    val transliteration: String,
    val translation: String,
    val defaultTarget: Int = 33
)

data class IslamicUiState(
    val selectedTab: NavTab = NavTab.PRAYER,
    val generalSubScreen: GeneralSubScreen = GeneralSubScreen.HUB,
    val selectedCity: CityLocation = CityLocation("São Paulo", "Brasil", -23.5505, -46.6333, "America/Sao_Paulo"),
    val calculationMethod: String = "MuslimWorldLeague",
    val madhab: String = "Shafi",
    val customPrayerOverrides: Map<String, String> = emptyMap(),

    // Theme, Language & App Settings
    val isDarkMode: Boolean = true,
    val selectedLanguage: AppLanguage = AppLanguage.PORTUGUESE,
    val isAdhanEnabled: Boolean = true,
    val adhanAdvanceMinutes: Int = 0, // 0 (exact time), 5, 10, 15, 30 min before
    val adhanSound: String = "adhan_mecca",
    
    // Prayer
    val prayerTimes: PrayerTimesData? = null,
    val isPrayerLoading: Boolean = false,
    
    // Duas
    val categories: List<DuaCategory> = emptyList(),
    val selectedCategory: String = "all",
    val duas: List<DuaItem> = emptyList(),
    val duaSearchQuery: String = "",
    val isDuasLoading: Boolean = false,
    val duaTapProgress: Map<Int, Int> = emptyMap(),
    
    // Hadith
    val hadithCollections: List<HadithCollectionInfo> = emptyList(),
    val selectedCollectionKey: String? = null,
    val randomHadith: HadithItem? = null,
    val hadithSearchQuery: String = "",
    val searchResults: List<HadithItem> = emptyList(),
    val browseHadiths: List<HadithItem> = emptyList(),
    val browsePage: Int = 1,
    val isHadithLoading: Boolean = false,
    val isBrowsingCollection: Boolean = false,
    
    // Events & Hijri
    val todayHijri: TodayHijriData? = null,
    val islamicEvents: IslamicEventsData? = null,
    val isEventsLoading: Boolean = false,
    
    // Tasbih
    val selectedDhikrIndex: Int = 0,
    val tasbihCount: Int = 0,
    val tasbihTarget: Int = 33,
    val sessionTotalCount: Int = 0,

    // Quran
    val quranSurahs: List<SurahMeta> = emptyList(),
    val quranSearchQuery: String = "",
    val quranViewMode: QuranViewMode = QuranViewMode.SURAHS,
    val selectedSurahNumber: Int? = null,
    val selectedSurahDetail: QuranSurahDetailData? = null,
    val selectedReciterId: Int = 1,
    val selectedJuzNumber: Int? = null,
    val selectedJuzData: QuranJuzData? = null,
    val selectedMutashabihat: List<MutashabihVerseItem> = emptyList(),
    val isQuranLoading: Boolean = false,
    val isMutashabihatLoading: Boolean = false,
    val showMutashabihatDialogForAyah: MutashabihVerseItem? = null,
    val showReciterDialog: Boolean = false,

    // Tafsir
    val tafsirSources: List<TafsirSourceItem> = emptyList(),
    val selectedTafsirKey: String = "ibn_kathir",
    val selectedTafsirSurah: Int = 1,
    val selectedTafsirAyah: Int = 1,
    val currentAyahTafsir: TafsirDetailItem? = null,
    val currentSurahTafsir: TafsirSurahData? = null,
    val isTafsirLoading: Boolean = false,

    // Qibla
    val qiblaData: QiblaData? = null,
    val compassHeading: Float = 0f,
    val isQiblaLoading: Boolean = false,

    // 99 Names of Allah (Asma-ul-Husna)
    val asmaUlHusna: AsmaUlHusnaData? = null,
    val asmaSearchQuery: String = "",
    val selectedAsmaName: AsmaNameItem? = null,
    val isAsmaLoading: Boolean = false,

    // Islamic Names Dictionary
    val islamicNames: List<IslamicNameItem> = emptyList(),
    val selectedGenderFilter: String? = null,
    val namesSearchQuery: String = "",
    val randomIslamicName: IslamicNameItem? = null,
    val isNamesLoading: Boolean = false,

    // Moon Sighting & Phases
    val moonSighting: MoonSightingData? = null,
    val moonPhases: List<NewMoonItem> = emptyList(),
    val isMoonLoading: Boolean = false,
    
    // UI Dialogs
    val showCityDialog: Boolean = false,
    val showFavoritesDialog: Boolean = false,
    val showMethodDialog: Boolean = false,
    val showDuaDetailDialog: DuaItem? = null,
    val showHadithDetailDialog: HadithItem? = null
)

class IslamicViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getDatabase(application)
    private val repository = IslamicRepository(database)
    val audioPlayer = AudioPlayerManager(application)

    val popularCities = repository.popularCities
    val popularReciters = QuranDataStore.popularReciters

    val dhikrPresets = listOf(
        DhikrPreset("SubhanAllah", "سُبْحَانَ اللَّهِ", "SubhanAllah", "Glória a Allah", 33),
        DhikrPreset("Alhamdulillah", "الْحَمْدُ لِلَّهِ", "Alhamdulillah", "Louvado seja Allah", 33),
        DhikrPreset("Allahu Akbar", "اللَّهُ أَكْبَرُ", "Allahu Akbar", "Allah é o Maior", 33),
        DhikrPreset("Astaghfirullah", "أَسْتَغْفِرُ اللَّهَ", "Astaghfirullah", "Peço perdão a Allah", 100),
        DhikrPreset("La ilaha illallah", "لَا إِلَهَ إِلَّا اللَّهُ", "La ilaha illallah", "Não há divindade exceto Allah", 100),
        DhikrPreset("Salawat", "اللَّهُمَّ صَلِّ عَلَى مُحَمَّدٍ", "Allahumma salli 'ala Muhammad", "Bênçãos sobre o Profeta", 100),
        DhikrPreset("SubhanAllahi wa bihamdih", "سُبْحَانَ اللَّهِ وَبِحَمْدِهِ", "SubhanAllahi wa bihamdih", "Glória a Allah e com Seu louvor", 100),
        DhikrPreset("Hasbunallahu wa ni'mal-wakil", "حَسْبُنَا اللَّهُ وَنِعْمَ الْوَكِيلُ", "Hasbunallahu wa ni'mal-wakil", "Allah nos basta e é o melhor Protetor", 33),
        DhikrPreset("La hawla wa la quwwata", "لَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللَّهِ", "La hawla wa la quwwata illa billah", "Não há força nem poder senão com Allah", 33)
    )

    private val _uiState = MutableStateFlow(IslamicUiState())
    val uiState: StateFlow<IslamicUiState> = _uiState.asStateFlow()

    val favoriteDuas: StateFlow<List<FavoriteDuaEntity>> = repository.favoriteDuas
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val favoriteHadiths: StateFlow<List<FavoriteHadithEntity>> = repository.favoriteHadiths
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val tasbihHistory: StateFlow<List<TasbihEntity>> = repository.tasbihHistory
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val playbackState: StateFlow<PlaybackState> = audioPlayer.playbackState
    val currentTrack: StateFlow<AudioTrackInfo?> = audioPlayer.currentTrack

    init {
        loadSavedPreferences()
        loadInitialData()
        autoDetectUserLocation()
        AdhanOfflineManager.prefetchSelectedAndAllAdhans(getApplication())
    }

    private fun loadSavedPreferences() {
        try {
            val prefs = getApplication<Application>().getSharedPreferences("ummah_prefs", Context.MODE_PRIVATE)
            val isDark = prefs.getBoolean("is_dark_mode", true)
            val langStr = prefs.getString("app_language", AppLanguage.PORTUGUESE.name) ?: AppLanguage.PORTUGUESE.name
            val lang = try { AppLanguage.valueOf(langStr) } catch (_: Exception) { AppLanguage.PORTUGUESE }
            val isAdhan = prefs.getBoolean("is_adhan_enabled", true)
            val advanceMins = prefs.getInt("adhan_advance_minutes", 0)
            val adhanSoundKey = prefs.getString("adhan_sound", "adhan_mecca") ?: "adhan_mecca"

            _uiState.update {
                it.copy(
                    isDarkMode = isDark,
                    selectedLanguage = lang,
                    isAdhanEnabled = isAdhan,
                    adhanAdvanceMinutes = advanceMins,
                    adhanSound = adhanSoundKey
                )
            }
        } catch (_: Exception) {}
    }

    private fun autoDetectUserLocation() {
        viewModelScope.launch {
            try {
                val detected = LocationHelper.autoDetectLocation(getApplication())
                _uiState.update { 
                    it.copy(
                        selectedCity = detected,
                        calculationMethod = "MuslimWorldLeague"
                    ) 
                }
                fetchPrayerTimes()
            } catch (_: Exception) {}
        }
    }

    private fun loadInitialData() {
        fetchPrayerTimes()
        fetchDuasData()
        fetchHadithData()
        fetchHijriAndEvents()
        fetchQuranSurahs()
    }

    // --- NAVIGATION & DIALOGS ---
    fun selectTab(tab: NavTab) {
        _uiState.update { 
            it.copy(selectedTab = tab) 
        }
    }

    fun showCityPicker(show: Boolean) {
        _uiState.update { it.copy(showCityDialog = show) }
    }

    fun showFavoritesDialog(show: Boolean) {
        _uiState.update { it.copy(showFavoritesDialog = show) }
    }

    fun showMethodDialog(show: Boolean) {
        _uiState.update { it.copy(showMethodDialog = show) }
    }

    fun showDuaDetail(dua: DuaItem?) {
        _uiState.update { it.copy(showDuaDetailDialog = dua) }
    }

    fun showHadithDetail(hadith: HadithItem?) {
        _uiState.update { it.copy(showHadithDetailDialog = hadith) }
    }

    // --- PRAYER TIMES ---
    fun selectCity(city: CityLocation) {
        _uiState.update { it.copy(selectedCity = city, showCityDialog = false) }
        fetchPrayerTimes()
    }

    fun selectCalculationMethod(method: String, madhab: String) {
        _uiState.update { it.copy(calculationMethod = method, madhab = madhab, showMethodDialog = false) }
        fetchPrayerTimes()
    }

    fun updateCustomPrayerTime(prayerKey: String, newTime: String) {
        _uiState.update {
            val overrides = it.customPrayerOverrides.toMutableMap()
            overrides[prayerKey.lowercase()] = newTime.trim()
            it.copy(customPrayerOverrides = overrides)
        }
        rescheduleAdhanAlarms()
    }

    fun rescheduleAdhanAlarms() {
        val state = _uiState.value
        val times = state.prayerTimes?.prayer_times ?: return
        val overrides = state.customPrayerOverrides

        val prayerMap = mapOf(
            "imsak" to (overrides["imsak"] ?: times.imsak ?: "04:30"),
            "fajr" to (overrides["fajr"] ?: times.fajr ?: "05:00"),
            "sunrise" to (overrides["sunrise"] ?: times.sunrise ?: "06:15"),
            "dhuhr" to (overrides["dhuhr"] ?: times.dhuhr ?: "12:30"),
            "asr" to (overrides["asr"] ?: times.asr ?: "15:45"),
            "maghrib" to (overrides["maghrib"] ?: times.maghrib ?: "18:40"),
            "isha" to (overrides["isha"] ?: times.isha ?: "20:00")
        )

        AdhanAlarmScheduler.scheduleAlarms(
            context = getApplication(),
            prayerTimes = prayerMap,
            advanceMinutes = state.adhanAdvanceMinutes,
            selectedAdhanId = state.adhanSound,
            isAdhanEnabled = state.isAdhanEnabled
        )
    }

    fun fetchPrayerTimes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isPrayerLoading = true) }
            val city = _uiState.value.selectedCity
            val method = "MuslimWorldLeague"
            val madhab = _uiState.value.madhab
            val data = repository.getPrayerTimes(
                lat = city.latitude,
                lng = city.longitude,
                method = method,
                madhab = madhab
            )
            _uiState.update { it.copy(prayerTimes = data, isPrayerLoading = false) }
            rescheduleAdhanAlarms()
        }
    }

    // --- DUAS ---
    fun selectDuaCategory(categoryId: String) {
        _uiState.update { it.copy(selectedCategory = categoryId) }
    }

    fun updateDuaSearchQuery(query: String) {
        _uiState.update { it.copy(duaSearchQuery = query) }
    }

    fun incrementDuaCount(duaId: Int, targetRepeat: Int) {
        val current = _uiState.value.duaTapProgress[duaId] ?: 0
        val next = (current + 1)
        _uiState.update {
            val map = it.duaTapProgress.toMutableMap()
            map[duaId] = if (next > targetRepeat) 1 else next
            it.copy(duaTapProgress = map)
        }
        triggerHapticFeedback()
    }

    fun resetDuaCount(duaId: Int) {
        _uiState.update {
            val map = it.duaTapProgress.toMutableMap()
            map[duaId] = 0
            it.copy(duaTapProgress = map)
        }
    }

    fun toggleFavoriteDua(dua: DuaItem, isFavorite: Boolean) {
        viewModelScope.launch {
            repository.toggleFavoriteDua(dua, isFavorite)
        }
    }

    private fun fetchDuasData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isDuasLoading = true) }
            val cats = repository.getDuasCategories()
            val list = repository.getDuas()
            _uiState.update {
                it.copy(
                    categories = cats,
                    duas = list,
                    isDuasLoading = false
                )
            }
        }
    }

    // --- HADITH ---
    fun fetchHadithData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isHadithLoading = true) }
            val collections = repository.getHadithCollections()
            val random = repository.getRandomHadith()
            _uiState.update {
                it.copy(
                    hadithCollections = collections,
                    randomHadith = random,
                    isHadithLoading = false
                )
            }
        }
    }

    fun refreshRandomHadith(collection: String? = null) {
        viewModelScope.launch {
            _uiState.update { it.copy(isHadithLoading = true) }
            val random = repository.getRandomHadith(collection)
            _uiState.update { it.copy(randomHadith = random, isHadithLoading = false) }
        }
    }

    fun updateHadithSearchQuery(query: String) {
        _uiState.update { it.copy(hadithSearchQuery = query) }
    }

    fun searchHadiths(collection: String? = null) {
        val q = _uiState.value.hadithSearchQuery.trim()
        if (q.isEmpty()) return
        val numMatch = Regex("""\d+""").find(q)?.value
        val targetColl = collection ?: _uiState.value.selectedCollectionKey ?: "bukhari"
        viewModelScope.launch {
            _uiState.update { it.copy(isHadithLoading = true) }
            val results = mutableListOf<HadithItem>()
            if (numMatch != null) {
                val directHadith = repository.getSpecificHadith(targetColl, numMatch)
                if (directHadith != null) {
                    results.add(directHadith)
                }
            }
            val searchList = repository.searchHadith(q, collection)
            for (item in searchList) {
                if (results.none { it.id == item.id || (it.hadithnumber != null && it.hadithnumber == item.hadithnumber && it.collection == item.collection) }) {
                    results.add(item)
                }
            }
            _uiState.update {
                it.copy(
                    searchResults = results,
                    isHadithLoading = false,
                    isBrowsingCollection = false
                )
            }
        }
    }

    fun openHadithCollection(collectionKey: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    selectedCollectionKey = collectionKey,
                    isHadithLoading = true,
                    isBrowsingCollection = true,
                    browsePage = 1
                )
            }
            val list = repository.browseHadithCollection(collectionKey, page = 1)
            _uiState.update {
                it.copy(
                    browseHadiths = list,
                    isHadithLoading = false
                )
            }
        }
    }

    fun loadMoreBrowseHadiths() {
        val collection = _uiState.value.selectedCollectionKey ?: return
        val nextPage = _uiState.value.browsePage + 1
        viewModelScope.launch {
            val more = repository.browseHadithCollection(collection, page = nextPage)
            if (more.isNotEmpty()) {
                _uiState.update {
                    it.copy(
                        browseHadiths = it.browseHadiths + more,
                        browsePage = nextPage
                    )
                }
            }
        }
    }

    fun closeBrowseCollection() {
        _uiState.update { it.copy(isBrowsingCollection = false, selectedCollectionKey = null) }
    }

    fun toggleFavoriteHadith(hadith: HadithItem, isFavorite: Boolean) {
        viewModelScope.launch {
            repository.toggleFavoriteHadith(hadith, isFavorite)
        }
    }

    // --- HIJRI & EVENTS ---
    private fun fetchHijriAndEvents() {
        viewModelScope.launch {
            _uiState.update { it.copy(isEventsLoading = true) }
            val today = repository.getTodayHijri()
            val events = repository.getIslamicEvents()
            _uiState.update {
                it.copy(
                    todayHijri = today,
                    islamicEvents = events,
                    isEventsLoading = false
                )
            }
        }
    }

    // --- TASBIH ---
    fun selectDhikrPreset(index: Int) {
        if (index in dhikrPresets.indices) {
            val preset = dhikrPresets[index]
            _uiState.update {
                it.copy(
                    selectedDhikrIndex = index,
                    tasbihCount = 0,
                    tasbihTarget = preset.defaultTarget
                )
            }
        }
    }

    fun setTasbihTarget(target: Int) {
        _uiState.update { it.copy(tasbihTarget = target) }
    }

    fun incrementTasbih() {
        val state = _uiState.value
        val newCount = state.tasbihCount + 1
        val newTotal = state.sessionTotalCount + 1

        if (state.tasbihTarget > 0 && newCount >= state.tasbihTarget) {
            triggerTargetReachedHaptic()
            // Auto save record upon completing cycle
            val preset = dhikrPresets[state.selectedDhikrIndex]
            viewModelScope.launch {
                repository.saveTasbihRecord(
                    title = preset.title,
                    arabic = preset.arabic,
                    count = newCount,
                    target = state.tasbihTarget
                )
            }
            _uiState.update {
                it.copy(
                    tasbihCount = 0,
                    sessionTotalCount = newTotal
                )
            }
        } else {
            triggerHapticFeedback()
            _uiState.update {
                it.copy(
                    tasbihCount = newCount,
                    sessionTotalCount = newTotal
                )
            }
        }
    }

    fun resetTasbih() {
        val state = _uiState.value
        if (state.tasbihCount > 0) {
            val preset = dhikrPresets[state.selectedDhikrIndex]
            viewModelScope.launch {
                repository.saveTasbihRecord(
                    title = preset.title,
                    arabic = preset.arabic,
                    count = state.tasbihCount,
                    target = state.tasbihTarget
                )
            }
        }
        _uiState.update { it.copy(tasbihCount = 0) }
    }

    fun deleteTasbihRecord(id: Int) {
        viewModelScope.launch {
            repository.deleteTasbihRecord(id)
        }
    }

    fun clearAllTasbihHistory() {
        viewModelScope.launch {
            repository.clearTasbihRecords()
        }
    }

    // --- QURAN ---
    fun fetchQuranSurahs() {
        viewModelScope.launch {
            _uiState.update { it.copy(isQuranLoading = true) }
            val list = repository.getQuranSurahs()
            _uiState.update {
                it.copy(
                    quranSurahs = list,
                    isQuranLoading = false
                )
            }
        }
    }

    fun updateQuranSearchQuery(query: String) {
        _uiState.update { it.copy(quranSearchQuery = query) }
    }

    fun setQuranViewMode(mode: QuranViewMode) {
        _uiState.update { it.copy(quranViewMode = mode) }
    }

    fun openSurah(surahNumber: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    selectedSurahNumber = surahNumber,
                    isQuranLoading = true
                )
            }
            val detail = repository.getQuranSurahDetail(surahNumber, _uiState.value.selectedReciterId)
            _uiState.update {
                it.copy(
                    selectedSurahDetail = detail,
                    isQuranLoading = false
                )
            }
            fetchMutashabihat(surahNumber)
        }
    }

    fun closeSurah() {
        _uiState.update {
            it.copy(
                selectedSurahNumber = null,
                selectedSurahDetail = null,
                selectedMutashabihat = emptyList()
            )
        }
    }

    fun openJuz(juzNumber: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    selectedJuzNumber = juzNumber,
                    isQuranLoading = true
                )
            }
            val data = repository.getQuranJuz(juzNumber)
            _uiState.update {
                it.copy(
                    selectedJuzData = data,
                    isQuranLoading = false
                )
            }
        }
    }

    fun closeJuz() {
        _uiState.update { it.copy(selectedJuzNumber = null, selectedJuzData = null) }
    }

    fun selectQuranReciter(reciterId: Int) {
        _uiState.update { it.copy(selectedReciterId = reciterId, showReciterDialog = false) }
        val surahNum = _uiState.value.selectedSurahNumber
        if (surahNum != null) {
            openSurah(surahNum)
        }
    }

    fun showReciterPicker(show: Boolean) {
        _uiState.update { it.copy(showReciterDialog = show) }
    }

    fun fetchMutashabihat(surahNumber: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isMutashabihatLoading = true) }
            val list = repository.getQuranMutashabihat(surahNumber)
            _uiState.update {
                it.copy(
                    selectedMutashabihat = list,
                    isMutashabihatLoading = false
                )
            }
        }
    }

    fun showMutashabihatDialog(item: MutashabihVerseItem?) {
        _uiState.update { it.copy(showMutashabihatDialogForAyah = item) }
    }

    // ==========================================
    // SETTINGS & ADHAN
    // ==========================================

    fun toggleDarkMode() {
        val newMode = !_uiState.value.isDarkMode
        setDarkMode(newMode)
    }

    fun setDarkMode(enabled: Boolean) {
        _uiState.update { it.copy(isDarkMode = enabled) }
        val prefs = getApplication<Application>().getSharedPreferences("ummah_prefs", Context.MODE_PRIVATE)
        prefs.edit().putBoolean("is_dark_mode", enabled).apply()
    }

    fun setAdhanEnabled(enabled: Boolean) {
        _uiState.update { it.copy(isAdhanEnabled = enabled) }
        val prefs = getApplication<Application>().getSharedPreferences("ummah_prefs", Context.MODE_PRIVATE)
        prefs.edit().putBoolean("is_adhan_enabled", enabled).apply()
        rescheduleAdhanAlarms()
    }

    fun setAdhanAdvanceMinutes(minutes: Int) {
        _uiState.update { it.copy(adhanAdvanceMinutes = minutes) }
        val prefs = getApplication<Application>().getSharedPreferences("ummah_prefs", Context.MODE_PRIVATE)
        prefs.edit().putInt("adhan_advance_minutes", minutes).apply()
        rescheduleAdhanAlarms()
    }

    fun setAdhanSound(soundKey: String) {
        _uiState.update { it.copy(adhanSound = soundKey) }
        val prefs = getApplication<Application>().getSharedPreferences("ummah_prefs", Context.MODE_PRIVATE)
        prefs.edit().putString("adhan_sound", soundKey).apply()
    }

    fun setAppLanguage(lang: AppLanguage) {
        _uiState.update { it.copy(selectedLanguage = lang) }
        val prefs = getApplication<Application>().getSharedPreferences("ummah_prefs", Context.MODE_PRIVATE)
        prefs.edit().putString("app_language", lang.name).apply()
    }

    fun playAdhanTestSound() {
        val soundKey = _uiState.value.adhanSound
        val adhanInfo = AdhanAudioData.getOptionById(soundKey)
        playAudio(
            url = adhanInfo.audioUrl,
            title = "Adhan - ${adhanInfo.name}",
            subtitle = adhanInfo.origin,
            id = "adhan_demo"
        )
    }

    fun triggerTestAdhanNotificationAndAudio(prayerKey: String = "dhuhr") {
        val state = _uiState.value
        val prayerTime = state.prayerTimes?.prayer_times?.dhuhr ?: "12:30"
        AdhanAlarmScheduler.triggerTestAdhanNow(
            context = getApplication(),
            prayerKey = prayerKey,
            prayerTime = prayerTime,
            soundId = state.adhanSound
        )
    }

    fun downloadAllAdhansOffline() {
        AdhanOfflineManager.prefetchSelectedAndAllAdhans(getApplication())
    }

    // ==========================================
    // TAFSIR METHODS
    // ==========================================

    fun fetchTafsirSources() {
        viewModelScope.launch {
            val sources = repository.getTafsirSources()
            _uiState.update { it.copy(tafsirSources = sources) }
        }
    }

    fun selectTafsirKey(key: String) {
        _uiState.update { it.copy(selectedTafsirKey = key) }
        val state = _uiState.value
        loadTafsirForAyah(state.selectedTafsirSurah, state.selectedTafsirAyah)
    }

    fun loadTafsirForAyah(surah: Int, ayah: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isTafsirLoading = true, selectedTafsirSurah = surah, selectedTafsirAyah = ayah) }
            val tafsirDetail = repository.getTafsirAyah(_uiState.value.selectedTafsirKey, surah, ayah)
            _uiState.update {
                it.copy(
                    currentAyahTafsir = tafsirDetail,
                    isTafsirLoading = false
                )
            }
        }
    }

    fun loadTafsirForSurah(surah: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isTafsirLoading = true, selectedTafsirSurah = surah) }
            val surahTafsir = repository.getTafsirSurah(_uiState.value.selectedTafsirKey, surah)
            _uiState.update {
                it.copy(
                    currentSurahTafsir = surahTafsir,
                    isTafsirLoading = false
                )
            }
        }
    }

    // ==========================================
    // QIBLA METHODS
    // ==========================================

    fun fetchQibla() {
        viewModelScope.launch {
            _uiState.update { it.copy(isQiblaLoading = true) }
            val city = _uiState.value.selectedCity
            val qibla = repository.getQibla(city.latitude, city.longitude)
            _uiState.update {
                it.copy(
                    qiblaData = qibla,
                    isQiblaLoading = false
                )
            }
        }
    }

    fun updateCompassHeading(heading: Float) {
        _uiState.update { it.copy(compassHeading = heading) }
    }

    // ==========================================
    // 99 NAMES OF ALLAH (ASMA-UL-HUSNA)
    // ==========================================

    fun fetchAsmaUlHusna() {
        viewModelScope.launch {
            _uiState.update { it.copy(isAsmaLoading = true) }
            val data = repository.getAsmaUlHusna()
            _uiState.update {
                it.copy(
                    asmaUlHusna = data,
                    isAsmaLoading = false
                )
            }
        }
    }

    fun updateAsmaSearchQuery(query: String) {
        _uiState.update { it.copy(asmaSearchQuery = query) }
    }

    fun selectAsmaName(name: AsmaNameItem?) {
        _uiState.update { it.copy(selectedAsmaName = name) }
    }

    // ==========================================
    // ISLAMIC NAMES
    // ==========================================

    fun fetchIslamicNames(gender: String? = _uiState.value.selectedGenderFilter, origin: String? = null) {
        viewModelScope.launch {
            _uiState.update { it.copy(isNamesLoading = true) }
            val data = repository.getIslamicNames(gender = gender, origin = origin)
            _uiState.update {
                it.copy(
                    islamicNames = data.names,
                    isNamesLoading = false
                )
            }
        }
    }

    fun updateNamesSearchQuery(query: String) {
        _uiState.update { it.copy(namesSearchQuery = query) }
    }

    fun setGenderFilter(gender: String?) {
        _uiState.update { it.copy(selectedGenderFilter = gender) }
        fetchIslamicNames(gender = gender)
    }

    fun fetchRandomIslamicName() {
        viewModelScope.launch {
            val name = repository.getRandomIslamicName(_uiState.value.selectedGenderFilter)
            _uiState.update { it.copy(randomIslamicName = name) }
        }
    }

    // ==========================================
    // MOON SIGHTING & LUNAR PHASES
    // ==========================================

    fun fetchMoonData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isMoonLoading = true) }
            val moon = repository.getMoonSighting()
            val phases = repository.getMoonPhases(12)
            _uiState.update {
                it.copy(
                    moonSighting = moon,
                    moonPhases = phases,
                    isMoonLoading = false
                )
            }
        }
    }

    // --- AUDIO CONTROLS ---
    fun playAudio(url: String, title: String, subtitle: String = "", id: String = "") {
        audioPlayer.play(AudioTrackInfo(url, title, subtitle, id))
    }

    fun pauseAudio() {
        audioPlayer.pause()
    }

    fun resumeAudio() {
        audioPlayer.resume()
    }

    fun stopAudio() {
        audioPlayer.stop()
    }

    fun downloadAudio(url: String, fileName: String, title: String) {
        audioPlayer.downloadAudio(url, fileName, title)
    }

    override fun onCleared() {
        super.onCleared()
        audioPlayer.stop()
    }

    private fun triggerHapticFeedback() {
        try {
            val context = getApplication<Application>()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                vibratorManager?.defaultVibrator?.vibrate(
                    VibrationEffect.createOneShot(25, VibrationEffect.DEFAULT_AMPLITUDE)
                )
            } else {
                @Suppress("DEPRECATION")
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator?.vibrate(VibrationEffect.createOneShot(25, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    @Suppress("DEPRECATION")
                    vibrator?.vibrate(25)
                }
            }
        } catch (_: Exception) {}
    }

    private fun triggerTargetReachedHaptic() {
        try {
            val context = getApplication<Application>()
            val pattern = longArrayOf(0, 200, 100, 300, 100, 500)
            val amplitudes = intArrayOf(0, 255, 0, 255, 0, 255)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                val vibrator = vibratorManager?.defaultVibrator
                try {
                    vibrator?.vibrate(VibrationEffect.createWaveform(pattern, amplitudes, -1))
                } catch (_: Exception) {
                    vibrator?.vibrate(VibrationEffect.createWaveform(pattern, -1))
                }
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                @Suppress("DEPRECATION")
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
                try {
                    vibrator?.vibrate(VibrationEffect.createWaveform(pattern, amplitudes, -1))
                } catch (_: Exception) {
                    vibrator?.vibrate(VibrationEffect.createWaveform(pattern, -1))
                }
            } else {
                @Suppress("DEPRECATION")
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
                vibrator?.vibrate(pattern, -1)
            }
        } catch (_: Exception) {}
    }
}
