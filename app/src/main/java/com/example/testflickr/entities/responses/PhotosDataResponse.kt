package com.example.testflickr.entities.responses

import com.google.gson.annotations.SerializedName

class PhotosDataResponse {
    @SerializedName("page") var page = 0
    @SerializedName("pages") var pages = ""
    @SerializedName("perpage") var perpage = 0
    @SerializedName("total") var total = ""
    @SerializedName("photo") lateinit var photo: List<PhotoResponse>
}