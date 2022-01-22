package com.example.dictionarynew.datasource

import com.example.dictionarynew.model.DataModelDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<DataModelDto>>

//    @GET("words/search")
//    suspend fun search(@Query("search") wordToSearch: String): List<DataModel>
}