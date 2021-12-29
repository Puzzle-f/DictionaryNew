package com.example.dictionarynew.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dictionarynew.AppState
import com.example.dictionarynew.datasource.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T : AppState>(
    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : ViewModel() { // Наследуемся от ViewModel
    // Метод, благодаря которому Activity подписывается на изменение данных,
    // возвращает LiveData, через которую и передаются данные
    open fun getData(word: String, isOnline: Boolean): LiveData<T> = liveDataForViewToObserve
    // Единственный метод класса ViewModel, который вызывается перед
    // уничтожением Activity
    override fun onCleared() {
        compositeDisposable.clear()
    }
}
