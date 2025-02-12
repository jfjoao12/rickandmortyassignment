package com.example.rickandmortyassginment

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.rickandmortyassginment.api.models.*
import com.example.rickandmortyassginment.layouts.CharacterCard
import com.example.rickandmortyassginment.layouts.CharacterLayout
import com.example.rickandmortyassginment.ui.*
import com.example.rickandmortyassginment.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyAssginmentTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()) { innerPadding ->

                    val characterManager = CharactersManager()
                    App(modifier = Modifier
                        .padding(innerPadding), characterManager)
//                        Row (modifier = Modifier
//                            .fillMaxSize()
//                            .height(10.dp)){
//                            Column(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(top = 50.dp),
//                                horizontalAlignment = Alignment.CenterHorizontally
//                            ) {
//
//                            }
//                        }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//fun CardLayout(character: Character
fun App(modifier: Modifier, characterManager: CharactersManager) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {Text(text = "Rick and Morty Challenge")}
            )
        }
    ){paddingValues ->
        paddingValues.calculateBottomPadding()

        CharacterLayout(modifier, characterManager)

    }
}




