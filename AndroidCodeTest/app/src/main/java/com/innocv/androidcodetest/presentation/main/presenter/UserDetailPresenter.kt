package com.innocv.androidcodetest.presentation.main.presenter

import com.innocv.androidcodetest.domain.User
import com.innocv.androidcodetest.domain.interactor.delete.DeleteUserUseCase
import com.innocv.androidcodetest.presentation.base.BasePresenter
import com.innocv.androidcodetest.presentation.main.view.UserDetailView
import javax.inject.Inject

class UserDetailPresenter @Inject constructor(
        private val deleteUserUseCase: DeleteUserUseCase
) : BasePresenter<UserDetailView>() {

    private lateinit var user: User

    fun loadData(user: User) {
        this.user = user
        view.setImageProfile()
        view.fillData(user)
    }

    fun onClickDelete() {
        //TODO improve
        deleteUserUseCase.executeAsync(this.user, onSuccess = {view.close()} , onError = {view.showMessage("What the heck is going on, bro!")})
    }
}