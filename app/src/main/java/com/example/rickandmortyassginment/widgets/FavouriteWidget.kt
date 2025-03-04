package com.example.rickandmortyassginment.widgets

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.Text
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.cornerRadius
import androidx.glance.layout.Alignment
import androidx.glance.layout.padding
import androidx.glance.text.FontStyle
import androidx.glance.text.FontWeight.Companion.Bold
import androidx.glance.text.TextStyle
import coil3.Bitmap
import com.example.rickandmortyassginment.MainActivity
import com.example.rickandmortyassginment.api.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class FavouriteWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val db = AppDatabase.getInstance(context.applicationContext)
        val characterItem = withContext(Dispatchers.IO) {
            db.characterDao().getCharacterById(4)
        }
        val bitmap = characterItem?.image?.let { imageUrl ->
            loadBitmapFromUrl(imageUrl)
        }


        provideContent {
            Column(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(GlanceTheme.colors.background)
                    .padding(20.dp)
                    .clickable(actionStartActivity<MainActivity>()),
                horizontalAlignment = Alignment.Horizontal.CenterHorizontally
            ) {
                bitmap?.let {
                    Image(
                        provider = ImageProvider(it),
                        contentDescription = "Character Image",
                        modifier = GlanceModifier
                            .cornerRadius(100.dp),
                    )
                }

                Text(
                    text = "${characterItem?.name}",
                    style =
                    TextStyle(
                        fontWeight = Bold,
                        fontSize = 20.sp,
                        color = GlanceTheme.colors.onBackground,
                    ),
                )
                Text(
                    text ="Species: ${characterItem?.species}",
                    style = TextStyle(
                        fontStyle = FontStyle.Italic,
                        color = GlanceTheme.colors.onBackground
                        ),
                )
                Text(
                    text ="Gender: ${characterItem?.gender}",
                    style = TextStyle(
                        fontStyle = FontStyle.Italic,
                        color = GlanceTheme.colors.onBackground
                    ),
                )
                Text(
                    text ="Origin: ${characterItem?.origin!!?.name}",
                    style = TextStyle(
                        fontStyle = FontStyle.Italic,
                        color = GlanceTheme.colors.onBackground
                    ),
                )
            }
        }
    }
}

private suspend fun loadBitmapFromUrl(imageUrl: String): Bitmap? {
    return withContext(Dispatchers.IO) {
        try {
            val url = URL(imageUrl)
            BitmapFactory.decodeStream(url.openStream())
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}


class WidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = FavouriteWidget()
}

