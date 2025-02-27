package com.example.rickandmortyassginment.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.rickandmortyassginment.api.models.*

@Composable
fun CharacterLayout(modifier: Modifier = Modifier, charactersManager: CharactersManager) {
    val characters = charactersManager.charactersResponse.value

    LazyColumn (
        modifier = Modifier
            .padding(top = 90.dp)
    ){
        items(characters) { character ->
            CharacterCard(
                characterItem = character,
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
    }

}

@Composable
fun CharacterCard(characterItem: Character, modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(characterItem.image)
                    .build(),
                contentDescription = "${characterItem.name} profile picture",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
            Text(
                text = characterItem.id.toString() ?: "Unknown",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = characterItem.name ?: "Unknown",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Species: ${characterItem.species ?: "Unknown"}",
                fontStyle = FontStyle.Italic
            )
            Text(
                text = "Gender: ${characterItem.gender ?: "Unknown"}",
                fontStyle = FontStyle.Italic
            )
            Text(
                text = "Origin: ${characterItem.origin?.name ?: "Unknown"}",
                fontStyle = FontStyle.Italic
            )
        }
    }
}

