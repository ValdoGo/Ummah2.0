package com.example

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.IslamicViewModel
import com.example.ui.NavTab
import com.example.ui.components.CalculationMethodDialog
import com.example.ui.components.CitySelectorDialog
import com.example.ui.screens.EventsScreen
import com.example.ui.screens.PrayerTimesScreen
import com.example.ui.screens.QuranScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.TasbihScreen
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldLight
import com.example.ui.theme.MyApplicationTheme
import com.example.util.AppLanguage
import com.example.util.AppStrings

class MainActivity : ComponentActivity() {

    private val viewModel: IslamicViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val uiState by viewModel.uiState.collectAsState()

            // Request Notification Permission on Android 13+ (Tiramisu)
            val notificationPermissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) { /* granted -> notifications enabled */ }

            LaunchedEffect(Unit) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }

            MyApplicationTheme(darkTheme = uiState.isDarkMode) {
                val currentLang = uiState.selectedLanguage

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(34.dp)
                                            .clip(CircleShape)
                                            .background(MaterialTheme.colorScheme.primary),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Mosque,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.onPrimary,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            text = "Ummah",
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 18.sp,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = when (uiState.selectedTab) {
                                                NavTab.PRAYER -> AppStrings.tabPrayer(currentLang)
                                                NavTab.QURAN -> AppStrings.tabQuran(currentLang)
                                                NavTab.TASBIH -> AppStrings.tabTasbih(currentLang)
                                                NavTab.CALENDAR -> AppStrings.tabCalendar(currentLang)
                                                NavTab.SETTINGS -> AppStrings.tabSettings(currentLang)
                                            },
                                            fontSize = 11.sp,
                                            color = MaterialTheme.colorScheme.primary,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            },
                            navigationIcon = {
                                Surface(
                                    onClick = { viewModel.showCityPicker(true) },
                                    shape = RoundedCornerShape(12.dp),
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                    modifier = Modifier.padding(start = 12.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.LocationOn,
                                            contentDescription = "Cidade",
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = uiState.selectedCity.name,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            },
                            actions = {
                                // Theme Toggle Button
                                IconButton(
                                    onClick = { viewModel.toggleDarkMode() },
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                        .testTag("theme_toggle_button")
                                ) {
                                    Icon(
                                        imageVector = if (uiState.isDarkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
                                        contentDescription = if (uiState.isDarkMode) "Mudar para Modo Claro" else "Mudar para Modo Escuro",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                                titleContentColor = MaterialTheme.colorScheme.onSurface
                            )
                        )
                    },
                    bottomBar = {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.primary,
                            tonalElevation = 8.dp
                        ) {
                            // 1. SALAT
                            NavigationBarItem(
                                selected = uiState.selectedTab == NavTab.PRAYER,
                                onClick = { viewModel.selectTab(NavTab.PRAYER) },
                                icon = {
                                    Icon(
                                        Icons.Default.Mosque,
                                        contentDescription = AppStrings.tabPrayer(currentLang)
                                    )
                                },
                                label = {
                                    Text(
                                        AppStrings.tabPrayer(currentLang),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    indicatorColor = MaterialTheme.colorScheme.primary
                                ),
                                modifier = Modifier.testTag("nav_tab_prayer")
                            )

                            // 2. QUR'AN
                            NavigationBarItem(
                                selected = uiState.selectedTab == NavTab.QURAN,
                                onClick = { viewModel.selectTab(NavTab.QURAN) },
                                icon = {
                                    Icon(
                                        Icons.AutoMirrored.Filled.MenuBook,
                                        contentDescription = AppStrings.tabQuran(currentLang)
                                    )
                                },
                                label = {
                                    Text(
                                        AppStrings.tabQuran(currentLang),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    indicatorColor = MaterialTheme.colorScheme.primary
                                ),
                                modifier = Modifier.testTag("nav_tab_quran")
                            )

                            // 3. TASBIH
                            NavigationBarItem(
                                selected = uiState.selectedTab == NavTab.TASBIH,
                                onClick = { viewModel.selectTab(NavTab.TASBIH) },
                                icon = {
                                    Icon(
                                        Icons.Default.Fingerprint,
                                        contentDescription = AppStrings.tabTasbih(currentLang)
                                    )
                                },
                                label = {
                                    Text(
                                        AppStrings.tabTasbih(currentLang),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    indicatorColor = MaterialTheme.colorScheme.primary
                                ),
                                modifier = Modifier.testTag("nav_tab_tasbih")
                            )

                            // 4. CALENDAR
                            NavigationBarItem(
                                selected = uiState.selectedTab == NavTab.CALENDAR,
                                onClick = { viewModel.selectTab(NavTab.CALENDAR) },
                                icon = {
                                    Icon(
                                        Icons.Default.CalendarMonth,
                                        contentDescription = AppStrings.tabCalendar(currentLang)
                                    )
                                },
                                label = {
                                    Text(
                                        AppStrings.tabCalendar(currentLang),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    indicatorColor = MaterialTheme.colorScheme.primary
                                ),
                                modifier = Modifier.testTag("nav_tab_calendar")
                            )

                            // 5. SETTINGS
                            NavigationBarItem(
                                selected = uiState.selectedTab == NavTab.SETTINGS,
                                onClick = { viewModel.selectTab(NavTab.SETTINGS) },
                                icon = {
                                    Icon(
                                        Icons.Default.Settings,
                                        contentDescription = AppStrings.tabSettings(currentLang)
                                    )
                                },
                                label = {
                                    Text(
                                        AppStrings.tabSettings(currentLang),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    indicatorColor = MaterialTheme.colorScheme.primary
                                ),
                                modifier = Modifier.testTag("nav_tab_settings")
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        AnimatedContent(
                            targetState = uiState.selectedTab,
                            transitionSpec = {
                                fadeIn(animationSpec = tween(200)) togetherWith fadeOut(animationSpec = tween(200))
                            },
                            label = "tab_transition"
                        ) { targetTab ->
                            when (targetTab) {
                                NavTab.PRAYER -> PrayerTimesScreen(
                                    city = uiState.selectedCity,
                                    prayerData = uiState.prayerTimes,
                                    isLoading = uiState.isPrayerLoading,
                                    customOverrides = uiState.customPrayerOverrides,
                                    lang = uiState.selectedLanguage,
                                    onUpdateCustomTime = { key, time -> viewModel.updateCustomPrayerTime(key, time) },
                                    onTestAdhan = { prayerKey -> viewModel.triggerTestAdhanNotificationAndAudio(prayerKey) },
                                    onRefresh = { viewModel.fetchPrayerTimes() }
                                )

                                NavTab.QURAN -> QuranScreen(
                                    viewModel = viewModel,
                                    onBack = { viewModel.selectTab(NavTab.PRAYER) }
                                )

                                NavTab.TASBIH -> TasbihScreen(
                                    viewModel = viewModel
                                )

                                NavTab.CALENDAR -> EventsScreen(
                                    viewModel = viewModel
                                )

                                NavTab.SETTINGS -> SettingsScreen(
                                    state = uiState,
                                    viewModel = viewModel,
                                    onBack = { viewModel.selectTab(NavTab.PRAYER) }
                                )
                            }
                        }

                        // City Selector Dialog
                        if (uiState.showCityDialog) {
                            CitySelectorDialog(
                                cities = viewModel.popularCities,
                                currentCity = uiState.selectedCity,
                                onSelectCity = { viewModel.selectCity(it) },
                                onDismiss = { viewModel.showCityPicker(false) }
                            )
                        }

                        // Calculation Method Dialog
                        if (uiState.showMethodDialog) {
                            CalculationMethodDialog(
                                currentMethod = uiState.calculationMethod,
                                currentMadhab = uiState.madhab,
                                onSave = { method, madhab -> viewModel.selectCalculationMethod(method, madhab) },
                                onDismiss = { viewModel.showMethodDialog(false) }
                            )
                        }
                    }
                }
            }
        }
    }
}
