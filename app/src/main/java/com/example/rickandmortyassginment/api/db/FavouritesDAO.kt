package com.example.rickandmortyassginment.api.db

import android.util.Log
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.rickandmortyassginment.api.CharactersManager
import com.example.rickandmortyassginment.api.CharactersService
import com.example.rickandmortyassginment.api.models.Character
import com.example.rickandmortyassginment.api.models.Favourites
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Dao
interface FavouritesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favourites: Favourites)

    @Delete
    fun delete(favourites: Favourites)

    @Query("DELETE FROM favourites WHERE id = :id")
    fun deleteFavouriteById(id: Int)

    @Query("SELECT name FROM favourites WHERE id = :id")
    fun getFavouriteNameById(id: Int): String

    @Query("SELECT * FROM favourites")
    fun getAllFavourites(): Flow<List<Character>>
}