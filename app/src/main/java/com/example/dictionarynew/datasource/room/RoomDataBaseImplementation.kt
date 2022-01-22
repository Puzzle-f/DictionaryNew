package com.example.dictionarynew.datasource.room

import com.example.dictionarynew.AppState
import com.example.dictionarynew.datasource.IDataSourceLocal
import com.example.dictionarynew.model.DataModelDto
import com.example.dictionarynew.model.room.HistoryDao
import com.example.dictionarynew.viewmodel.convertDataModelSuccessToEntity
import com.example.dictionarynew.viewmodel.mapHistoryEntityToSearchResult



// Теперь наш локальный репозиторий работает. Передаём в конструктор
// HistoryDao (вспоминаем в модуле Koin RoomDataBaseImplementation(get())).
class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    IDataSourceLocal<List<DataModelDto>> {

    override suspend fun getData(word: String): List<DataModelDto> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    // Метод сохранения слова в БД. Он будет использоваться в интеракторе
    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}

