package com.example.mybeerapp.data.api

import com.example.mybeerapp.data.model.BeerApiModel
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PunkApi {

    @GET("/v2/beers")
    suspend fun getBeers(
        @Query("beer_name") name: String
    ) : Response<List<BeerApiModel>>

    @GET("/v2/beers/{id}")
    suspend fun getBeer(
        @Path("id") id: Int
    ) : Response<List<BeerApiModel>>

    companion object {
        private const val BASE_URL = "https://api.punkapi.com/v2/"

        fun create(): PunkApi {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(PunkApi::class.java)
        }
    }
}