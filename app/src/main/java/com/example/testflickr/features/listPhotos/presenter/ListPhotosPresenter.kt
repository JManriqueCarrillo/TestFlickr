package com.example.testflickr.features.listPhotos.presenter

import com.example.testflickr.entities.responses.PhotoResponse
import com.example.testflickr.features.listPhotos.contract.ContractInterface
import com.example.testflickr.features.listPhotos.model.ListPhotosModel


class ListPhotosPresenter(_view: ContractInterface.View): ContractInterface.Presenter {

    private var view: ContractInterface.View = _view
    private var model: ContractInterface.Model = ListPhotosModel()

    override fun searchPhotos(tag: String, extras: String) {
        view?.showProgress()
        model?.searchPhotos(this, tag, extras)
    }

    override fun showSearchList(data: List<PhotoResponse>) {
        view?.hideProgress()
        view?.showSearchList(data)
    }

    override fun showError(text: String) {
        view?.showError(text)
    }


}