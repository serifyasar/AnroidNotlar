package com.serifyasar.quiz.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api.php")
    fun soruCek(@Query("amount") amount: Int, @Query("type") type:String) : Call<ApiQuestion>

}