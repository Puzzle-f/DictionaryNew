package com.example.dictionarynew.repositiry

import io.reactivex.Observable

//  эти данные передаём интерактору
interface Repository<T> {

    suspend fun getData(word: String): T
}
