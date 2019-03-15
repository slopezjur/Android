package com.innocv.androidcodetest.infrastructure

import android.app.Application
import com.innocv.androidcodetest.BuildConfig
import com.innocv.androidcodetest.infrastructure.di.component.ApplicationComponent
import com.innocv.androidcodetest.infrastructure.di.component.DaggerApplicationComponent
import com.innocv.androidcodetest.infrastructure.di.module.ApplicationModule
import timber.log.Timber

class AndroidApplication: Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }


    override fun onCreate() {
        applicationComponent.inject(this)
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}