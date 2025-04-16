package com.example.rickandmortyassginment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.rickandmortyassginment.api.CharactersManager
import com.example.rickandmortyassginment.api.db.AppDatabase
import com.example.rickandmortyassginment.ui.theme.*
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortyassginment.api.models.Character
import com.example.rickandmortyassginment.destinations.Destination
import com.example.rickandmortyassginment.navigation.BottomNav
import com.example.rickandmortyassginment.screens.AllCharactersScreen
import com.example.rickandmortyassginment.screens.AllFavouritesLayout
import com.example.rickandmortyassginment.screens.CharacterDetailsScreen
import com.example.rickandmortyassginment.screens.FavouritesScreen
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyAssginmentTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    val db = AppDatabase.getInstance(applicationContext)


                    val characterManager = CharactersManager(db)
                    App(modifier = Modifier
                        .padding(innerPadding),
                        characterManager,
                        db,
                        navController
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class,
    ExperimentalSharedTransitionApi::class
)
@Composable
fun App(modifier: Modifier,
        characterManager: CharactersManager,
        db: AppDatabase,
        navController: NavController
) {
    var character by remember {
        mutableStateOf<Character?>(null)
    }
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Rick and Morty Fan App",
                        fontWeight = FontWeight.Bold
                    )
                },

            )
        },
        bottomBar = { BottomNav(navController = navController) }

    ){paddingValues ->
        paddingValues.calculateBottomPadding()
        Spacer(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        )
        SharedTransitionLayout {
            NavHost(
                navController = navController as NavHostController,
                startDestination = Destination.Main.route,
            ) {
                composable(Destination.Main.route) {
                    AllCharactersScreen(
                        modifier = Modifier,
                        characterManager,
                        db,
                        navController,
                        paddingValues,
                        this,
                        this@SharedTransitionLayout
                        )
                }
                composable(Destination.Favourite.route) {
                    FavouritesScreen(
                        navController,
                        modifier = Modifier,
                        characterManager,
                        db,
                        paddingValues,
                        this,
                        this@SharedTransitionLayout
                        )
                }
                composable(Destination.CharacterDetails.route) { navBackStackEntry ->
                    var characterId: String? =
                        navBackStackEntry.arguments?.getString("characterId")
                    GlobalScope.launch {
                        if (characterId != null) {
                            character = db.characterDao().getCharacterById(characterId.toInt())
                        }
                    }
                    character?.let {
                        CharacterDetailsScreen(
                            characterItem = it,
                            paddingValues = paddingValues,
                            db = db,
                            sharedTransitionScope = this@SharedTransitionLayout,
                            animatedContentScope = this,
                        )
                    }
                }
            }
        }
    }
}




