package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite_duas ORDER BY savedAt DESC")
    fun getAllFavoriteDuas(): Flow<List<FavoriteDuaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteDua(dua: FavoriteDuaEntity)

    @Query("DELETE FROM favorite_duas WHERE duaId = :duaId")
    suspend fun deleteFavoriteDua(duaId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_duas WHERE duaId = :duaId)")
    fun isDuaFavorite(duaId: Int): Flow<Boolean>

    @Query("SELECT * FROM favorite_hadiths ORDER BY savedAt DESC")
    fun getAllFavoriteHadiths(): Flow<List<FavoriteHadithEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteHadith(hadith: FavoriteHadithEntity)

    @Query("DELETE FROM favorite_hadiths WHERE hadithId = :hadithId")
    suspend fun deleteFavoriteHadith(hadithId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_hadiths WHERE hadithId = :hadithId)")
    fun isHadithFavorite(hadithId: String): Flow<Boolean>
}

@Dao
interface TasbihDao {
    @Query("SELECT * FROM tasbih_records ORDER BY timestamp DESC")
    fun getAllTasbihRecords(): Flow<List<TasbihEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasbihRecord(record: TasbihEntity)

    @Query("DELETE FROM tasbih_records WHERE id = :id")
    suspend fun deleteTasbihRecord(id: Int)

    @Query("DELETE FROM tasbih_records")
    suspend fun clearAllRecords()
}
