package com.innocv.androidcodetest.infrastructure.di.component

import com.innocv.androidcodetest.infrastructure.AndroidApplication
import com.innocv.androidcodetest.infrastructure.di.module.ApplicationModule
import com.innocv.androidcodetest.infrastructure.di.module.ViewModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
        modules = [ApplicationModule::class]
)
interface ApplicationComponent {

    fun inject(application: AndroidApplication)

    fun viewComponent(viewModule: ViewModule): ViewComponent
}