package com.innocv.androidcodetest.presentation.main.view

import com.innocv.androidcodetest.domain.User
import com.innocv.androidcodetest.presentation.base.BaseView

interface UserListView: BaseView {

    fun onLoadData(userList: List<User>)

    fun showEmptyView()

    fun onFilterUsers(userList: List<User>)

    fun onUserDeleted(user: User)

}