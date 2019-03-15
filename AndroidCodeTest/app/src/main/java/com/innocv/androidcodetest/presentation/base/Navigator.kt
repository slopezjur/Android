package com.innocv.androidcodetest.presentation.base

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.innocv.androidcodetest.R
import com.innocv.androidcodetest.domain.User
import com.innocv.androidcodetest.presentation.main.MainActivity
import com.innocv.androidcodetest.presentation.main.fragment.RegisterUserFragment
import com.innocv.androidcodetest.presentation.main.fragment.UserDetailFragment
import javax.inject.Inject

class Navigator @Inject constructor(private val context: Context) {

    private val fragmentManager: FragmentManager
        get() = (context as AppCompatActivity).supportFragmentManager

    private fun showFragment(containerId: Int, fragment: Fragment, backEnable: Boolean) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)

        if (backEnable) {
            val fragmentTag = retrieveFragmentTag(fragment)
            transaction.addToBackStack(fragmentTag)
        }

        transaction.commit()
    }

    private fun retrieveFragmentTag(fragment: Fragment?): String? {
        return fragment?.javaClass?.simpleName
    }

    fun showUserDetail(user: User) {
        showFragment(R.id.fragmentContainer, UserDetailFragment.getInstance(user), true)
    }

    fun showRegisterUser() {
        showFragment(R.id.fragmentContainer, RegisterUserFragment.getInstance(), true)
    }
}