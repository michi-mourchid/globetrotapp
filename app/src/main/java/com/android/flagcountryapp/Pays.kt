package com.android.flagcountryapp
import com.google.gson.annotations.SerializedName

data class Pays(
    @SerializedName("name") val name: String,
    @SerializedName("official_name") val officialName: String,
    @SerializedName("alpha2Code") val alpha2Code: String,
    @SerializedName("alpha3Code") val alpha3Code: String,
    @SerializedName("numericCode") val numericCode: String,
    @SerializedName("callingCode") val callingCode: String,
    @SerializedName("capital") val capital: String,
    @SerializedName("region") val region: String,
    @SerializedName("population") val population: Int,
    @SerializedName("timezones") val timezones: List<String>,
    @SerializedName("flag") val flag: Flag,
    @SerializedName("regionalBlocs") val regionalBlocs: List<RegionalBloc>
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