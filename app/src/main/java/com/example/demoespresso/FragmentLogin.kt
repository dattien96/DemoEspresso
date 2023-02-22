package com.example.demoespresso

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentLogin: BaseFragment<LoginViewModel>() {

    override val viewModel: LoginViewModel by activityViewModel<LoginViewModel>()

    companion object {
        fun getInstance() = FragmentLogin()
    }

    override val layoutId: Int
        get() = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<AppCompatButton>(R.id.buttonLogin).setOnClickListener {
            viewModel.login()
        }
        viewModel.loginEvent.observe(viewLifecycleOwner, Observer {
            if (it !== null && it) {
                loginSucceed()
            } else {
                loginFailed()
            }
        })
    }

    fun loginSucceed() {
        Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
    }

    fun loginFailed() {
        Toast.makeText(requireContext(), "failed", Toast.LENGTH_SHORT).show()
    }

}