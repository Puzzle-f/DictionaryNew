package com.example.core.viewmodel

import com.example.model.DataModel
import com.example.model.DataModelDto

//  чистая бизнес-логика

interface IInteractor<T> {

   suspend fun getData(word: String, fromRemoteSource: Boolean): T
//    abstract fun mapSearchResultToResult(data: List<DataModelDto>): List<DataModel>?
}
