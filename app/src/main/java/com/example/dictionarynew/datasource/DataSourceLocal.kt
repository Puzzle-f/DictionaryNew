package com.example.dictionarynew.datasource

import com.example.dictionarynew.datasource.room.RoomDataBaseImplementation
import com.example.dictionarynew.model.DataModel
import io.reactivex.Observable


class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) :
    IDataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> = remoteProvider.getData(word)
}
