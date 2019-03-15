package com.innocv.androidcodetest.domain.interactor.delete

import com.innocv.androidcodetest.domain.User
import com.innocv.androidcodetest.domain.interactor.UseCase
import com.innocv.androidcodetest.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
        private val userRepository: UserRepository
    ): UseCase<Unit, User>() {

    override fun runInBackground(params: User) {

        //TODO: Implement functionality and control when user id is null
        // Y si es NULL qué hacemos desde aquí? Tal vez se tendría que haber controlado antes de llegar al REPO
        params.id?.let { userRepository.removeUser(params.id) }

    }
}