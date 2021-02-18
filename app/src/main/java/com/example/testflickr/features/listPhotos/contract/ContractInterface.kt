package com.example.testflickr.features.listPhotos.contract

import com.example.testflickr.entities.responses.PhotoResponse
import java.math.BigDecimal

interface ContractInterface {

    interface View{
        fun showProgress()
        fun hideProgress()
        fun showError(text: String)

        fun updateButtonEnable()
        fun showDialogFragment(transactionsMap: MutableMap<String, MutableMap<String, BigDecimal>>)
        fun showSearchList(data: List<PhotoResponse>)
    }

    interface Presenter{
        fun searchPhotos(tag: String)
        fun showSearchList(data: List<PhotoResponse>)
        fun getPhotoUrl(serverId: String, id: String)

        fun showError(text: String)
    }

    interface Model{
        fun searchPhotos(presenter: ContractInterface.Presenter, tag: String)
        fun getPhotoUrl(serverId: String, id: String)
    }

}