package com.innocv.androidcodetest.infrastructure.di.module

import com.innocv.androidcodetest.data.datasource.api.ApiClientGenerator
import com.innocv.androidcodetest.data.datasource.api.RetrofitApiClientGenerator
import com.innocv.androidcodetest.data.repository.user.UserDataRepository
import com.innocv.androidcodetest.domain.repository.UserRepository
import com.innocv.androidcodetest.infrastructure.AndroidApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun providesApplicationContext(): AndroidApplication {
        return application
    }

    @Provides
    @Singleton
    internal fun provideApiClientGenerator(apiClientGenerator: RetrofitApiClientGenerator): ApiClientGenerator {
        return apiClientGenerator
    }

    @Provides
    @Singleton
    fun providesUserRepository(userDataRepository: UserDataRepository): UserRepository = userDataRepository

}