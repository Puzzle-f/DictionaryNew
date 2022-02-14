package com.example.core.viewmodel

//  чистая бизнес-логика

interface IInteractor<T> {

   suspend fun getData(word: String, fromRemoteSource: Boolean): T
}
