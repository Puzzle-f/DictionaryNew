package com.example.repository

import com.example.model.AppState

interface IRepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: com.example.model.AppState)
}
