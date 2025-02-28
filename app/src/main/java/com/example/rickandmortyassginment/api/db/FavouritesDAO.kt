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

    @Query("SELECT * FROM favourites")
    fun getAllFavourites(): List<Favourites>

    @Query("SELECT * FROM favourites WHERE characterId = :characterId")
    fun getFavouriteByCharacterId(characterId: Int): Favourites?

    @Query("DELETE FROM favourites WHERE characterId = :characterId")
    fun deleteFavouritesByCharacterId(characterId: Int)

    @Query(
        "SELECT c.name AS cname, f.id as fid"  +
        "FROM Character, Favourite" +
        "WHERE favourite.id = :characterId"
    )
    fun getCharacterNameByFavourite(characterId: Int): String?

}