package com.android.flagcountryapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class Country(
    @PrimaryKey val alpha2Code: String,
    val name: String,
    val official_name: String,
    val capital: String,
    val region: String,
    val population: Int,
    val flagUrl: String
)
