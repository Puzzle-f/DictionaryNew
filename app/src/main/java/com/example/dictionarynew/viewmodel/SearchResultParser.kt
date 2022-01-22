package com.example.dictionarynew.viewmodel

import com.example.dictionarynew.AppState
import com.example.dictionarynew.model.DataModel
import com.example.dictionarynew.model.DataModelDto
import com.example.dictionarynew.model.Meanings
import com.example.dictionarynew.model.Translation
import com.example.dictionarynew.model.room.HistoryEntity


fun parseOnlineSearchResults(state: AppState): AppState {
    return AppState.Success(mapResult(state, true))
}

private fun mapResult(
    data: AppState,
    isOnline: Boolean
): List<DataModel> {
    val newSearchResults = arrayListOf<DataModel>()
    when (data) {
        is AppState.Success -> {
            getSuccessResultData(data, isOnline, newSearchResults)
        }
    }
    return newSearchResults
}

private fun getSuccessResultData(
    data: AppState.Success,
    isOnline: Boolean,
    newDataModels: ArrayList<DataModel>
) {
    val dataModels: List<DataModel> = data.data as List<DataModel>
    if (dataModels.isNotEmpty()) {
        if (isOnline) {
            for (searchResult in dataModels) {
                parseOnlineResult(searchResult, newDataModels)
            }
        } else {
            for (searchResult in dataModels) {
                newDataModels.add(DataModel(searchResult.text, arrayListOf()))
            }
        }
    }
}

private fun parseOnlineResult(dataModel: DataModel, newDataModels: ArrayList<DataModel>) {
    if (dataModel.text!!.isNotBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meanings>()
        for (meaning in dataModel.meanings) {
            if (meaning.translation?.translation!!.isNotBlank()) {
                newMeanings.add(Meanings(meaning.translation, meaning.imageUrl))
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(DataModel(dataModel.text, newMeanings))
        }
    }
}

fun convertMeaningsToString(meanings: List<Meanings>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.translation, ", ")
        } else {
            meaning.translation?.translation
        }
    }
    return meaningsSeparatedByComma
}

fun mapSearchResultToResult(searchResults: List<DataModelDto>): List<DataModel> {
    return searchResults.map { searchResult ->
        var meanings: List<Meanings> = listOf()
        searchResult.meanings?.let {
            meanings = it.map { meaningsDto ->
                Meanings(
                    Translation(meaningsDto?.translation?.translation ?: ""),
                    meaningsDto?.imageUrl ?: ""
                )
            }
        }
        DataModel(searchResult.text ?: "", meanings)
    }
}

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

fun parseLocalSearchResults(appState: AppState): AppState {
    return AppState.Success(mapResult(appState, false))
}
