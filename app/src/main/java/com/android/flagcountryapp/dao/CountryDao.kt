package com.android.flagcountryapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.flagcountryapp.Pays

@Dao
interface CountryDao {
    @Query("SELECT * FROM pays")
    fun getAllPays(): List<Pays>

    @Insert
    fun insert(pays: Pays)

    @Query("DELETE FROM pays WHERE name = :name")
    fun deleteCountry(name: String)
}