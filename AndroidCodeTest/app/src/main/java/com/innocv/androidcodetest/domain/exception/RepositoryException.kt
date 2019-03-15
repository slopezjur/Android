package com.innocv.androidcodetest.domain.exception


open class RepositoryException : RuntimeException {

    constructor() : super()

    constructor(message: String) : super(message)
}