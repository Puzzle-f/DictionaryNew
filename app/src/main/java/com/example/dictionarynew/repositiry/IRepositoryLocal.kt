package com.example.dictionarynew.repositiry

import com.example.dictionarynew.AppState

interface IRepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}
