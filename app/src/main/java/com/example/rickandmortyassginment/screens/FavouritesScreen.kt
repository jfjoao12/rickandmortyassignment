package com.example.rickandmortyassginment.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.layout.LazyLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rickandmortyassginment.api.CharactersManager
import com.example.rickandmortyassginment.api.db.AppDatabase
import com.example.rickandmortyassginment.api.models.Character
import com.example.rickandmortyassginment.layouts.CharacterCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun FavouritesScreen (
    modifier: Modifier,
    characterManager: CharactersManager,
    db: AppDatabase,
    paddingValues: PaddingValues
) {
    AllFavouritesLayout(
        modifier,
        characterManager,
        db,
        paddingValues
    )
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AllFavouritesLayout(
    modifier: Modifier = Modifier,
    charactersManager: CharactersManager,
    db: AppDatabase,
    paddingValues: PaddingValues
) {
    //val characters = charactersManager.charactersResponse.value
    var charactersFavourite by remember {
        mutableStateOf(emptyList<Character>()) // Initialize empty list
    }


    LaunchedEffect(Unit) {
        db.favouritesDao().getAllFavourites().collect { favourites ->
            charactersFavourite = favourites //
        }
    }
    LazyColumn (
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ){
        items(charactersFavourite) { character ->
            CharacterCard(
                characterItem = character,
                modifier = Modifier
                    .fillMaxWidth(),
                charactersManager,
                db,
            )
        }
    }
}