package com.android.flagcountryapp

import androidx.room.TypeConverter
import com.android.flagcountryapp.models.Flag
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromFlag(flag: Flag): String {
        val gson = Gson()
        return gson.toJson(flag)
    }

    @TypeConverter
    fun toFlag(value: String): Flag {
        val gson = Gson()
        return gson.fromJson(value, Flag::class.java)
    }
}