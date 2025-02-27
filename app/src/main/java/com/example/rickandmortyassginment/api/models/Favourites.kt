package com.example.rickandmortyassginment.api.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Entity(tableName = "favourites")
@JsonClass(generateAdapter = true)
data class Favourites (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val characterId: Int
)