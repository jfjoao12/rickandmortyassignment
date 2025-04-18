package com.example.rickandmortyassginment.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortyassginment.api.CharactersManager
import com.example.rickandmortyassginment.api.db.AppDatabase
import com.example.rickandmortyassginment.api.models.Character
import com.example.rickandmortyassginment.layouts.CharacterCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AllCharactersScreen (
    modifier: Modifier,
    characterManager: CharactersManager,
    db: AppDatabase,
    navController: NavHostController,
    paddingValues: PaddingValues,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
) {
    AllCharactersLayout(
        modifier,
        characterManager,
        db,
        navController,
        paddingValues,
        animatedVisibilityScope,
        sharedTransitionScope
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AllCharactersLayout(
    modifier: Modifier = Modifier,
    charactersManager: CharactersManager,
    db: AppDatabase,
    navController: NavHostController,
    paddingValues: PaddingValues,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
) {
    //val characters = charactersManager.charactersResponse.value
    var characters by remember { mutableStateOf(listOf<Character>()) }  // Hold the characters in state

    LaunchedEffect(Unit) {  // Run coroutine when composable is first launched
        characters = withContext(Dispatchers.IO) {
            db.characterDao().getAllCharacters()
        }
    }

    LazyColumn (
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ){
        items(characters) { character ->
            CharacterCard(
                navController,
                character,
                Modifier.fillMaxWidth(),
                charactersManager,
                db,
                animatedVisibilityScope,
                sharedTransitionScope
            )
        }
    }
}