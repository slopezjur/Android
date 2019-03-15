package com.innocv.androidcodetest.presentation.main.presenter

import com.innocv.androidcodetest.domain.User
import com.innocv.androidcodetest.domain.interactor.UseCase.None
import com.innocv.androidcodetest.domain.interactor.delete.DeleteUserUseCase
import com.innocv.androidcodetest.domain.interactor.user.UserListUseCase
import com.innocv.androidcodetest.presentation.base.BasePresenter
import com.innocv.androidcodetest.presentation.main.view.UserListView
import javax.inject.Inject

class UserListPresenter @Inject constructor(
        private val userListUseCase: UserListUseCase,
        private val deleteUserUseCase: DeleteUserUseCase
): BasePresenter<UserListView>() {

    var userList: MutableList<User> = mutableListOf()

    override fun onAttach(view: UserListView) {
        super.onAttach(view)
        userListUseCase.executeAsync(None(), ::onUserListLoaded, ::onUseCaseError)
    }

    private fun onUserListLoaded(userList: List<User>) {
        this.userList = userList.toMutableList()
        if (userList.isEmpty()) view.showEmptyView()
        else view.onLoadData(userList)
    }

    private fun onUseCaseError(t: Throwable) {
        view.showMessage(t.message ?: "An error has ocurred")
    }

    fun onClickUser(user: User) {
        navigator.showUserDetail(user)
    }

    fun onClickAddUser() {
        navigator.showRegisterUser()
    }

    fun onClickDelete(user: User) {
        // TODO: Implement functionality to delete user
        deleteUserUseCase.executeAsync(user, onSuccess = {onSuccessDelete(user)}, onError = {view.showMessage("What the heck is going on, bro!")})
    }

    private fun onSuccessDelete(user: User) {
        //TODO: Implement actions for when delete has been successful [ensure that local data has been updated]
        view.showMessage("User deleted")
        view.onUserDeleted(user)
    }

    fun filterUserList(text: String) {
        val filteredList = userList.filter { user ->
            containsText(user, text.toLowerCase())
        }
        view.onFilterUsers(filteredList)
    }

    private fun containsText(user: User, text: String): Boolean {
        return user.name?.toLowerCase()?.contains(text) == true
    }
}