package com.innocv.androidcodetest.help

import com.innocv.androidcodetest.domain.User
import com.innocv.androidcodetest.presentation.base.utils.parseDate
import org.mockito.invocation.InvocationOnMock


@Suppress("UNCHECKED_CAST")
fun <T> InvocationOnMock.onAnswer(supplier: () -> T) {
    this?.arguments?.let { args ->
        val onResult = args[1] as (T) -> Unit
        val onError = args[2] as (Throwable) -> Unit

        val result = supplier()

        when (result) {
            is Throwable -> onError(result)
            else -> onResult(result)
        }
    }
}

fun mockUser(): User = User(
        "1",
        "Pepe",
        "2018-09-12T00:00:00".parseDate()
)