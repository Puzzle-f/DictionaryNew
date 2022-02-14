package com.example.repository

import com.example.model.DataModelDto


class RepositoryImplementation(private val dataSource:
                               IDataSource<List<com.example.model.DataModelDto>>
) :
    Repository<List<com.example.model.DataModelDto>> {
    // Репозиторий возвращает данные, используя dataSource (локальный или
    // внешний)
    override suspend fun getData(word: String): List<com.example.model.DataModelDto> {
        return dataSource.getData(word)
    }
}
