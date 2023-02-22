package com.example.demoespresso

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * handle always is first arg
 */
val viewModelModule = module {
    viewModel { LoginViewModel() }
}
