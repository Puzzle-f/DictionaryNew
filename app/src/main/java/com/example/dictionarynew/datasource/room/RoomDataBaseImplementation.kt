package com.example.dictionarynew.datasource.room

import com.example.dictionarynew.datasource.IDataSource
import com.example.dictionarynew.model.DataModel
import io.reactivex.Observable

class RoomDataBaseImplementation : IDataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented") // To change body of created functions use File
        // | Settings | File Templates.
    }
}
