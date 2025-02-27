package com.example.rickandmortyassginment.api.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Entity(tableName = "characters")
@JsonClass(generateAdapter = true)
data class Character(
    @PrimaryKey
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    var image: String?,
    @Json(name = "name")
    var name: String?,
    @Json(name = "species")
    var species: String?,
    @Json(name = "gender")
    var gender: String?,
    @Json(name = "origin")
    var originName: String?,
    )

