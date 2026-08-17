package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.IslamicEventsData
import com.example.data.model.TodayHijriData
import com.example.ui.IslamicViewModel
import com.example.ui.components.SectionHeader
import com.example.ui.theme.EmeraldAccent
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.IslamicEmeraldPrimary
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldLight

@Composable
fun EventsScreen(
    viewModel: IslamicViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    EventsScreen(
        todayHijri = uiState.todayHijri,
        eventsData = uiState.islamicEvents,
        isLoading = uiState.isEventsLoading,
        lang = uiState.selectedLanguage
    )
}

@Composable
fun EventsScreen(
    todayHijri: TodayHijriData?,
    eventsData: IslamicEventsData?,
    isLoading: Boolean,
    lang: com.example.util.AppLanguage = com.example.util.AppLanguage.PORTUGUESE
) {
    val hijri = todayHijri?.hijri
    val gregorian = todayHijri?.gregorian
    val nextEvent = eventsData?.next_event
    val eventsList = eventsData?.events ?: emptyList()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("events_screen"),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(6.dp))
            // Current Hijri Date Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = when (lang) {
                            com.example.util.AppLanguage.PORTUGUESE -> "CALENDÁRIO HIJRI SAGRADO"
                            com.example.util.AppLanguage.SPANISH -> "CALENDARIO HIJRI SAGRADO"
                            com.example.util.AppLanguage.FRENCH -> "CALENDRIER HIJRI SACRÉ"
                            com.example.util.AppLanguage.ARABIC -> "التقويم الهجري الشريف"
                            com.example.util.AppLanguage.ENGLISH -> "SACRED HIJRI CALENDAR"
                        },
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 1.2.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    val displayHijri = com.example.util.TranslationHelper.translateHijriFormatted(hijri?.formatted, lang).ifEmpty { "01 Rabi' al-Awwal 1448 Hegira (AH)" }
                    Text(
                        text = displayHijri,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )

                    if (!hijri?.month_name_arabic.isNullOrBlank()) {
                        Text(
                            text = hijri?.month_name_arabic ?: "",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = androidx.compose.ui.text.font.FontFamily.Serif,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }

                    val displayGregorian = com.example.util.TranslationHelper.translateGregorianDate(gregorian?.formatted, lang).ifEmpty { "Sexta-feira, 14 de Agosto de 2026" }
                    Text(
                        text = displayGregorian,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // Next Upcoming Event
        if (nextEvent != null) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = RoundedCornerShape(18.dp),
                    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
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
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column {
                            Surface(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = when (lang) {
                                        com.example.util.AppLanguage.PORTUGUESE -> "PRÓXIMO GRANDE EVENTO"
                                        com.example.util.AppLanguage.SPANISH -> "PRÓXIMO GRAN EVENTO"
                                        com.example.util.AppLanguage.FRENCH -> "PROCHAIN GRAND ÉVÉNEMENT"
                                        com.example.util.AppLanguage.ARABIC -> "الحدث القادم المبارك"
                                        com.example.util.AppLanguage.ENGLISH -> "NEXT MAJOR EVENT"
                                    },
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            val localizedNextEventName = com.example.util.TranslationHelper.translateEventName(nextEvent.name, lang)
                            val localizedNextEventDate = com.example.util.TranslationHelper.translateHijriFormatted(nextEvent.hijri_date, lang)
                            Text(
                                text = localizedNextEventName,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = localizedNextEventDate,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }

        // Major Islamic Events List
        item {
            SectionHeader(
                title = when (lang) {
                    com.example.util.AppLanguage.PORTUGUESE -> "Grandes Eventos & Datas Sagradas"
                    com.example.util.AppLanguage.SPANISH -> "Grandes Eventos y Fechas Sagradas"
                    com.example.util.AppLanguage.FRENCH -> "Grands Événements & Dates Sacrées"
                    com.example.util.AppLanguage.ARABIC -> "المناسبات والشهور والأيام المباركة"
                    com.example.util.AppLanguage.ENGLISH -> "Major Islamic Events & Sacred Dates"
                },
                subtitle = when (lang) {
                    com.example.util.AppLanguage.PORTUGUESE -> "Celebrações e observâncias anuais"
                    com.example.util.AppLanguage.SPANISH -> "Celebraciones y observancias anuales"
                    com.example.util.AppLanguage.FRENCH -> "Célébrations et observances annuelles"
                    com.example.util.AppLanguage.ARABIC -> "الاحتفالات والمناسبات الدينية السنوية"
                    com.example.util.AppLanguage.ENGLISH -> "Annual celebrations and sacred observances"
                },
                modifier = Modifier.padding(horizontal = 0.dp)
            )
        }

        items(eventsList) { event ->
            val monthName = com.example.util.TranslationHelper.translateHijriMonthName(event.month, lang).substringBefore(" (")

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    // Date badge
                    Surface(
                        modifier = Modifier.size(54.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "${event.day}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = monthName.take(3).uppercase(),
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        val localizedEventName = com.example.util.TranslationHelper.translateEventName(event.name, lang)
                        val localizedEventDesc = com.example.util.TranslationHelper.translateEventDescription(event.description, lang)
                        Text(
                            text = localizedEventName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        val dayMonthText = when (lang) {
                            com.example.util.AppLanguage.ENGLISH -> "$monthName ${event.day}"
                            com.example.util.AppLanguage.FRENCH -> "${event.day} $monthName"
                            com.example.util.AppLanguage.ARABIC -> "${event.day} $monthName"
                            else -> "${event.day} de $monthName"
                        }
                        Text(
                            text = dayMonthText,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = localizedEventDesc,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
