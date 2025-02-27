package com.example.rickandmortyassginment.api.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyassginment.api.models.Character

@Dao
interface CharacterDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(character: List<Character>)

    @Query("Select * FROM characters WHERE id = :id")
    fun getCharacterById(id: Int): Character?


}