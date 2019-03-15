package com.innocv.androidcodetest.domain.interactor

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


abstract class UseCase<out T, in Params> {

    fun executeAsync(
            params: Params,
            onSuccess: (T) -> Unit,
            onError: (Throwable) -> Unit
    ) {
        GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT) {
            try {
                onSuccess(withContext(Dispatchers.IO) { runInBackground(params) })
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    protected abstract fun runInBackground(params: Params): T

    class None
}