package com.innocv.androidcodetest.data.repository.user

import com.innocv.androidcodetest.data.datasource.api.ApiClientGenerator
import com.innocv.androidcodetest.data.datasource.api.user.UserApi
import com.innocv.androidcodetest.data.datasource.api.user.model.UserResponse
import com.innocv.androidcodetest.help.CallFake
import com.innocv.androidcodetest.help.mockUser
import com.innocv.androidcodetest.presentation.base.utils.parseDate
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.Matchers.hasProperty
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserDataRepositoryTest {

    @InjectMocks
    private lateinit var repository: UserDataRepository

    @Mock
    private lateinit var clientGenerator: ApiClientGenerator

    @Mock
    private lateinit var userApi: UserApi

    @Before
    fun setUp() {
        whenever(clientGenerator.generateApi(UserApi::class.java)).thenReturn(userApi)
    }

    @Test
    fun getUserList_whenCallUserListApi_shouldMapDataCorrectly() {
        whenever(userApi.getUsers())
                .thenReturn(CallFake.buildSuccess(listOf(buildUserResponse())))

        val userList = repository.getUserList()

        assertThat(userList.size, `is`(1))
        assertThat(userList, hasItem(hasProperty("name", `is`("Stub"))))
        assertThat(userList, hasItem(hasProperty("birthdate", `is`("2018-09-12T00:00:00".parseDate()))))
    }

    @Test
    fun getUserList_whenCallToRepository_shouldCallToApi() {
        whenever(userApi.getUsers())
                .thenReturn(CallFake.buildSuccess(listOf(buildUserResponse())))

        repository.getUserList()

        verify(userApi).getUsers()
    }

    @Test
    fun createUser_whenCallToRepository_shouldReturnSuccess() {
        whenever(userApi.create(any()))
                .thenReturn(CallFake.buildSuccess())

        repository.createUser(mockUser())

        verify(userApi).create(any())
    }

    @Test
    fun createUser_whenCallToRepository_shouldMapRequestCorrectly() {
        whenever(userApi.create(any()))
                .thenReturn(CallFake.buildSuccess())

        repository.createUser(mockUser())

        argumentCaptor<UserResponse>().apply {
            verify(userApi).create(capture())

            val request = this.firstValue

            assertThat(request.id, `is`("1"))
            assertThat(request.name, `is`("Pepe"))
            assertThat(request.birthdate, `is`("2018-09-12T00:00:00"))
        }
    }

    @Test
    fun deleteUser_whenCallToRepository_shouldReturnSuccess() {

        val mockUser = mockUser()

        whenever(userApi.delete(mockUser.id!!))
                .thenReturn(CallFake.removeSuccess())

        mockUser.id?.let {
            repository.removeUser(mockUser.id!!)    // Worst shit ever with !! or what...
        }

        verify(userApi).delete(any())
    }

    private fun buildUserResponse() = UserResponse(
            Math.random().toInt().toString(),
            "Stub",
            "2018-09-12T00:00:00"
    )
}