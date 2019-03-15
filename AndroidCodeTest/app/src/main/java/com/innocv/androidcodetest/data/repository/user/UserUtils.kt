package com.innocv.androidcodetest.data.repository.user

import com.innocv.androidcodetest.data.datasource.api.user.model.UserResponse
import com.innocv.androidcodetest.domain.User
import com.innocv.androidcodetest.presentation.base.utils.formatDate
import com.innocv.androidcodetest.presentation.base.utils.formatServiceDate
import com.innocv.androidcodetest.presentation.base.utils.parseDate

fun UserResponse.mapToDomain(): User = User(
        id,
        name,
        birthdate.parseDate()
)

fun User.mapToData(): UserResponse = UserResponse(
        id,
        name,
        birthdate.formatServiceDate()
)