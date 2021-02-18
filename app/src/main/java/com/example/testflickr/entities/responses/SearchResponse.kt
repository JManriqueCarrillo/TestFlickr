package com.example.testflickr.entities.responses

import com.google.gson.annotations.SerializedName

class SearchResponse {
    @SerializedName("photos") var photos: PhotosDataResponse? = null
    @SerializedName("stat") var stat: String? = null
}