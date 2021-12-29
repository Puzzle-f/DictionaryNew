package com.example.dictionarynew.view

import androidx.appcompat.app.AppCompatActivity
import com.example.dictionarynew.AppState
import com.example.dictionarynew.interactor.IInteractor
import com.example.dictionarynew.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState, I : IInteractor<T>> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)
}
