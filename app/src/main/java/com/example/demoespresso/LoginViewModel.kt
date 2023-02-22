package com.example.demoespresso

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : BaseViewModel() {

    override fun saveViewModelSate() {

    }

    override fun restoreViewModelState() {

    }

    val userName = MutableLiveData<String>()
    val passWd = MutableLiveData<String>()
    val loginEvent = SingleLiveEvent<Boolean>()

    /**
     * Để adapt với code test, bắt buộc phải dùng kiểu defaultDispatcher ?: <Kiểu ta muốn run real code>
     * Trong real code (class Vm này) k đc set bất kì value nào cho defaultDispatcher
     * Concept :
     * - Trong real code: defaultDispatcher auto = null, và chạy phần dispatcher ở sau là cái ta muốn
     * - Trong code test: luôn chạy defaultDispatcher đã đc gán là testDispatcher (!= null)
     */
    fun login() {
        viewModelScope.launch(defaultDispatcher ?: Dispatchers.Main) {
            loadingVisible()
            launchWithIOContextIdling {
                withContext(defaultDispatcher ?: Dispatchers.IO) {
                    delay(2000)
                    loginEvent.postValue(
                        true
                    )
                }
            }
            loadingInvisible()
        }
    }
}

/**
 * Enable Idling Resource for Espresso Test
 */
suspend fun launchWithIOContextIdling(
    block: suspend () -> Unit
) {
    EspressoIdlingResource.increment()
    block()
    EspressoIdlingResource.decrement()
}