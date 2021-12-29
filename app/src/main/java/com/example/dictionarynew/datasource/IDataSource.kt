package com.example.dictionarynew.datasource

import io.reactivex.Observable

// Источник данных для репозитория (Интернет, БД и т. п.)
interface IDataSource<T> {

    fun getData(word: String): Observable<T>
}
