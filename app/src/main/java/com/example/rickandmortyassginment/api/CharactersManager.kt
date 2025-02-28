package com.example.rickandmortyassginment.api

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.room.Database
import com.example.rickandmortyassginment.api.db.AppDatabase
import com.example.rickandmortyassginment.api.models.Character
import com.example.rickandmortyassginment.api.models.CharactersData
import com.example.rickandmortyassginment.api.models.Favourites
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class CharactersManager(db: AppDatabase) {
    private var _characterResponse = mutableStateOf<List<Character>>(emptyList())

    val charactersResponse: MutableState<List<Character>>
        @Composable get() = remember{
            _characterResponse
        }
    init {
        getCharacters(db)
    }
    @OptIn(DelicateCoroutinesApi::class)
    private fun getCharacters(db: AppDatabase){
        val service= Api.retrofitService.getCharacters()

        service.enqueue(object : retrofit2.Callback<CharactersData>{
            override fun onResponse(
                call: Call<CharactersData>,
                response: Response<CharactersData>
            ) {
                if (response.isSuccessful) {
                    Log.i("Data", "Data is locked and loaded.")

                    _characterResponse.value = (response.body()?.results ?:
                    emptyList()) as List<Character>
                    Log.i("DataSteam", _characterResponse.value.toString())
                    
                    GlobalScope.launch{
                        saveDataToDatabase(database = db, characters = _characterResponse.value)
                    }
                }
            }

            override fun onFailure(
                call: Call<CharactersData>,
                t: Throwable) {
                Log.d("error", "${t.message}")


            }

        })
    }

    private suspend fun saveDataToDatabase(database: AppDatabase, characters: List<Character>){
        database.characterDao().insertAll(characters)
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun addFavourite(database: AppDatabase, character: Character) {
        val favourite = Favourites(characterId = character.id)
        GlobalScope.launch {
            database.favouritesDao().insert(favourite)
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    fun deleteFavourite(database: AppDatabase, character: Character) {
        GlobalScope.launch {
            val favourite = database.favouritesDao().getFavouriteByCharacterId(character.id)
            if (favourite != null) {
                database.favouritesDao().deleteFavouritesByCharacterId(character.id)
                Log.i("DB Character Manager Test", "Deleted favourite character with ID: ${character.id}")
            } else {
                Log.i("DB Character Manager Test", "Character ID: ${character.id} was not found in favourites")
            }
            val favouritesAfter = database.favouritesDao().getAllFavourites()
            Log.i("DB", "After Deletion - Favourites Table:")
            for (fav in favouritesAfter) {
                Log.i("DB", "ID=${fav.id}, characterId=${fav.characterId()}")
            }
        }
    }
}