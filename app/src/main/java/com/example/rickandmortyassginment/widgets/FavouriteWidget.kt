package com.example.rickandmortyassginment.widgets

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.Text
import com.example.rickandmortyassginment.api.CharactersManager
import com.example.rickandmortyassginment.api.db.AppDatabase
import com.example.rickandmortyassginment.api.db.CharacterDAO
import com.example.rickandmortyassginment.api.models.Character
import com.example.rickandmortyassginment.layouts.CharacterCard
import com.example.rickandmortyassginment.layouts.CharacterLayout

class FavouriteWidget(): GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {

        val db = AppDatabase.getInstance(context)
        val cManager = CharactersManager(db)
        val uniqueChar = db.characterDao().getCharacterById(1)
        provideContent {
            Column(
                modifier = GlanceModifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                verticalAlignment = Alignment.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
//                Text(text = "It's working!")
                if (uniqueChar != null) {
                    CharacterCard(uniqueChar, modifier = Modifier, cManager, db)
                }
            }
//                Column {
//                    Text("Test")
//                }


        }
    }
}

class WidgetReceiver: GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = FavouriteWidget()
}