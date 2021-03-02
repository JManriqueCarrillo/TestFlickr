package com.example.testflickr.dagger.component

import com.example.testflickr.BaseApp
import com.example.testflickr.dagger.module.ApplicationModule
import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(application: BaseApp)
}