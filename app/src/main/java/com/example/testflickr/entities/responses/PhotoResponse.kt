package com.example.testflickr.entities.responses

import com.google.gson.annotations.SerializedName

class PhotoResponse {
    @SerializedName("id") var id = ""
    @SerializedName("owner") var owner = ""
    @SerializedName("secret") var secret = ""
    @SerializedName("server") var server = ""
    @SerializedName("farm") var farm = 0
    @SerializedName("title") var title = ""
    @SerializedName("ispublic") var ispublic = 0
    @SerializedName("isfriend") var isfriend = 0
    @SerializedName("isfamily") var isfamily = 0
    @SerializedName("description") lateinit var description : DescriptionPhotoResponse
    @SerializedName("lastupdate") var lastUpdate = ""
    @SerializedName("ownername") var ownerName = ""
}