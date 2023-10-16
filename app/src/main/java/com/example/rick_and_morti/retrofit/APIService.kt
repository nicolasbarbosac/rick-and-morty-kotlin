package com.example.rick_and_morti.retrofit

import com.example.rick_and_morti.models.MainResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {
    @GET("/api/character/")//@Body AuthRequest: MainResponse   /?page=2"
     fun getcharacters(@Query("page") currentPage: Int): Call<MainResponse>
}