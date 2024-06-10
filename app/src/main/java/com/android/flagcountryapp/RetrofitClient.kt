package com.android.flagcountryapp
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://countryapi.io/api/"
    val gson = GsonBuilder()
        .registerTypeAdapter(object: TypeToken<Map<String,Pays>>() {}.type, CountryMapAdapter())
        .create()
    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}