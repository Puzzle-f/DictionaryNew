package com.example.dictionarynew.datasource

import com.example.dictionarynew.AppState

interface IDataSourceLocal<T> : IDataSource<T> {

    suspend fun saveToDB(appState: AppState)
}
