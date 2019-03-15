package com.innocv.androidcodetest.presentation.main.presenter

import com.innocv.androidcodetest.domain.User
import com.innocv.androidcodetest.domain.exception.RepositoryException
import com.innocv.androidcodetest.domain.interactor.user.UserListUseCase
import com.innocv.androidcodetest.help.mockUser
import com.innocv.androidcodetest.help.onAnswer
import com.innocv.androidcodetest.presentation.main.view.UserListView
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserListPresenterTest {

    @InjectMocks
    private lateinit var presenter: UserListPresenter

    private lateinit var view: UserListView

    private lateinit var userListUseCase: UserListUseCase

    @Before
    fun setUp() {
        presenter.onAttach(view)
    }

    @Test
    fun onAttach_whenLoadUserList_shouldLoadData() {
        doAnswer {
            it.onAnswer { listOf(mockUser()) }
        }.whenever(userListUseCase).executeAsync(any(), any(), any())

        presenter.onAttach(view)

        verify(view).onLoadData(any())
    }

    @Test
    fun onAttach_whenLoadUserListEmpty_shouldEmptyView() {
        Mockito.doAnswer {
            it.onAnswer { stubEmptyList() }
        }.whenever(userListUseCase).executeAsync(any(), any(), any())

        presenter.onAttach(view)

        verify(view).showEmptyView()
    }

    @Test()
    fun onAttach_whenLoadUserListFail_shouldErrorMessage() {
        Mockito.doAnswer {
            it.onAnswer { RepositoryException("Test Error") }
        }.whenever(userListUseCase).executeAsync(any(), any(), any())

        presenter.onAttach(view)

        verify(view).showMessage(any())
    }

    // TODO onClickDelete

    private fun stubEmptyList(): List<User> = emptyList()

}