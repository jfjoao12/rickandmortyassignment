package com.example.rickandmortyassginment.api.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyassginment.api.models.Character
import com.example.rickandmortyassginment.api.models.Favourites

@Dao
interface CharacterDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(character: List<Character>)

    @Query("Select * FROM characters WHERE id = :id")
    fun getCharacterById(id: Int): Character?

    @Delete
    fun deleteFavouriteRecord(favourites: Favourites)

    @Query("Select * FROM characters")
    fun getAllCharacters(): List<Character>

}