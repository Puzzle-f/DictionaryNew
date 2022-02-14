package com.example.history

import com.example.dictionarynew.AppState
import com.example.dictionarynew.model.DataModel
import com.example.dictionarynew.model.DataModelDto
import com.example.dictionarynew.model.Meanings
import com.example.dictionarynew.model.Translation
import com.example.dictionarynew.model.room.HistoryEntity
import com.example.model.DataModel
import com.example.repository.getSuccessResultData
import com.example.repository.parseOnlineResult
import java.util.ArrayList

//fun mapSearchResultToResult(searchResults: List<DataModelDto>): List<DataModel> {
//    return searchResults.map { searchResult ->
//        var meanings: List<Meanings> = listOf()
//        searchResult.meanings?.let {
//            meanings = it.map { meaningsDto ->
//                Meanings(
//                    Translation(meaningsDto?.translation?.translation ?: ""),
//                    meaningsDto?.imageUrl ?: ""
//                )
//            }
//        }
//        DataModel(searchResult.text ?: "", meanings)
//    }
//}


fun parseLocalSearchResults(appState: AppState): AppState {
    return AppState.Success(mapResult(appState, false))
}

private fun mapResult(
    data: com.example.model.AppState,
    isOnline: Boolean
): List<com.example.model.DataModel> {
    val newSearchResults = arrayListOf<com.example.model.DataModel>()
    when (data) {
        is com.example.model.AppState.Success -> {
            getSuccessResultData(data, isOnline, newSearchResults)
        }
    }
    return newSearchResults
}


private fun getSuccessResultData(
    data: com.example.model.AppState.Success,
    isOnline: Boolean,
    newDataModels: ArrayList<DataModel>
) {
    val dataModels: List<com.example.model.DataModel> = data.data as List<com.example.model.DataModel>
    if (dataModels.isNotEmpty()) {
        if (isOnline) {
            for (searchResult in dataModels) {
                parseOnlineResult(searchResult, newDataModels)
            }
        } else {
            for (searchResult in dataModels) {
                newDataModels.add(
                    com.example.model.DataModel(
                        searchResult.text,
                        arrayListOf()
                    )
                )
            }
        }
    }
}

private fun parseOnlineResult(dataModel: com.example.model.DataModel, newDataModels: ArrayList<com.example.model.DataModel>) {
    if (dataModel.text!!.isNotBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<com.example.model.Meanings>()
        for (meaning in dataModel.meanings) {
            if (meaning.translation?.translation!!.isNotBlank()) {
                newMeanings.add(
                    com.example.model.Meanings(
                        meaning.translation,
                        meaning.imageUrl
                    )
                )
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(com.example.model.DataModel(dataModel.text, newMeanings))
        }
    }
}