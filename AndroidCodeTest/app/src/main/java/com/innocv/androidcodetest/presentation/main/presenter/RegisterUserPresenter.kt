package com.innocv.androidcodetest.presentation.main.presenter

import com.innocv.androidcodetest.domain.User
import com.innocv.androidcodetest.domain.interactor.create.RegisterUserUseCase
import com.innocv.androidcodetest.presentation.base.BasePresenter
import com.innocv.androidcodetest.presentation.base.utils.parseFormalDate
import com.innocv.androidcodetest.presentation.main.view.RegisterUserView
import javax.inject.Inject


class RegisterUserPresenter @Inject constructor(
        private val registerUserUseCase: RegisterUserUseCase
): BasePresenter<RegisterUserView>() {

    fun onClickConfirm() {
        val user = buildUser()
        registerUserUseCase.executeAsync(user, onSuccess =  { view.close() }, onError = ::onError)
    }

    private fun onError(t: Throwable) {
        view.showMessage(t.message ?: "Error in user registration")
    }

    private fun buildUser(): User = User(
            name = view.getName(),
            birthdate = view.getBirthdate().parseFormalDate()
    )
}