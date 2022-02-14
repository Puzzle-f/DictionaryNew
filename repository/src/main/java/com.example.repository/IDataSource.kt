package com.example.repository

// Источник данных для репозитория (Интернет, БД и т. п.)
interface IDataSource<T> {

    suspend fun getData(word: String): T
}
