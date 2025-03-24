    package com.example.rickandmortyassginment

    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.padding
    import androidx.compose.material3.CenterAlignedTopAppBar
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
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
    import com.example.rickandmortyassginment.destinations.Destination
    import com.example.rickandmortyassginment.navigation.BottomNav
    import com.example.rickandmortyassginment.screens.AllCharactersScreen
    import com.example.rickandmortyassginment.screens.AllFavouritesLayout
    import com.example.rickandmortyassginment.screens.FavouritesScreen


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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun App(modifier: Modifier,
            characterManager: CharactersManager,
            db: AppDatabase,
            navController: NavController) {
        Scaffold (
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Rick and Morty Challenge",
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
            NavHost(
                navController = navController as NavHostController,
                startDestination = Destination.Main.route,
            ) {
                composable(Destination.Main.route) {
                    AllCharactersScreen(modifier = Modifier, characterManager, db, paddingValues)
                }
                composable(Destination.Favourite.route) {
                    FavouritesScreen(modifier = Modifier, characterManager, db, paddingValues)
                }
            }
        }
    }




