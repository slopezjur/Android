package com.innocv.androidcodetest.infrastructure.di.component

import com.innocv.androidcodetest.domain.interactor.delete.DeleteUserUseCase
import com.innocv.androidcodetest.infrastructure.di.module.ViewModule
import com.innocv.androidcodetest.infrastructure.di.scope.ViewScope
import com.innocv.androidcodetest.presentation.main.MainActivity
import com.innocv.androidcodetest.presentation.main.fragment.RegisterUserFragment
import com.innocv.androidcodetest.presentation.main.fragment.UserDetailFragment
import com.innocv.androidcodetest.presentation.main.fragment.UserListFragment
import dagger.Subcomponent


@ViewScope
@Subcomponent(
        modules = [
            ViewModule::class
        ]
)
interface ViewComponent {

    fun inject(activity: MainActivity)

    fun inject(userListFragment: UserListFragment)
    fun inject(userDetailFragment: UserDetailFragment)
    fun inject(registerUserFragment: RegisterUserFragment)
}