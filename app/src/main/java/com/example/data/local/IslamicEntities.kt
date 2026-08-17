package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_duas")
data class FavoriteDuaEntity(
    @PrimaryKey val duaId: Int,
    val category: String,
    val title: String,
    val arabic: String,
    val transliteration: String,
    val translation: String,
    val source: String,
    val repeatCount: Int,
    val savedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "favorite_hadiths")
data class FavoriteHadithEntity(
    @PrimaryKey val hadithId: String,
    val collection: String,
    val collectionName: String,
    val hadithNumber: Int,
    val arabic: String,
    val english: String,
    val grade: String,
    val savedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "tasbih_records")
data class TasbihEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val dhikrTitle: String,
    val dhikrArabic: String,
    val count: Int,
    val target: Int,
    val timestamp: Long = System.currentTimeMillis()
)
