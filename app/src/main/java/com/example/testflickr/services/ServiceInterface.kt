package com.example.testflickr.services

import com.example.testflickr.entities.responses.SearchResponse
import com.example.testflickr.interfaces.Constant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceInterface {

    @GET(Constant.SEARCH_URL)
    fun searchPhotos(@Query("api_key") apikey:String,
                     @Query("tags") tags:String,
                     @Query("extras") extras: String): Call<SearchResponse>


}