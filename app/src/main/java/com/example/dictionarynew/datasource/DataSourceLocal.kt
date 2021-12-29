package com.example.dictionarynew.datasource

import com.example.dictionarynew.datasource.room.RoomDataBaseImplementation
import com.example.dictionarynew.model.DataModel
import io.reactivex.Observable


class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) :
    IDataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}
