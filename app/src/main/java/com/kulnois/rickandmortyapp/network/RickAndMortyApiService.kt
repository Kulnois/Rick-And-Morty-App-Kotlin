package com.kulnois.rickandmortyapp.network

import com.kulnois.rickandmortyapp.data.model.DataRickAndMorty
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by @kulnois on 28/08/2020.
 */

private const val BASE_URL = "https://rickandmortyapi.com/api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface RickAndMortyApiService {

    @GET("character/")
    suspend fun getData(@Query("page") type: Int):
            Response<DataRickAndMorty>
}

object RickAndMortyApi {
    val retrofitService : RickAndMortyApiService by lazy {
        retrofit.create(RickAndMortyApiService::class.java)
    }
}