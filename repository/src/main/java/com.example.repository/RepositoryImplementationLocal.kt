package com.example.repository

import com.example.model.AppState
import com.example.model.DataModelDto

// RepositoryImplementationLocal теперь содержит два метода, наследуется от
// RepositoryLocal и в конструктор получает инстанс DataSourceLocal
class RepositoryImplementationLocal(private val dataSource: IDataSourceLocal<List<com.example.model.DataModelDto>>) :
    IRepositoryLocal<List<com.example.model.DataModelDto>> {

    override suspend fun getData(word: String): List<com.example.model.DataModelDto> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: com.example.model.AppState) {
        dataSource.saveToDB(appState)
    }
}
