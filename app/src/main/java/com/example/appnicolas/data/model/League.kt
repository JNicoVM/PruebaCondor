package com.example.appnicolas.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class League(
    val id: Int,
    val name: String,
    val nameQuery: String
)