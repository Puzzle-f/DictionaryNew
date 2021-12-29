package com.example.dictionarynew.interactor

import com.example.dictionarynew.AppState
import io.reactivex.Observable

//  чистая бизнес-логика

interface IInteractor<T> {

    fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState>
}
