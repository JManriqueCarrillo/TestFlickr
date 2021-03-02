package com.example.testflickr.dagger.module

import android.app.Activity
import com.example.testflickr.features.listPhotos.ListPhotosContract
import com.example.testflickr.features.listPhotos.ListPhotosPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideListPhotosActivity(): Activity {
        return activity
    }

    @Provides
    fun provideListPhotosPresenter(): ListPhotosContract.Presenter{
        return ListPhotosPresenter()
    }
}