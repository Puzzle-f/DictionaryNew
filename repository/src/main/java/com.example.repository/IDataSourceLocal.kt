package com.example.repository

import com.example.model.AppState

interface IDataSourceLocal<T> : IDataSource<T> {

    suspend fun saveToDB(appState: com.example.model.AppState)
}
