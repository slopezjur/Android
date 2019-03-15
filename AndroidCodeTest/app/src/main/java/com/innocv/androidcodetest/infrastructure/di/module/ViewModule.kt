package com.innocv.androidcodetest.infrastructure.di.module

import android.app.Activity
import com.innocv.androidcodetest.infrastructure.di.scope.ViewScope
import com.innocv.androidcodetest.presentation.base.BaseActivity
import com.innocv.androidcodetest.presentation.base.Navigator
import dagger.Module
import dagger.Provides


@Module
class ViewModule(private val activity: BaseActivity) {

    @Provides
    @ViewScope
    internal fun activity(): Activity {
        return activity
    }

    @Provides
    @ViewScope
    fun providesNavigator(): Navigator = Navigator(activity)

}