package com.example.dictionarynew.viewmodel

import androidx.lifecycle.LiveData
import com.example.dictionarynew.AppState
import com.example.dictionarynew.datasource.DataSourceLocal
import com.example.dictionarynew.datasource.DataSourceRemote
import com.example.dictionarynew.interactor.MainInteractor
import com.example.dictionarynew.repositiry.RepositoryImplementation
import io.reactivex.observers.DisposableObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {
    // В этой переменной хранится последнее состояние Activity
    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    // Переопределяем метод из BaseViewModel
    override fun getData(word: String, isOnline: Boolean) {
_mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            startInteractor(word, isOnline)
        }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean)=
        withContext(Dispatchers.IO) {
            _mutableLiveData.postValue(parseOnlineSearchResults(interactor.getData(word, isOnline)))
        }

    fun subscribe(): LiveData<AppState>{
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

