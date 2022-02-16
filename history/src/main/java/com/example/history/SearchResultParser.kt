package com.example.history

import com.example.model.AppState
import com.example.model.DataModel
import java.util.*

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
                newDataModels.add(
                    DataModel(
                        searchResult.text,
                        arrayListOf()
                    )
                )
            }
        }
    }
}

private fun parseOnlineResult(
    dataModel: DataModel,
    newDataModels: ArrayList<DataModel>
) {
    if (dataModel.text.isNotBlank() && !dataModel.meanings.isNullOrEmpty()) {
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
            newDataModels.add(DataModel(dataModel.text, newMeanings))
        }
    }
}