package com.example.repository

//  эти данные передаём интерактору
interface Repository<T> {

    suspend fun getData(word: String): T
}
