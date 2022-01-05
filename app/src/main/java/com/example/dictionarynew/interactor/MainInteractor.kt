package com.example.dictionarynew.interactor

import android.app.appsearch.SearchResult
import com.example.dictionarynew.AppState
import com.example.dictionarynew.datasource.di.NAME_LOCAL
import com.example.dictionarynew.datasource.di.NAME_REMOTE
import com.example.dictionarynew.model.DataModel
import com.example.dictionarynew.repositiry.Repository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val repositoryRemote: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val repositoryLocal: Repository<List<DataModel>>
)
    : IInteractor<AppState> {
    // Интерактор лишь запрашивает у репозитория данные, детали имплементации
    // интерактору неизвестны
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            repositoryRemote.getData(word).map { AppState.Success(it) }
        } else {
            repositoryLocal.getData(word).map { AppState.Success(it) }
        }
    }
}
