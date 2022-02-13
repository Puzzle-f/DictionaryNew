package com.example.dictionarynew

import android.util.Log
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun injectDependenciesHistory() = loadModulesHistory

private val loadModulesHistory by lazy {
    loadKoinModules(listOf(historyScreen))
    Log.d("", " Инициилизируоем модуль Koin History")
}

val historyScreen = module {
    scope(named<HistoryActivity>()) {
        scoped { HistoryInteractor(repositoryRemote = get(),repositoryLocal = get()) }
        viewModel { HistoryViewModel(get()) }
    }
}