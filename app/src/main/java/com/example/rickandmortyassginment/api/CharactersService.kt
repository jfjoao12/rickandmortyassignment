package com.example.rickandmortyassginment.api

import com.example.rickandmortyassginment.api.models.CharactersData
import retrofit2.Call
import retrofit2.http.GET

interface CharactersService {
    //endpoints get defined here
    @GET("character")
    fun getCharacters(): Call<CharactersData>
}