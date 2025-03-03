package com.example.rickandmortyassginment.widgets

import android.content.Context
import androidx.compose.material3.MaterialTheme
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
import com.example.rickandmortyassginment.api.db.AppDatabase

class FavouriteWidget: GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Column(
                modifier = GlanceModifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                verticalAlignment = Alignment.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "It's working!")
            }
        }
    }
}

class WidgetReceiver: GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = FavouriteWidget()
}