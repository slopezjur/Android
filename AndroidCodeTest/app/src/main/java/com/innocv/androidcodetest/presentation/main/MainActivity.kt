package com.innocv.androidcodetest.presentation.main

import android.os.Bundle
import com.innocv.androidcodetest.R
import com.innocv.androidcodetest.infrastructure.di.component.ViewComponent
import com.innocv.androidcodetest.presentation.base.BaseActivity
import com.innocv.androidcodetest.presentation.main.fragment.UserListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: BaseActivity() {

    private var displayHomeAsUpEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        initFragmentContainer(R.id.fragmentContainer, UserListFragment.getInstance())
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)

        toolbar.title = getString(R.string.app_name)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}