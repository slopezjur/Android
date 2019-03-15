package com.innocv.androidcodetest.data.repository.user

import com.innocv.androidcodetest.data.datasource.api.ApiClientGenerator
import com.innocv.androidcodetest.data.datasource.api.user.UserApi
import com.innocv.androidcodetest.data.repository.BaseRepository
import com.innocv.androidcodetest.domain.User
import com.innocv.androidcodetest.domain.repository.UserRepository
import javax.inject.Inject

class UserDataRepository @Inject constructor(
        private val apiClientGenerator: ApiClientGenerator
): BaseRepository(), UserRepository {

    override fun getUserList(): List<User> {
        val userApi = apiClientGenerator.generateApi(UserApi::class.java)
        val call = userApi.getUsers()

        return executeCall(call).map { it.mapToDomain() }
    }

    override fun createUser(user: User) {
        val userApi = apiClientGenerator.generateApi(UserApi::class.java)
        val call = userApi.create(user.mapToData())

        executeEmptyCall(call)
    }

    override fun removeUser(id: String) {
        //TODO: Implement API call
        val userApi = apiClientGenerator.generateApi((UserApi::class.java))
        val call = userApi.delete(id)

        return executeCall(call)
    }
}