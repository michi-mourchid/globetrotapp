package com.android.flagcountryapp
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pays")
data class Pays(
    @PrimaryKey @SerializedName("name") val name: String,
    @SerializedName("official_name") val officialName: String,
    @SerializedName("topLevelDomain") val topLevelDomain: JsonElement,
    @SerializedName("alpha2Code") val alpha2Code: String,
    @SerializedName("alpha3Code") val alpha3Code: String,
    @SerializedName("cioc") val cioc: String,
    @SerializedName("numericCode") val numericCode: String,
    @SerializedName("callingCode") val callingCode: String,
    @SerializedName("capital") val capital: String,
    @SerializedName("altSpellings") val altSpellings: JsonElement,
    @SerializedName("region") val region: String,
    @SerializedName("subregion") val subregion: String,
    @SerializedName("population") val population: Int,
    @SerializedName("latLng") val latLng: JsonElement,
    @SerializedName("demonyms") val demonyms: JsonElement,
    @SerializedName("area") val area: Float,
    @SerializedName("gini") val gini: JsonElement,
    @SerializedName("timezones") val timezones: List<String>,
    @SerializedName("borders") val borders: JsonElement,
    @SerializedName("nativeNames") val nativeNames: JsonElement,
    @SerializedName("currencies") val currencies: JsonElement,
    @SerializedName("languages") val languages: JsonElement,
    @SerializedName("translations") val translations: JsonElement,
    @SerializedName("flag") val flag: Flag,
    @SerializedName("regionalBlocs") val regionalBlocs: JsonElement
)

data class LatLng(
    @SerializedName("country") val country: List<Double>,
    @SerializedName("capital") val capital: List<Double>
)

data class Demonyms(
    @SerializedName("eng") val eng: Gender,
    @SerializedName("fra") val fra: Gender
)

data class Gender(
    @SerializedName("f") val female: String,
    @SerializedName("m") val male: String
)

data class Gini(
    @SerializedName("2018") val value: Double
)

data class NativeNames(
    @SerializedName("bar") val bar: Name
)

data class Name(
    @SerializedName("official") val official: String,
    @SerializedName("common") val common: String
)

data class Currencies(
    @SerializedName("EUR") val eur: Currency
)

data class Currency(
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String
)

data class Flag(
    @SerializedName("small") val small: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("large") val large: String
)

data class RegionalBloc(
    @SerializedName("acronym") val acronym: String,
    @SerializedName("name") val name: String
)