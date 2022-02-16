package com.example.history

import com.example.core.viewmodel.IInteractor
import com.example.model.AppState
import com.example.model.DataModel
import com.example.model.DataModelDto
import com.example.repository.IRepositoryLocal
import com.example.repository.Repository

// Класс мало чем отличается от интерактора, который мы уже описывали
class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: IRepositoryLocal<List<DataModel>>
) : IInteractor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState{
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}

//class HistoryInteractor(
//    private val repositoryRemote: Repository<List<DataModelDto>>,
//    private val repositoryLocal: IRepositoryLocal<List<DataModelDto>>
//) : IInteractor<AppState> {
//    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
//        val appState: AppState
//        if (fromRemoteSource) {
//            appState = AppState.Success(mapSearchResultToResult(repositoryRemote.getData(word)))
//            repositoryLocal.saveToDB(appState)
//        } else {
//            appState = AppState.Success(mapSearchResultToResult(repositoryLocal.getData(word)))
//        }
//        return appState
//    }
//}
