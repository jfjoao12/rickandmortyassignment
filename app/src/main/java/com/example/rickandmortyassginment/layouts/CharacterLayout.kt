package com.example.rickandmortyassginment.layouts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.rememberConstraintsSizeResolver
import coil3.request.ImageRequest
import com.example.rickandmortyassginment.R
import com.example.rickandmortyassginment.api.models.*
import coil3.compose.AsyncImage

@Composable
//fun CardLayout(character: Character
fun CharacterLayout(modifier: Modifier = Modifier, charactersManager: CharactersManager) {
    val characters = charactersManager.charactersResponse.value

    LazyColumn (
        modifier = Modifier
            .padding(top = 90.dp)
    ){
        items(characters) { character ->
            CharacterCard(characterItem = character)
        }
    }

}

@Composable
fun CharacterCard(characterItem: Character) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            //.size(width = 240.dp, height = 125.dp)

            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)

    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterVertically)
            )  {
                AsyncImage(
                    model = ImageRequest.Builder(
                        LocalContext.current
                    ).data("${characterItem.image}")
                        .build(),
                    contentDescription = "${characterItem.name} card",
                    modifier = Modifier
                        .clip(CircleShape)
                )

            }
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(5.dp)
            ) {
                Text(
                    text = "Name: ${characterItem.name}"
                )
                Text(
                    text = "Species: ${characterItem.species} "
                )
                Text(
                    text = "Gender: ${characterItem.gender} "
                )
                Text(
                    text = "Origin: ${characterItem.origin?.name}"
                )
            }
        }
    }

}