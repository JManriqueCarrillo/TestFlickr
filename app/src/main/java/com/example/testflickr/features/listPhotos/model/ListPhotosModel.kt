package com.example.testflickr.features.listPhotos.model

import android.util.Log
import com.example.testflickr.entities.responses.SearchResponse
import com.example.testflickr.features.listPhotos.contract.ContractInterface
import com.example.testflickr.interfaces.Constant
import com.example.testflickr.services.ServiceAPI
import com.example.testflickr.services.ServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListPhotosModel: ContractInterface.Model {

    companion object{
        const val TAG = "ListPhotos"
    }

    private var webService: ServiceInterface? = null

    init{
        webService = ServiceAPI.client.create(ServiceInterface::class.java)
    }

    override fun searchPhotos(presenter: ContractInterface.Presenter, tag: String, extras:String) {
        val call = this.webService?.searchPhotos(Constant.API_KEY, tag, extras)
        call?.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if(response.isSuccessful){
                    Log.d(TAG, "Success")
                    val body = response.body()
                    if (body != null) {
                        presenter.showSearchList(body.photos.photo)
                    }
                } else {
                    presenter.showError("Error searching photos")
                    Log.d(TAG, "Error searching photos")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                presenter.showError("Error searching photos: ${t.message}")
                Log.d(TAG, "Failure ${t.message}")
            }
        })
    }
}