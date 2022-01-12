package com.example.dictionarynew.repositiry

import com.example.dictionarynew.datasource.IDataSource
import com.example.dictionarynew.model.DataModel
import io.reactivex.Observable


class RepositoryImplementation(private val dataSource:
    IDataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    // Репозиторий возвращает данные, используя dataSource (локальный или
    // внешний)
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}
