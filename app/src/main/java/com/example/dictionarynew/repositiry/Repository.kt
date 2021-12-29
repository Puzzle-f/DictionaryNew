package com.example.dictionarynew.repositiry

import io.reactivex.Observable

//  эти данные передаём интерактору
interface Repository<T> {

    fun getData(word: String): Observable<T>
}
