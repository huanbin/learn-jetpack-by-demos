package com.example.jetpack_demos.room.converter

import androidx.room.TypeConverter
import java.util.Date

//@ProvidedTypeConverter
class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}