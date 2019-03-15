package com.innocv.androidcodetest.domain

import java.io.Serializable
import java.util.Date

class User(
        val id: String? = null,
        val name: String?,
        val birthdate: Date
): Serializable