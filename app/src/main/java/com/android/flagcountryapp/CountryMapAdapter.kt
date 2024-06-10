package com.android.flagcountryapp

import com.google.gson.*
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CountryMapAdapter : JsonDeserializer<Map<String, Pays>> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Map<String, Pays> {
        val countriesMap = mutableMapOf<String, Pays>()
        val jsonObject = json.asJsonObject

        for ((key, value) in jsonObject.entrySet()) {
            val country = context.deserialize<Pays>(value, Pays::class.java)
            countriesMap[key] = country
        }

        return countriesMap
    }
}