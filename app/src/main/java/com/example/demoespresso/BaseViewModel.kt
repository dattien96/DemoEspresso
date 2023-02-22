package com.example.demoespresso

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher

abstract class BaseViewModel : ViewModel() {

    /**
     * User for Unit-Test Coroutine Purpose
     * Do not set value for this var in real code execution
     */
    var defaultDispatcher: CoroutineDispatcher? = null

    val isLoading = MutableLiveData<Boolean>().apply { value = false }
    val errorApi = MutableLiveData<String>()

    open fun saveViewModelSate() {}
    open fun restoreViewModelState() {}

    override fun onCleared() {
        super.onCleared()
    }

//    suspend inline fun <ModelItem, Model> safeModelCall(
//        mapper: (Model) -> ModelItem,
//        crossinline callFunction: suspend () -> AppResult<Model>?
//    ): ModelItem? {
//        val appResultRes = withContext(Dispatchers.IO) { callFunction.invoke() } ?: return null
//        if (appResultRes is AppResult.Success) {
//            return mapper(appResultRes.data)
//        } else if (appResultRes is AppResult.Error) {
//            errorApi.value = "${appResultRes.error.errorType.name}: ${appResultRes.error.message}"
//            return null
//        }
//        return null
//    }

    fun loadingVisible() {
        isLoading.value = true
    }

    fun loadingInvisible() {
        isLoading.value = false
    }
}
