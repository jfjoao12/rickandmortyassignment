package com.example.rickandmortyassginment.destinations

sealed class Destination(val route: String) {
    object Main : Destination("All")
    object Favourite : Destination("Favourites")
    object CharacterDetails : Destination("characterDetails/{characterId}")
}