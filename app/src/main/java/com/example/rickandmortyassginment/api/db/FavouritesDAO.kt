package com.example.rickandmortyassginment.api.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyassginment.api.models.Favourites

@Dao
interface FavouritesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favourite: Favourites)

    @Query("Select * FROM favourites WHERE id = :id")
    fun getFavouritesById(id: Int): Favourites?

    @Query("DELETE FROM favourites WHERE characterId = :id")
    fun deleteFavouritesById(id: Int): Unit

    @Delete
    public fun delete(favourite: Favourites)

}