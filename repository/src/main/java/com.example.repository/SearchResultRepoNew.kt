package com.example.repository

// Принимаем на вход список слов в виде таблицы из БД и переводим его в
// List<SearchResult>
fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<DataModelDto> {
//return list.map { DataModel(it.word, null )}

    val searchResult = ArrayList<DataModelDto>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(DataModelDto(entity.word, null))
        }
    }
    return searchResult
}

fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isEmpty()) {
                null
            } else {
                HistoryEntity(searchResult[0].text, null)
            }
        }
        else -> null
    }
}
