package com.example.dictionarynew.interactor

import com.example.core.viewmodel.IInteractor
import com.example.history.mapSearchResultToResult
import com.example.model.AppState
import com.example.model.DataModelDto
import com.example.repository.IRepositoryLocal
import com.example.repository.Repository


class MainInteractor(
    private val repositoryRemote: Repository<List<DataModelDto>>,
    private val repositoryLocal: IRepositoryLocal<List<DataModelDto>>
) : IInteractor<AppState> {

    override suspend fun getData(
        word: String,
        fromRemoteSource: Boolean
    ): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(
                mapSearchResultToResult(
                    repositoryRemote.getData(word)
                )
            )
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(
                mapSearchResultToResult(
                    repositoryLocal.getData(word)
                )
            )
        }
        return appState
    }
}

