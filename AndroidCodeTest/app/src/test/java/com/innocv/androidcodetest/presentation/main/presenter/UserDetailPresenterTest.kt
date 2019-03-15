package com.innocv.androidcodetest.presentation.main.presenter

import com.innocv.androidcodetest.domain.exception.RepositoryException
import com.innocv.androidcodetest.domain.interactor.create.RegisterUserUseCase
import com.innocv.androidcodetest.domain.interactor.delete.DeleteUserUseCase
import com.innocv.androidcodetest.help.onAnswer
import com.innocv.androidcodetest.presentation.main.view.RegisterUserView
import com.innocv.androidcodetest.presentation.main.view.UserDetailView
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserDetailPresenterTest {

    @InjectMocks
    private lateinit var presenter: UserDetailPresenter

    @Mock
    private lateinit var deleteUserUseCase: DeleteUserUseCase

    @Mock
    private lateinit var view: UserDetailView

    @Before
    fun setUp() {
        presenter.onAttach(view)
    }

    // TODO onClickDelete

    @Test
    fun onClickDelete_whenUserDelete_shouldCloseView() {
        doAnswer {
            it.onAnswer { Unit }
        }.whenever(deleteUserUseCase).executeAsync(any(), any(), any())

        presenter.onClickDelete()

        verify(view).close()
    }
}