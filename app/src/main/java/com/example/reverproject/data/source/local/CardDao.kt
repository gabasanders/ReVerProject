package com.example.reverproject.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface CardDao{

    @Query("SELECT * FROM card")
    fun observeAll(): Flow<List<LocalCard>>

    @Upsert
    suspend fun upsert(card: LocalCard)

    @Delete
    suspend fun delete(card: LocalCard)


}