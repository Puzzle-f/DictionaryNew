package com.example.dictionarynew.interactor

import com.example.model.AppState
import com.example.model.DataModelDto
import com.example.repository.IRepositoryLocal
import com.example.repository.Repository
import com.example.core.viewmodel.mapSearchResultToResult

class MainInteractor(
    private val repositoryRemote: com.example.repository.Repository<List<com.example.model.DataModelDto>>,
    private val repositoryLocal: com.example.repository.IRepositoryLocal<List<com.example.model.DataModelDto>>
) : com.example.core.viewmodel.IInteractor<com.example.model.AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): com.example.model.AppState {
        val appState: com.example.model.AppState
        if (fromRemoteSource) {
            appState = com.example.model.AppState.Success(mapSearchResultToResult(repositoryRemote.getData(word)))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = com.example.model.AppState.Success(mapSearchResultToResult(repositoryLocal.getData(word)))
        }
        return appState
    }
}

