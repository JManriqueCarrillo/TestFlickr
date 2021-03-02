package com.example.testflickr.features.listPhotos

import com.example.testflickr.entities.responses.PhotoResponse

class ListPhotosPresenter(): ListPhotosContract.Presenter {

    private lateinit var view: ListPhotosContract.View
    private var model: ListPhotosContract.Model = ListPhotosModel()

    override fun attach(view: ListPhotosContract.View) {
        this.view = view
    }

    override fun searchPhotos(tag: String, extras: String) {
        view.showProgress()
        model.searchPhotos(this, tag, extras)
    }

    override fun showSearchList(data: List<PhotoResponse>) {
        view.hideProgress()
        view.showSearchList(data)
    }

    override fun showError(text: String) {
        view.hideProgress()
        view.showError(text)
    }
}