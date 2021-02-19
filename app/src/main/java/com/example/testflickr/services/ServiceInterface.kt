package com.example.testflickr.services


import com.example.testflickr.entities.responses.SearchResponse
import com.example.testflickr.interfaces.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceInterface {

    @GET(Constant.SEARCH_URL)
    fun searchPhotos(@Query("api_key") apikey:String,
                     @Query("tags") tags:String,
                     @Query("extras") extras: String): Call<SearchResponse>


}