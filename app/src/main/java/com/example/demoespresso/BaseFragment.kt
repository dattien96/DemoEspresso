package com.example.demoespresso

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment<V : BaseViewModel> : Fragment(), CoroutineScope {

    abstract val viewModel: V

    @get:LayoutRes
    abstract val layoutId: Int

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    var job: Job = Job()

    private var mAlertDialog: AlertDialog? = null

    open fun initView() {

    }

    open fun dataObserve() {

    }

    open fun loadData() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(container!!.context).inflate(layoutId, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.restoreViewModelState()
    }

    override fun onPause() {
        viewModel.saveViewModelSate()
        super.onPause()
    }

    override fun onDestroyView() {
        viewModel.saveViewModelSate()
        job.cancel()
        hideKeyboard()
        super.onDestroyView()
    }

    fun hideKeyboard() {
        val view = activity?.currentFocus
        if (view != null) {
            dismissKeyboard(view.windowToken)
        }
    }

    private fun dismissKeyboard(windowToken: IBinder) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }


    fun hideLoading() {
        if (mAlertDialog != null && mAlertDialog!!.isShowing) {
            mAlertDialog?.cancel()
        }
    }
}