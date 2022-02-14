package com.example.dictionarynew.datasource

import com.example.repository.RetrofitImplementation
import com.example.model.DataModelDto

class DataSourceRemote(private val remoteProvider: com.example.repository.RetrofitImplementation = com.example.repository.RetrofitImplementation()) :
    com.example.repository.IDataSource<List<com.example.model.DataModelDto>> {

    override suspend fun getData(word: String): List<com.example.model.DataModelDto> = remoteProvider.getData(word)
}
