package com.innocv.androidcodetest.domain.interactor.user

import com.innocv.androidcodetest.domain.User
import com.innocv.androidcodetest.domain.interactor.UseCase
import com.innocv.androidcodetest.domain.interactor.UseCase.None
import com.innocv.androidcodetest.domain.repository.UserRepository
import javax.inject.Inject

class UserListUseCase @Inject constructor(
        private val repository: UserRepository
) : UseCase<List<User>, None>() {

    override fun runInBackground(params: None): List<User> {
        return repository.getUserList()
    }
}