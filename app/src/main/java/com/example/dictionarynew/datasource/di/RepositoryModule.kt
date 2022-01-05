package com.example.dictionarynew.datasource.di

import com.example.dictionarynew.datasource.IDataSource
import com.example.dictionarynew.datasource.retrofit.RetrofitImplementation
import com.example.dictionarynew.datasource.room.RoomDataBaseImplementation
import com.example.dictionarynew.model.DataModel
import com.example.dictionarynew.repositiry.Repository
import com.example.dictionarynew.repositiry.RepositoryImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: IDataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSourceLocal: IDataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): IDataSource<List<DataModel>> =
        RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): IDataSource<List<DataModel>> = RoomDataBaseImplementation()
}
