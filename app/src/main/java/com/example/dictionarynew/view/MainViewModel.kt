package com.example.dictionarynew.view

import androidx.lifecycle.LiveData
import com.example.core.viewmodel.BaseViewModel
import com.example.model.AppState
import com.example.dictionarynew.interactor.MainInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val interactor: MainInteractor
) : BaseViewModel<AppState>() {
    // В этой переменной хранится последнее состояние Activity
    private val liveDataForViewToObserve: LiveData<com.example.model.AppState> = _mutableLiveData

    // Переопределяем метод из BaseViewModel
    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            startInteractor(word, isOnline)
        }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            _mutableLiveData.postValue(
               parseOnlineSearchResults(
                    interactor.getData(
                        word,
                        isOnline
                    )
                )
            )
        }

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }
}

