package com.example.testflickr.interfaces

interface Constant {
    companion object {
        const val API_KEY = "c23173b5dd5fe61a3dbd3410119dde17"
        const val SECRET_KEY = "d53d1c40336954e3"
        const val BASE_URL = "https://www.flickr.com/services/rest/"
        const val SEARCH_URL = "?method=flickr.photos.search&format=json&nojsoncallback=1"
        const val PHOTO_URL = "{serverid}/{id}_{secret}.jpg"
    }
}