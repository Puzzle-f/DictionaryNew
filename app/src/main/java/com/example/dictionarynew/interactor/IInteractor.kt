package com.example.dictionarynew.interactor

import com.example.dictionarynew.AppState
import io.reactivex.Observable

//  чистая бизнес-логика

interface IInteractor<T> {

   suspend fun getData(word: String, fromRemoteSource: Boolean): T
}
