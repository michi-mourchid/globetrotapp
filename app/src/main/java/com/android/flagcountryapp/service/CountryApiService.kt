package com.android.flagcountryapp.service

import com.android.flagcountryapp.Pays
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApiService {

    @GET("name/{nom_pays}?apikey=AjUxVCug9IgZZglt0tGgOx2BdA7Nx8BHjDZKkx3O")
    fun getPays(@Path("nom_pays") nom_pays: String): Call<Pays>


    @GET("all?apikey=AjUxVCug9IgZZglt0tGgOx2BdA7Nx8BHjDZKkx3O")
    fun getAllPays(): Call<Map<String,Pays>>
}