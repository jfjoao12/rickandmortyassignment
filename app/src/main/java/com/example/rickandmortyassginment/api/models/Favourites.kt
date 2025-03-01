package com.example.rickandmortyassginment.api.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Entity(tableName = "favourites")
@JsonClass(generateAdapter = true)
data class Favourites (
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    @Json(name = "image")
    var image: String?,
    @Json(name = "name")
    var name: String?,
    @Json(name = "species")
    var species: String?,
    @Json(name = "gender")
    var gender: String?,
    @Json(name = "origin")
    var origin: Origin?
)