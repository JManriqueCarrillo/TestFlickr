package com.example.testflickr.entities.responses

import com.google.gson.annotations.SerializedName

class SearchResponse {
    @SerializedName("photos") lateinit var photos: PhotosDataResponse
    @SerializedName("stat") var stat: String = ""
}