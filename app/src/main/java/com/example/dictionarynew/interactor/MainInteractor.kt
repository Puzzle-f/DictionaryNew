package com.example.dictionarynew.interactor

import com.example.dictionarynew.AppState
import com.example.dictionarynew.model.DataModelDto
import com.example.dictionarynew.repositiry.IRepositoryLocal
import com.example.dictionarynew.repositiry.Repository
import com.example.dictionarynew.viewmodel.mapSearchResultToResult

class MainInteractor(
    private val repositoryRemote: Repository<List<DataModelDto>>,
    private val repositoryLocal: IRepositoryLocal<List<DataModelDto>>
) : IInteractor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(mapSearchResultToResult(repositoryRemote.getData(word)))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(mapSearchResultToResult(repositoryLocal.getData(word)))
        }
        return appState
    }
}

