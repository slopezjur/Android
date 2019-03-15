package com.innocv.androidcodetest.data.datasource.api

interface ApiClientGenerator {
    fun <T> generateApi(service: Class<T>): T
}