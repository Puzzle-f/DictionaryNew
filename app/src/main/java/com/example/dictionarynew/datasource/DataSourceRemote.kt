package com.example.dictionarynew.datasource

import com.example.dictionarynew.datasource.retrofit.RetrofitImplementation
import com.example.dictionarynew.model.DataModel
import io.reactivex.Observable

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    IDataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> = remoteProvider.getData(word)
}
