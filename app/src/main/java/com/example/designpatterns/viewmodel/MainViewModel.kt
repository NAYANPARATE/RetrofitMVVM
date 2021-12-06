package com.example.designpatterns.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.designpatterns.model.GithubIssue
import com.example.designpatterns.repository.GithubRepository
import kotlinx.coroutines.*

class MainViewModel(val repository: GithubRepository) : ViewModel() {

    val issuesLiveData: MutableLiveData<List<GithubIssue>> = MutableLiveData()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    var job: Job? = null

    val exceptionHandler = CoroutineExceptionHandler { _ , throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }


    fun getIssues() {

        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = repository.getIssues()

           // Log.e("MainViewModel" , "getIssues:${response.body()}  /n ${response.errorBody()} ")

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    issuesLiveData.value = response.body()
                    loading.value = false
                } else {
                    val msg = response.message() ?: "Data Not Available"
                    onError("Error : $msg ")
                }
            }
        }
    }

    fun getData() = issuesLiveData

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}