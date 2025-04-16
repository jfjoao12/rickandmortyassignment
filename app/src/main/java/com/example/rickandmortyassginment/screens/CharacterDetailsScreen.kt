package com.example.rickandmortyassginment.screens

import android.graphics.drawable.Icon
import android.graphics.drawable.shapes.Shape
import android.provider.Settings.Global
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star

import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.rickandmortyassginment.api.db.AppDatabase
import com.example.rickandmortyassginment.api.models.Character
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CharacterDetailsScreen(
    paddingValues: PaddingValues,
    characterItem: Character,
    db: AppDatabase,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {

    with(sharedTransitionScope) {

        ElevatedCard (
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(top = 200.dp, bottom = 125.dp, start = 16.dp, end = 16.dp)
                .sharedElement(
                    sharedTransitionScope.rememberSharedContentState(key = "characterCard-${characterItem.id}"),
                    animatedVisibilityScope = animatedContentScope,
                )
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 110.dp, bottom = 25.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Box(
                    Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(start = 12.dp, end = 12.dp)

                    ){
                        Text(
                            modifier = Modifier
                                .sharedBounds(
                                    sharedTransitionScope.rememberSharedContentState(key = "characterName-${characterItem.id}"),
                                    animatedVisibilityScope = animatedContentScope,
                                    enter = fadeIn(),
                                    exit = fadeOut(),
                                    resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds()
                                ),
                            text = characterItem.name ?: "Unknown",
                            style = MaterialTheme.typography.headlineLarge,
                            fontSize = 44.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            modifier = Modifier
                                .sharedBounds(
                                    sharedTransitionScope.rememberSharedContentState(key = "characterOrigin-${characterItem.id}"),
                                    animatedVisibilityScope = animatedContentScope,
                                ),
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 22.sp,
                            text = "Origin: ${characterItem.origin!!?.name ?: "Unknown"}",
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center,
                            )

                        Text(
                            modifier = Modifier
                                .sharedBounds(
                                    sharedTransitionScope.rememberSharedContentState(key = "characterGender-${characterItem.id}"),
                                    animatedVisibilityScope = animatedContentScope,
                                ),
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 22.sp,
                            text = "Gender: ${characterItem.gender ?: "Unknown"}",
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            modifier = Modifier
                                .sharedBounds(
                                    sharedTransitionScope.rememberSharedContentState(key = "characterSpecies-${characterItem.id}"),
                                    animatedVisibilityScope = animatedContentScope,
                                ),
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 22.sp,
                            text = "Species: ${characterItem.species ?: "Unknown"}",
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 22.sp,
                            text = "Status: ${characterItem.status ?: "Unknown"}",
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }


        Box (
            modifier = Modifier.fillMaxWidth()
        ){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(characterItem.image)
                    .build(),
                contentDescription = "${characterItem.name} profile picture",
                modifier = Modifier
                    .padding(top = 25.dp)
                    .offset(0.dp, 70.dp)
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = "characterImage-${characterItem.id}"),
                        animatedVisibilityScope = animatedContentScope
                    )
                    .size(300.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center)
            )
        }
    }
}