package com.example.rickandmortyassginment.api.models


import com.example.rickandmortyassginment.api.Info
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersData(
    @Json(name = "info")
    var info: Info?,
    @Json(name = "results")
    var results: List<Character?>?
)

