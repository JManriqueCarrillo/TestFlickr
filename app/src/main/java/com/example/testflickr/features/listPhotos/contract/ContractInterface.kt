package com.example.testflickr.features.listPhotos.contract

import com.example.testflickr.entities.responses.PhotoResponse

interface ContractInterface {

    interface View{
        fun showProgress()
        fun hideProgress()
        fun showError(text: String)

        fun showSearchList(data: List<PhotoResponse>)
    }

    interface Presenter{
        fun searchPhotos(tag: String, extras: String)
        fun showSearchList(data: List<PhotoResponse>)

        fun showError(text: String)
    }

    interface Model{
        fun searchPhotos(presenter: Presenter, tag: String, extras: String)
    }

}