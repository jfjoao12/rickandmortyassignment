package com.example.rickandmortyassginment.api

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.rickandmortyassginment.api.models.Character
import com.example.rickandmortyassginment.api.models.CharactersData
import retrofit2.Call
import retrofit2.Response

class CharactersManager {
    private var _characterResponse = mutableStateOf<List<Character>>(emptyList())

    val charactersResponse: MutableState<List<Character>>
        @Composable get() = remember{
            _characterResponse
        }
    init {
        getMovies()
    }
    private fun getMovies(){
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
                }
            }

            override fun onFailure(
                call: Call<CharactersData>,
                t: Throwable) {
                Log.d("error", "${t.message}")


            }

        })
    }
}