package com.example.repository

import com.example.model.AppState
import com.example.model.DataModelDto
import com.example.core.viewmodel.convertDataModelSuccessToEntity
import com.example.core.viewmodel.mapHistoryEntityToSearchResult



// Теперь наш локальный репозиторий работает. Передаём в конструктор
// HistoryDao (вспоминаем в модуле Koin RoomDataBaseImplementation(get())).
class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    IDataSourceLocal<List<com.example.model.DataModelDto>> {

    override suspend fun getData(word: String): List<com.example.model.DataModelDto> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    // Метод сохранения слова в БД. Он будет использоваться в интеракторе
    override suspend fun saveToDB(appState: com.example.model.AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}

