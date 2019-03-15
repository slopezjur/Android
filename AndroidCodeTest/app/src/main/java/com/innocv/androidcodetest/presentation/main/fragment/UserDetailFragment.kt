package com.innocv.androidcodetest.presentation.main.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.innocv.androidcodetest.R
import com.innocv.androidcodetest.domain.User
import com.innocv.androidcodetest.infrastructure.di.component.ViewComponent
import com.innocv.androidcodetest.presentation.base.BaseFragment
import com.innocv.androidcodetest.presentation.base.utils.formatDate
import com.innocv.androidcodetest.presentation.base.utils.getParamsByClass
import com.innocv.androidcodetest.presentation.base.utils.setParamsByClass
import com.innocv.androidcodetest.presentation.base.utils.showCircleImage
import com.innocv.androidcodetest.presentation.main.presenter.UserDetailPresenter
import com.innocv.androidcodetest.presentation.main.view.UserDetailView
import kotlinx.android.synthetic.main.fragment_user_detail.*
import javax.inject.Inject


class UserDetailFragment: BaseFragment(), UserDetailView {

    @Inject
    lateinit var presenter: UserDetailPresenter

    companion object {
        fun getInstance(user: User): UserDetailFragment {
            val fragment = UserDetailFragment()
            fragment.setParamsByClass(user, User::class.java.simpleName)
            return fragment
        }
    }

    override val layoutContainer: Int
        get() = R.layout.fragment_user_detail

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
        presenter.loadData(getParamsByClass(User::class.java.simpleName))
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_detail_user, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.action_delete -> {
            presenter.onClickDelete()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun setImageProfile() {
        context?.let {
            imageProfile.showCircleImage(it)
        }
    }

    override fun fillData(user: User) {
        textTitleName.text = user.name
        textUsername.text = user.name
        textRegistered.text = user.birthdate.formatDate()
    }

}