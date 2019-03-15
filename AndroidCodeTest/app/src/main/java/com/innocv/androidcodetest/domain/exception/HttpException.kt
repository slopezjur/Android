package com.innocv.androidcodetest.domain.exception


class HttpException(httpError: Int) : RuntimeException("Http error: $httpError")