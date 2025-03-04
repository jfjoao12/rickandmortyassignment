package com.example.rickandmortyassginment.api.db

import androidx.room.TypeConverter
import com.example.rickandmortyassginment.api.models.Origin

class Converters {
    @TypeConverter
    fun fromOrigin(origin: Origin?): String? {
        return origin?.let {
            it.name;
        }
    }

    @TypeConverter
    fun toOriginName(value: String?): Origin? {
        return value?.let {
            Origin(name = it)
        }
    }
}