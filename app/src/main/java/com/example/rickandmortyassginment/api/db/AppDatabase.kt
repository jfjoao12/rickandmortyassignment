package com.example.rickandmortyassginment.api.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmortyassginment.api.models.Character
import com.example.rickandmortyassginment.api.models.Favourites

@Database(entities = [Character::class, Favourites::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDAO
    abstract fun favouritesDao(): FavouritesDAO

    // COMPANION OBJECT
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase ?= null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Rick and Morty Challenge"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance

            }
        }
    }

}