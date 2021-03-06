package com.serifyasar.pokemon.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val BASE_URL="https://raw.githubusercontent.com/"
    fun apiCagir() : ApiService{
        val refrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return refrofit.create(ApiService::class.java)
    }
}