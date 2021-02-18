package com.example.testflickr.features.listPhotos.presenter

import com.example.testflickr.features.listPhotos.contract.ContractInterface
import com.example.testflickr.features.listPhotos.model.ListPhotosModel


class ListPhotosPresenter(_view: ContractInterface.View): ContractInterface.Presenter {

    private var view: ContractInterface.View = _view
    private var model: ContractInterface.Model = ListPhotosModel()

    override fun searchPhotos(tag: String) {
       // view?.showProgress()
        model?.searchPhotos(this, tag)
    }

    override fun getPhotoUrl(serverId: String, id:String){
        model?.getPhotoUrl(serverId, id)
    }

    override fun showError(text: String) {
      //  view?.showError(text)
    }


}