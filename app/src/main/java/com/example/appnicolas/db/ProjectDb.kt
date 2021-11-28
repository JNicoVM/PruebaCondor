package com.example.appnicolas.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appnicolas.dao.FavoriteDao
import com.example.appnicolas.data.entities.Favorite

@Database(
    entities = [Favorite::class],
    version = 1
)
abstract class ProjectDB :RoomDatabase() {

    abstract fun FavoriteDao() : FavoriteDao
}