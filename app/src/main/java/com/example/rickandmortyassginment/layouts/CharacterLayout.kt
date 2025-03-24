package com.example.rickandmortyassginment.layouts

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.rickandmortyassginment.api.models.Character
import com.example.rickandmortyassginment.api.CharactersManager
import com.example.rickandmortyassginment.api.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CharacterCard(
    characterItem: Character,
    modifier: Modifier = Modifier,
    charactersManager: CharactersManager,
    db: AppDatabase
) {
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
            Box (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(characterItem.image)
                        .build(),
                    contentDescription = "${characterItem.name} profile picture",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .align(Alignment.Center)
                )
                // Menu Add
                MinimalDropdownMenu(
                    db,
                    characterItem,
                    charactersManager,
                    modifier = Modifier
                        .align(
                            alignment = Alignment.TopEnd
                        )
                )
            }

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
                text = "Origin: ${characterItem.origin!!?.name?: "Unknown"}",
                fontStyle = FontStyle.Italic ,

            )
            Log.d("OriginData", characterItem?.origin.toString())

        }
    }
}

@Composable
fun MinimalDropdownMenu(
    db: AppDatabase,
    characterItem: Character,
    charactersManager: CharactersManager,
    modifier: Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 0.dp),
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Text(
                text = "Favourite",
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
            )
            HorizontalDivider(thickness = 2.dp)
            DropdownMenuItem(
                text = { Text("Add") },
                onClick = { charactersManager.addFavourite(db, characterItem)}
            )
            DropdownMenuItem(
                text = { Text("Delete") },
                onClick = { charactersManager.deleteFavourite(db, characterItem)}
            )
        }
    }
}



