package com.android.flagcountryapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountryDao {
    @Query("SELECT * FROM countries")
    fun getAllCountries(): List<Country>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountry(country: Country)

    @Query("DELETE FROM countries WHERE alpha2Code = :alpha2Code")
    fun deleteCountry(alpha2Code: String)
}
