package com.innocv.androidcodetest.presentation.main.fragment

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.innocv.androidcodetest.R
import com.innocv.androidcodetest.domain.User
import com.innocv.androidcodetest.infrastructure.di.component.ViewComponent
import com.innocv.androidcodetest.presentation.base.BaseFragment
import com.innocv.androidcodetest.presentation.base.utils.hide
import com.innocv.androidcodetest.presentation.base.utils.onQueryTextChange
import com.innocv.androidcodetest.presentation.base.utils.show
import com.innocv.androidcodetest.presentation.main.adapter.UserListAdapter
import com.innocv.androidcodetest.presentation.main.presenter.UserListPresenter
import com.innocv.androidcodetest.presentation.main.view.UserListView
import kotlinx.android.synthetic.main.fragment_user_list.*
import javax.inject.Inject

class UserListFragment: BaseFragment(), UserListView {

    @Inject
    lateinit var presenter: UserListPresenter

    private val adapter by lazy { UserListAdapter() }

    companion object {
        fun getInstance(): UserListFragment = UserListFragment()
    }

    override val layoutContainer: Int
        get() = R.layout.fragment_user_list

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttach(this)

        adapter.onItemClick = presenter::onClickUser
        adapter.onClickDelete = presenter::onClickDelete
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_user_list, menu)
        configureSearch(menu?.findItem(R.id.action_search))
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun configureSearch(menuItem: MenuItem?) {
        menuItem?.let {
            val searchView = it.actionView as SearchView
            searchView.onQueryTextChange(presenter::filterUserList)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.action_add -> {
            presenter.onClickAddUser()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onLoadData(userList: List<User>) {
        adapter.swapData(userList)
        loadingView.hide()
        emptyView.hide()
        recyclerView.show()
    }

    override fun showEmptyView() {
        loadingView.hide()
        recyclerView.hide()
        emptyView.show()
    }

    override fun onFilterUsers(userList: List<User>) {
        adapter.swapData(userList)
    }

    override fun onUserDeleted(user: User) {
        adapter.notifyItemDeleted(user)
    }

}