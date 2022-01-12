package com.example.dictionarynew.datasource.room

import com.example.dictionarynew.datasource.IDataSource
import com.example.dictionarynew.model.DataModel

class RoomDataBaseImplementation : IDataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        TODO("not implemented") // To change body of created functions use File
        // | Settings | File Templates.
    }
}
