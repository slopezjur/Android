package com.innocv.androidcodetest.presentation.main.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.widget.AppCompatEditText
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.innocv.androidcodetest.R
import com.innocv.androidcodetest.infrastructure.di.component.ViewComponent
import com.innocv.androidcodetest.presentation.base.BaseFragment
import com.innocv.androidcodetest.presentation.base.utils.formatDate
import com.innocv.androidcodetest.presentation.base.utils.validate
import com.innocv.androidcodetest.presentation.main.presenter.RegisterUserPresenter
import com.innocv.androidcodetest.presentation.main.view.RegisterUserView
import kotlinx.android.synthetic.main.fragment_register_user.*
import java.util.Calendar
import java.util.Date
import javax.inject.Inject


class RegisterUserFragment : BaseFragment(), RegisterUserView {

    @Inject
    lateinit var presenter: RegisterUserPresenter

    companion object {
        fun getInstance(): RegisterUserFragment {
            return RegisterUserFragment()
        }
    }

    override val layoutContainer: Int
        get() = R.layout.fragment_register_user

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

        textBirthdate.setOnClickListener {
            val datePickerFragment = DatePickerFragment.getNewInstance()
            datePickerFragment.setListener(datePickerListener)
            datePickerFragment.show(activity?.supportFragmentManager, DatePickerFragment::class.java.name)
        }
    }

    private val datePickerListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)

        textBirthdate.setText(Date(calendar.timeInMillis).formatDate())
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_register_user, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        menu?.findItem(R.id.action_add)?.isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.action_confirm -> {
            validateForm()
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun validateForm(): Boolean {
        if (isValidForm()) {
            presenter.onClickConfirm()
            return true
        }
        return false
    }

    private fun isValidForm(): Boolean {
        textName.validate({ it.isNotEmpty() }, getString(R.string.validation_empty_error_message))
        textBirthdate.validate({ it.isNotEmpty() }, getString(R.string.validation_empty_error_message))

        return !hasErrors()
    }

    private fun hasErrors(): Boolean {
        for (index in 0..layoutForm.childCount) {
            val child = layoutForm.getChildAt(index)
            if (child is AppCompatEditText && !child.error.isNullOrEmpty()) {
                return true
            }
        }
        return false
    }

    override fun getName(): String {
        return textName.text.toString()
    }

    override fun getBirthdate(): String {
        return textBirthdate.text.toString()
    }

}