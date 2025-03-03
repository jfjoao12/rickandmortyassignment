package com.example.rickandmortyassginment.api.db

import android.util.Log
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyassginment.api.CharactersManager
import com.example.rickandmortyassginment.api.CharactersService
import com.example.rickandmortyassginment.api.models.Character
import com.example.rickandmortyassginment.api.models.Favourites

@Dao
interface FavouritesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favourite: Favourites)

    @Query("SELECT name FROM favourites WHERE id = :id")
    fun getFavouriteNameById(id: Int): String

    @Query("DELETE FROM favourites WHERE id = :id")
    fun deleteFavouriteById(id: Int)
}