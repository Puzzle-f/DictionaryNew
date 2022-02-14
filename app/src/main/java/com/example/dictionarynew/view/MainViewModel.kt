package com.example.dictionarynew.view

import androidx.lifecycle.LiveData
import com.example.model.AppState
import com.example.dictionarynew.interactor.MainInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainViewModel(
    private val interactor: MainInteractor
) :
    BaseViewModel<com.example.model.AppState>() {
    // В этой переменной хранится последнее состояние Activity
    private val liveDataForViewToObserve: LiveData<com.example.model.AppState> = _mutableLiveData

    // Переопределяем метод из BaseViewModel
    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = com.example.model.AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            startInteractor(word, isOnline)
        }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            _mutableLiveData.postValue(
                com.example.history.parseOnlineSearchResults(
                    interactor.getData(
                        word,
                        isOnline
                    )
                )
            )
        }

    fun subscribe(): LiveData<com.example.model.AppState> {
        return liveDataForViewToObserve
    }

    override fun onCleared() {
        _mutableLiveData.value = com.example.model.AppState.Success(null)
        super.onCleared()
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(com.example.model.AppState.Error(error))
    }
}

