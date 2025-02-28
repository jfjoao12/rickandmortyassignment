package com.example.rickandmortyassginment.api.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Entity(
    tableName = "favourites",
    foreignKeys = [
        ForeignKey(
            entity = Character::class,  // Reference the Character table
            parentColumns = ["id"],     // Column in Character table
            childColumns = ["characterId"], // Column in Favourites table
            onDelete = ForeignKey.CASCADE  // Delete the favourite if the character is deleted
        )
    ],
)
@JsonClass(generateAdapter = true)
data class Favourites (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val characterId: Int
)