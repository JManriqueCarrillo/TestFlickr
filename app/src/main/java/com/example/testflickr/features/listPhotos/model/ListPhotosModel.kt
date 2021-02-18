package com.example.testflickr.features.listPhotos.model

import android.util.Log
import com.example.testflickr.entities.responses.SearchResponse
import com.example.testflickr.features.listPhotos.contract.ContractInterface
import com.example.testflickr.features.listPhotos.view.MainActivity
import com.example.testflickr.interfaces.Constant
import com.example.testflickr.services.ServiceAPI
import com.example.testflickr.services.ServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigDecimal
import java.math.RoundingMode

class ListPhotosModel: ContractInterface.Model {

    companion object{
        const val TAG = "MainActivityModel"
    }

    private var webService: ServiceInterface? = null
    private lateinit var ratesMap: Map<String?, Map<String?, BigDecimal>>
    private lateinit var transactionsDetailsMap: Map<String?, List<String>>
    private lateinit var transactionsMap: MutableMap<String, MutableMap<String, BigDecimal>>

    init{
        webService = ServiceAPI.client.create(ServiceInterface::class.java)
    }

    override fun searchPhotos(presenter: ContractInterface.Presenter, tag: String) {
        val call = this.webService?.searchPhotos(Constant.API_KEY, tag)
        call?.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if(response?.isSuccessful){
                    Log.d(TAG, "Success")
                    var body = response.body()
                    if (body != null) {
                        presenter?.showSearchList(body.photos.photo)
                    }
                } else {
                    presenter?.showError("Error downloadRates")
                    Log.d(TAG, "Error downloadRates")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                presenter?.showError("Error downloadRates: ${t.message}")
                Log.d(TAG, "Failure ${t.message}")
            }
        })
    }

    override fun getPhotoUrl(serverId: String, id: String) {
        val call = this.webService?.getPhotoUrl(serverId, id)
        call?.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response?.isSuccessful){
                    Log.d(TAG, "Success")
                } else {

                    Log.d(TAG, "Error downloadRates")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d(TAG, "Failure ${t.message}")
            }
        })
    }

}