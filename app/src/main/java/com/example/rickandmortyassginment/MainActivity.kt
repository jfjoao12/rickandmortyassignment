package com.example.rickandmortyassginment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.rickandmortyassginment.api.CharactersManager
import com.example.rickandmortyassginment.api.db.AppDatabase
import com.example.rickandmortyassginment.layouts.CharacterLayout
import com.example.rickandmortyassginment.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyAssginmentTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()) { innerPadding ->

                    val db = AppDatabase.getInstance(applicationContext)


                    val characterManager = CharactersManager(db)
                    App(modifier = Modifier
                        .padding(innerPadding),
                        characterManager,
                        db
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(modifier: Modifier, characterManager: CharactersManager, db: AppDatabase) {
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Rick and Morty Challenge",
                        fontWeight = FontWeight.Bold
                    )
                }

            )
        }
    ){paddingValues ->
        paddingValues.calculateBottomPadding()
        CharacterLayout(
            modifier,
            characterManager,
            db
        )

    }
}




