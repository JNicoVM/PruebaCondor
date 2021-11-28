package com.example.appnicolas.dao

import androidx.room.*
import com.example.appnicolas.data.entities.Favorite

@Dao
interface FavoriteDao {

    @Query(value = "SELECT * FROM favorites")
    suspend fun getAll(): List<Favorite>

    @Query(value = "SELECT * FROM favorites WHERE id = :id")
    suspend fun getById(id:Int): Favorite

    @Update
    suspend fun update(league:Favorite)

    @Insert
    suspend fun insert(leagues: List<Favorite>)

    @Delete
    suspend fun delete(league: List<Favorite>)
}