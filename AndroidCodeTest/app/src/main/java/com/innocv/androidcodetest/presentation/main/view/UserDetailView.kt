package com.innocv.androidcodetest.presentation.main.view

import com.innocv.androidcodetest.domain.User
import com.innocv.androidcodetest.presentation.base.BaseView

interface UserDetailView: BaseView {
    fun setImageProfile()
    fun fillData(user: User)
}