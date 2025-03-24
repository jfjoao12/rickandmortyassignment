package com.example.rickandmortyassginment.destinations

sealed class Destination(val route: String) {
    object Main : Destination("All")
    object Favourite : Destination("Favourites")

}