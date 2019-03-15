package com.innocv.androidcodetest.domain.repository

import com.innocv.androidcodetest.domain.User

interface UserRepository {

    fun getUserList(): List<User>

    fun createUser(user: User)

    fun removeUser(id: String)

}