package com.example.testflickr.dagger.component

import com.example.testflickr.dagger.module.ActivityModule
import com.example.testflickr.features.listPhotos.ListPhotosActivity
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(activity: ListPhotosActivity)
}