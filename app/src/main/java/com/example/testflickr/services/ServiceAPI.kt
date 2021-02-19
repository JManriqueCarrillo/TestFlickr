package com.example.testflickr.services

import com.example.testflickr.interfaces.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceAPI {

    companion object {

        private var interceptor = HttpLoggingInterceptor()
        private var okHttpClient: OkHttpClient? = null
        private var retrofit: Retrofit? = null

        val client: Retrofit
            get() {
                if (retrofit == null) {
                    interceptor = HttpLoggingInterceptor()
                    interceptor.level = HttpLoggingInterceptor.Level.BODY
                    okHttpClient = OkHttpClient.Builder()
                            .addInterceptor(interceptor)
                            .build()
                    retrofit = Retrofit.Builder()
                            .baseUrl(Constant.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(okHttpClient)
                            .build()
                }
                return retrofit!!
            }
    }

}