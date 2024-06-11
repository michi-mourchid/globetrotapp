package com.android.flagcountryapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.flagcountryapp.models.Pays

@Dao
interface CountryDao {
    /*@Query("SELECT * FROM pays")
    fun getAllPays(): List<Pays>

    @Insert
    fun insert(pays: Pays)

    @Query("DELETE FROM pays WHERE name = :arg0")
    fun deleteCountry(arg0: String)

    @Query("SELECT * FROM pays WHERE name = :arg0 LIMIT 1")
    fun getCountryByName(arg0: String): Pays?*/
}