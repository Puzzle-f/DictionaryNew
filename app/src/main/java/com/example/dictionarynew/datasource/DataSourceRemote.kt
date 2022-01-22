package com.example.dictionarynew.datasource

import com.example.dictionarynew.datasource.retrofit.RetrofitImplementation
import com.example.dictionarynew.model.DataModelDto

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    IDataSource<List<DataModelDto>> {

    override suspend fun getData(word: String): List<DataModelDto> = remoteProvider.getData(word)
}
