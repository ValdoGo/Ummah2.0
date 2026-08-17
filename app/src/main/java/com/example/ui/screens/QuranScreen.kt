package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AyahItem
import com.example.data.model.MutashabihVerseItem
import com.example.data.model.QuranSurahDetailData
import com.example.data.model.SurahMeta
import com.example.ui.IslamicViewModel
import com.example.ui.QuranViewMode
import com.example.util.PlaybackState

@Composable
fun QuranScreen(
    viewModel: IslamicViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val playbackState by viewModel.playbackState.collectAsState()
    val currentTrack by viewModel.currentTrack.collectAsState()
    val context = LocalContext.current

    val goldAccent = MaterialTheme.colorScheme.primary
    val emeraldAccent = Color(0xFF10B981)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top Navigation Bar
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 2.dp,
                shadowElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            if (uiState.selectedSurahDetail != null) {
                                viewModel.closeSurah()
                            } else if (uiState.selectedJuzData != null) {
                                viewModel.closeJuz()
                            } else {
                                onBack()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = goldAccent
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = when {
                                uiState.selectedSurahDetail != null -> {
                                    "${uiState.selectedSurahDetail?.surah?.number}. ${uiState.selectedSurahDetail?.surah?.name_english} (${uiState.selectedSurahDetail?.surah?.name_arabic})"
                                }
                                uiState.selectedJuzData != null -> "Juz ${uiState.selectedJuzData?.juz_number}"
                                else -> "Al-Qur'an Al-Kareem"
                            },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = when {
                                uiState.selectedSurahDetail != null -> "${uiState.selectedSurahDetail?.total_verses} versículos • ${uiState.selectedSurahDetail?.surah?.revelation_place?.replaceFirstChar { it.uppercase() }}"
                                uiState.selectedJuzData != null -> "${uiState.selectedJuzData?.total_verses} versículos"
                                else -> "Texto Sagrado e Áudio MP3"
                            },
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Reciter selector button
                    IconButton(
                        onClick = { viewModel.showReciterPicker(true) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Headphones,
                            contentDescription = "Selecionar Recitador",
                            tint = goldAccent
                        )
                    }
                }
            }

            // Body Content: Surah View or List View
            if (uiState.selectedSurahDetail != null) {
                SurahReaderView(
                    detail = uiState.selectedSurahDetail!!,
                    viewModel = viewModel,
                    playbackState = playbackState,
                    currentTrack = currentTrack,
                    mutashabihatList = uiState.selectedMutashabihat,
                    onOpenMutashabihat = { item -> viewModel.showMutashabihatDialog(item) }
                )
            } else if (uiState.selectedJuzData != null) {
                JuzReaderView(
                    juzData = uiState.selectedJuzData!!,
                    viewModel = viewModel,
                    playbackState = playbackState,
                    currentTrack = currentTrack
                )
            } else {
                // Surah & Juz directory list
                SurahDirectoryView(viewModel = viewModel)
            }
        }

        // Dialog for Reciter Selection
        if (uiState.showReciterDialog) {
            ReciterPickerDialog(
                currentReciterId = uiState.selectedReciterId,
                reciters = viewModel.popularReciters,
                onSelect = { reciterId -> viewModel.selectQuranReciter(reciterId) },
                onDismiss = { viewModel.showReciterPicker(false) }
            )
        }

        // Dialog for Mutashabihat Comparison
        if (uiState.showMutashabihatDialogForAyah != null) {
            MutashabihatComparisonDialog(
                item = uiState.showMutashabihatDialogForAyah!!,
                onDismiss = { viewModel.showMutashabihatDialog(null) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SurahDirectoryView(
    viewModel: IslamicViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val goldAccent = MaterialTheme.colorScheme.primary
    var isSearchExpanded by remember { mutableStateOf(uiState.quranSearchQuery.isNotEmpty()) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Tab row with compact Search Icon (Lupa)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SecondaryTabRow(
                selectedTabIndex = if (uiState.quranViewMode == QuranViewMode.SURAHS) 0 else 1,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = goldAccent,
                modifier = Modifier.weight(1f)
            ) {
                Tab(
                    selected = uiState.quranViewMode == QuranViewMode.SURAHS,
                    onClick = { viewModel.setQuranViewMode(QuranViewMode.SURAHS) },
                    text = { Text("Surahs (114)", fontWeight = FontWeight.SemiBold) },
                    selectedContentColor = goldAccent,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Tab(
                    selected = uiState.quranViewMode == QuranViewMode.JUZ,
                    onClick = { viewModel.setQuranViewMode(QuranViewMode.JUZ) },
                    text = { Text("Juz (30)", fontWeight = FontWeight.SemiBold) },
                    selectedContentColor = goldAccent,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (uiState.quranViewMode == QuranViewMode.SURAHS) {
                IconButton(
                    onClick = {
                        isSearchExpanded = !isSearchExpanded
                        if (!isSearchExpanded) {
                            viewModel.updateQuranSearchQuery("")
                        }
                    },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Icon(
                        imageVector = if (isSearchExpanded) Icons.Default.Close else Icons.Default.Search,
                        contentDescription = if (isSearchExpanded) "Fechar Busca" else "Buscar Surah",
                        tint = if (isSearchExpanded || uiState.quranSearchQuery.isNotEmpty()) goldAccent else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Expandable Search bar (collapsible lupa)
        AnimatedVisibility(
            visible = uiState.quranViewMode == QuranViewMode.SURAHS && isSearchExpanded
        ) {
            OutlinedTextField(
                value = uiState.quranSearchQuery,
                onValueChange = { viewModel.updateQuranSearchQuery(it) },
                placeholder = { Text("Buscar por nome, tradução ou número...", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = goldAccent) },
                trailingIcon = {
                    if (uiState.quranSearchQuery.isNotEmpty()) {
                        IconButton(onClick = { viewModel.updateQuranSearchQuery("") }) {
                            Icon(Icons.Default.Close, contentDescription = "Limpar", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = goldAccent,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                ),
                singleLine = true
            )
        }

        if (uiState.isQuranLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = goldAccent)
            }
        } else if (uiState.quranViewMode == QuranViewMode.SURAHS) {
            val query = uiState.quranSearchQuery.trim().lowercase()
            val lang = uiState.selectedLanguage
            val filteredSurahs = remember(uiState.quranSurahs, query, lang) {
                if (query.isEmpty()) {
                    uiState.quranSurahs
                } else {
                    uiState.quranSurahs.filter {
                        val translatedName = com.example.util.TranslationHelper.getSurahNameTranslation(it.number, it.name_translation, lang)
                        it.name_english.lowercase().contains(query) ||
                                (it.name_translation?.lowercase()?.contains(query) == true) ||
                                translatedName.lowercase().contains(query) ||
                                it.name_arabic.contains(query) ||
                                it.number.toString() == query
                    }
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filteredSurahs, key = { it.number }) { surah ->
                    SurahListItemCard(
                        surah = surah,
                        lang = lang,
                        onClick = { viewModel.openSurah(surah.number) }
                    )
                }
                item { Spacer(modifier = Modifier.height(30.dp)) }
            }
        } else {
            // Juz list 1..30
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items((1..30).toList()) { juzNum ->
                    JuzListItemCard(
                        juzNumber = juzNum,
                        onClick = { viewModel.openJuz(juzNum) }
                    )
                }
                item { Spacer(modifier = Modifier.height(30.dp)) }
            }
        }
    }
}

@Composable
private fun SurahListItemCard(
    surah: SurahMeta,
    lang: com.example.util.AppLanguage,
    onClick: () -> Unit
) {
    val goldAccent = MaterialTheme.colorScheme.primary
    val translatedName = com.example.util.TranslationHelper.getSurahNameTranslation(surah.number, surah.name_translation, lang)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Surah Number Badge
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = surah.number.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            // English & Translation
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = surah.name_english,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "$translatedName • ${surah.verses_count} versículos",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = surah.revelation_place.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.labelSmall,
                    color = if (surah.revelation_place.lowercase() == "makkah") Color(0xFF2563EB) else Color(0xFF059669),
                    fontWeight = FontWeight.Medium
                )
            }

            // Arabic Calligraphy Name
            Text(
                text = surah.name_arabic,
                style = MaterialTheme.typography.headlineSmall,
                color = goldAccent,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
private fun JuzListItemCard(
    juzNumber: Int,
    onClick: () -> Unit
) {
    val goldAccent = MaterialTheme.colorScheme.primary

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = juzNumber.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Juz $juzNumber",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Parte $juzNumber de 30 do Nobre Alcorão",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.MenuBook,
                contentDescription = null,
                tint = goldAccent
            )
        }
    }
}

@Composable
private fun SurahReaderView(
    detail: QuranSurahDetailData,
    viewModel: IslamicViewModel,
    playbackState: PlaybackState,
    currentTrack: com.example.util.AudioTrackInfo?,
    mutashabihatList: List<MutashabihVerseItem>,
    onOpenMutashabihat: (MutashabihVerseItem) -> Unit
) {
    val goldAccent = MaterialTheme.colorScheme.primary
    val emeraldAccent = Color(0xFF10B981)

    val surah = detail.surah
    val surahNumber = surah?.number ?: 1
    val surahNameArabic = surah?.name_arabic ?: "القرآن"
    val surahNameEnglish = surah?.name_english ?: "Surah $surahNumber"
    val lang = viewModel.uiState.collectAsState().value.selectedLanguage
    val surahTranslation = com.example.util.TranslationHelper.getSurahNameTranslation(surahNumber, surah?.name_translation, lang)
    val revelationPlace = surah?.revelation_place ?: "makkah"
    val versesCount = surah?.verses_count ?: detail.verses.size

    val selectedReciterId = viewModel.uiState.collectAsState().value.selectedReciterId
    val selectedReciterObj = viewModel.popularReciters.firstOrNull { it.id == selectedReciterId }
    val reciterName = selectedReciterObj?.name ?: "Mishary Alafasy"

    val pad = surahNumber.toString().padStart(3, '0')
    val matchingReciterAudio = detail.audio.firstOrNull { it.reciter_id == selectedReciterId }
    val surahAudioUrl = matchingReciterAudio?.surah_audio
        ?: detail.audio.firstOrNull { it.reciter.contains(reciterName, ignoreCase = true) }?.surah_audio
        ?: when (selectedReciterId) {
            1 -> "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/$pad.mp3"
            2 -> "https://download.quranicaudio.com/quran/abdurrahmaan_as-sudais/$pad.mp3"
            3 -> "https://download.quranicaudio.com/quran/abdul_basit_murattal/$pad.mp3"
            4 -> "https://download.quranicaudio.com/quran/sa3d_al-ghaamidi/complete/$pad.mp3"
            5 -> "https://download.quranicaudio.com/quran/abu_bakr_ash-shaatree/$pad.mp3"
            6 -> "https://download.quranicaudio.com/quran/mahmood_khaleel_al-husaree_iza3ah/$pad.mp3"
            7 -> "https://download.quranicaudio.com/quran/ali_alhuthaify/$pad.mp3"
            8 -> "https://download.quranicaudio.com/quran/mahir_al-mu3ayqlee/link/$pad.mp3"
            else -> detail.audio.firstOrNull()?.surah_audio ?: "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/$pad.mp3"
        }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Surah Header Banner
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                shape = RoundedCornerShape(18.dp),
                border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = surahNameArabic,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = goldAccent,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (surahTranslation.isNotEmpty()) "$surahNameEnglish ($surahTranslation)" else surahNameEnglish,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "${revelationPlace.replaceFirstChar { it.uppercase() }} • $versesCount Versículos • $reciterName",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Audio controls row for full surah
                    if (!surahAudioUrl.isNullOrEmpty()) {
                        val isPlayingThisSurah = currentTrack?.id == "surah_${surahNumber}_$selectedReciterId" && playbackState == PlaybackState.PLAYING
                        val isBuffering = currentTrack?.id == "surah_${surahNumber}_$selectedReciterId" && playbackState == PlaybackState.BUFFERING

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Card(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(24.dp))
                                    .clickable {
                                        viewModel.playAudio(
                                            url = surahAudioUrl,
                                            title = "Surah $surahNameEnglish",
                                            subtitle = "Recitação ($reciterName)",
                                            id = "surah_${surahNumber}_$selectedReciterId"
                                        )
                                    },
                                colors = CardDefaults.cardColors(containerColor = if (isPlayingThisSurah) emeraldAccent else goldAccent)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    if (isBuffering) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.size(18.dp),
                                            color = Color.White,
                                            strokeWidth = 2.dp
                                        )
                                    } else {
                                        Icon(
                                            imageVector = if (isPlayingThisSurah) Icons.Default.Pause else Icons.Default.PlayArrow,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = if (isPlayingThisSurah) "Pausar Áudio" else "Ouvir Surah Completa",
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            }

                            // Download Button
                            IconButton(
                                onClick = {
                                    viewModel.downloadAudio(
                                        url = surahAudioUrl,
                                        fileName = "Surah_${surahNumber}_$surahNameEnglish",
                                        title = "Surah $surahNameEnglish"
                                    )
                                },
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape)
                                    .size(40.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Download,
                                    contentDescription = "Baixar Áudio",
                                    tint = goldAccent,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }

                    // Bismillah pre if not Surah 9
                    if (surahNumber != 9 && surahNumber != 1) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "بِسْمِ ٱللَّهِ ٱلرَّحْمَـٰنِ ٱلرَّحِيمِ",
                            style = MaterialTheme.typography.titleLarge,
                            color = goldAccent,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        // Verses list
        items(detail.verses, key = { it.verse_key }) { ayah ->
            val matchingMutashabih = mutashabihatList.find { it.ayah == ayah.ayah || it.verse_key == ayah.verse_key }
            AyahItemCard(
                ayah = ayah,
                viewModel = viewModel,
                playbackState = playbackState,
                currentTrack = currentTrack,
                matchingMutashabih = matchingMutashabih,
                onOpenMutashabihat = { if (matchingMutashabih != null) onOpenMutashabihat(matchingMutashabih) }
            )
        }

        item { Spacer(modifier = Modifier.height(40.dp)) }
    }
}

@Composable
private fun JuzReaderView(
    juzData: com.example.data.model.QuranJuzData,
    viewModel: IslamicViewModel,
    playbackState: PlaybackState,
    currentTrack: com.example.util.AudioTrackInfo?
) {
    val goldAccent = MaterialTheme.colorScheme.primary

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(18.dp),
                border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "الجزء ${juzData.juz_number}",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = goldAccent
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Juz ${juzData.juz_number}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "${juzData.total_verses} versículos",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        items(juzData.verses, key = { it.verse_key }) { ayah ->
            AyahItemCard(
                ayah = ayah,
                viewModel = viewModel,
                playbackState = playbackState,
                currentTrack = currentTrack,
                matchingMutashabih = null,
                onOpenMutashabihat = {}
            )
        }

        item { Spacer(modifier = Modifier.height(40.dp)) }
    }
}

@Composable
private fun AyahItemCard(
    ayah: AyahItem,
    viewModel: IslamicViewModel,
    playbackState: PlaybackState,
    currentTrack: com.example.util.AudioTrackInfo?,
    matchingMutashabih: MutashabihVerseItem?,
    onOpenMutashabihat: () -> Unit
) {
    val goldAccent = MaterialTheme.colorScheme.primary
    val context = LocalContext.current

    val isPlayingThisAyah = currentTrack?.id == "ayah_${ayah.verse_key}" && playbackState == PlaybackState.PLAYING
    val isBuffering = currentTrack?.id == "ayah_${ayah.verse_key}" && playbackState == PlaybackState.BUFFERING
    val uiState by viewModel.uiState.collectAsState()
    val lang = uiState.selectedLanguage
    val localizedTranslation = com.example.util.TranslationHelper.getAyahTranslation(ayah, lang)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Verse Header: key badge + actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Verse Key Badge
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = ayah.verse_key,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                // Action icons: Play Ayah, Mutashabihat badge, Copy text
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (matchingMutashabih != null) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { onOpenMutashabihat() }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CompareArrows,
                                    contentDescription = "Versículos Semelhantes",
                                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "${matchingMutashabih.similar_verses.size} Semelhantes",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    // Copy Ayah
                    IconButton(
                        onClick = {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("Ayah ${ayah.verse_key}", "${ayah.arabic}\n\n$localizedTranslation")
                            clipboard.setPrimaryClip(clip)
                            Toast.makeText(context, "Versículo copiado!", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = "Copiar",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    // Play Ayah Audio
                    val ayahAudioUrl = ayah.audio?.ayah_audio
                    if (!ayahAudioUrl.isNullOrEmpty()) {
                        IconButton(
                            onClick = {
                                viewModel.playAudio(
                                    url = ayahAudioUrl,
                                    title = "Ayah ${ayah.verse_key}",
                                    subtitle = ayah.surah_name ?: "Alcorão",
                                    id = "ayah_${ayah.verse_key}"
                                )
                            },
                            modifier = Modifier.size(36.dp)
                        ) {
                            if (isBuffering) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(16.dp),
                                    color = goldAccent,
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Icon(
                                    imageVector = if (isPlayingThisAyah) Icons.Default.Pause else Icons.Default.VolumeUp,
                                    contentDescription = "Áudio do Versículo",
                                    tint = if (isPlayingThisAyah) goldAccent else MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Arabic Text
            Text(
                text = ayah.arabic,
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 24.sp, lineHeight = 42.sp),
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )

            // Transliteration
            if (!ayah.transliteration.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = ayah.transliteration,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Translation (Localized)
            if (localizedTranslation.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = localizedTranslation,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun ReciterPickerDialog(
    currentReciterId: Int,
    reciters: List<com.example.data.model.ReciterInfo>,
    onSelect: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    val goldAccent = MaterialTheme.colorScheme.primary

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surface,
        title = {
            Text("Selecione o Recitador (Qari)", color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold)
        },
        text = {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(reciters) { reciter ->
                    val isSelected = reciter.id == currentReciterId
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { onSelect(reciter.id) },
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = reciter.name,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "${reciter.name_arabic ?: ""} • ${reciter.style ?: "Murattal"}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = null,
                                    tint = goldAccent
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Fechar", color = goldAccent)
            }
        }
    )
}

@Composable
private fun MutashabihatComparisonDialog(
    item: MutashabihVerseItem,
    onDismiss: () -> Unit
) {
    val goldAccent = MaterialTheme.colorScheme.primary
    val purpleAccent = MaterialTheme.colorScheme.secondary

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surface,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CompareArrows, contentDescription = null, tint = purpleAccent)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Mutashabihat (${item.verse_key})", color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold)
            }
        },
        text = {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        text = "Versículo Base (${item.surah_name_english ?: "Surah ${item.surah}"}):",
                        style = MaterialTheme.typography.labelLarge,
                        color = goldAccent,
                        fontWeight = FontWeight.Bold
                    )
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = item.arabic,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth()
                            )
                            if (!item.translation.isNullOrEmpty()) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = item.translation,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                item {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Versículos Semelhantes (${item.similar_verses.size}):",
                        style = MaterialTheme.typography.labelLarge,
                        color = purpleAccent,
                        fontWeight = FontWeight.Bold
                    )
                }

                items(item.similar_verses) { similar ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Surah ${similar.surah_name_english ?: "${similar.surah}"} [${similar.verse_key}]",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = purpleAccent,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = similar.surah_name_arabic ?: "",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = goldAccent
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = similar.arabic,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth()
                            )
                            if (!similar.translation.isNullOrEmpty()) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = similar.translation,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Entendido", color = goldAccent)
            }
        }
    )
}
