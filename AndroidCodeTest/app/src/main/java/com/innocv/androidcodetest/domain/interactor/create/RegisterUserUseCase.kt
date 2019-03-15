package com.innocv.androidcodetest.domain.interactor.create

import com.innocv.androidcodetest.domain.User
import com.innocv.androidcodetest.domain.interactor.UseCase
import com.innocv.androidcodetest.domain.repository.UserRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
        private val userRepository: UserRepository
): UseCase<Unit, User>() {
    override fun runInBackground(params: User) {
        userRepository.createUser(params)
    }
}