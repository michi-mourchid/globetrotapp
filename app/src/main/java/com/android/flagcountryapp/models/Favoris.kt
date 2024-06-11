package com.android.flagcountryapp.models

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class Favoris(
){
    companion object {
        var countries : ArrayList<Pays> = ArrayList()
        fun add(pays: Pays) {
            countries.add(pays)
        }

        fun remove(pays: Pays) {
            countries.remove(pays)
        }

        fun find(name: String) : Pays? {
            for (country in countries){
                if (country.name==name){
                    return country as Pays
                }
            }
            return null
        }
    }
}