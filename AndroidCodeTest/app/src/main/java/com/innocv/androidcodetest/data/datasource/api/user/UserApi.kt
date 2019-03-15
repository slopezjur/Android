package com.innocv.androidcodetest.data.datasource.api.user

import com.innocv.androidcodetest.data.datasource.api.user.model.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @GET("user/")
    fun getUsers(): Call<List<UserResponse>>

    @POST("user/")
    fun create(@Body userResponse: UserResponse): Call<Unit>

    @DELETE("user/{id}")
    fun delete(@Path(value = "id", encoded = false) userId: String): Call<Unit>

}