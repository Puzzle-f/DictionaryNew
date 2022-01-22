package com.example.dictionarynew.repositiry

import com.example.dictionarynew.datasource.IDataSource
import com.example.dictionarynew.model.DataModelDto


class RepositoryImplementation(private val dataSource:
    IDataSource<List<DataModelDto>>) :
    Repository<List<DataModelDto>> {
    // Репозиторий возвращает данные, используя dataSource (локальный или
    // внешний)
    override suspend fun getData(word: String): List<DataModelDto> {
        return dataSource.getData(word)
    }
}
