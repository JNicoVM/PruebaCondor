package com.example.appnicolas.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val teamId: String,
    val name: String,
)