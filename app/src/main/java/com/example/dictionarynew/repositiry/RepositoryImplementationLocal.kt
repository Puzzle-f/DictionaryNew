package com.example.dictionarynew.repositiry

import com.example.dictionarynew.AppState
import com.example.dictionarynew.datasource.IDataSourceLocal
import com.example.dictionarynew.model.DataModelDto

// RepositoryImplementationLocal теперь содержит два метода, наследуется от
// RepositoryLocal и в конструктор получает инстанс DataSourceLocal
class RepositoryImplementationLocal(private val dataSource: IDataSourceLocal<List<DataModelDto>>) :
    IRepositoryLocal<List<DataModelDto>> {

    override suspend fun getData(word: String): List<DataModelDto> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}
