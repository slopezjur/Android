package com.innocv.androidcodetest.presentation.main.presenter

import com.innocv.androidcodetest.domain.exception.RepositoryException
import com.innocv.androidcodetest.domain.interactor.create.RegisterUserUseCase
import com.innocv.androidcodetest.help.onAnswer
import com.innocv.androidcodetest.presentation.main.view.RegisterUserView
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
class RegisterUserPresenterTest {

    @InjectMocks
    private lateinit var presenter: RegisterUserPresenter

    @Mock
    private lateinit var registerUserUseCase: RegisterUserUseCase

    @Mock
    private lateinit var view: RegisterUserView

    @Before
    fun setUp() {
        presenter.onAttach(view)

        whenever(view.getName()).thenReturn("name")
        whenever(view.getBirthdate()).thenReturn("20 de Enero de 2017")
    }

    @Test
    fun onClickConfirm_whenRegisterUser_shouldCloseView() {
        doAnswer {
            it.onAnswer { Unit }
        }.whenever(registerUserUseCase).executeAsync(any(), any(), any())

        presenter.onClickConfirm()

        verify(view).close()
    }

    @Test
    fun onClickConfirm_whenFailRegisterUser_shouldShowErrorMessage() {
        doAnswer {
            it.onAnswer { RepositoryException("Test Error") }
        }.whenever(registerUserUseCase).executeAsync(any(), any(), any())

        presenter.onClickConfirm()

        verify(view).showMessage(any())
    }


}