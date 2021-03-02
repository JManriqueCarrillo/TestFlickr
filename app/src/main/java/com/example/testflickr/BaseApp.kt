package com.example.testflickr

import android.app.Application
import com.example.testflickr.dagger.component.ApplicationComponent
import com.example.testflickr.dagger.component.DaggerApplicationComponent
import com.example.testflickr.dagger.module.ApplicationModule


class BaseApp : Application() {

    companion object {
        lateinit var instance: BaseApp private set
    }

    lateinit var component: ApplicationComponent


    override fun onCreate() {
        super.onCreate()
        instance = this
        setup()
    }

    fun setup() {
        component = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }
}