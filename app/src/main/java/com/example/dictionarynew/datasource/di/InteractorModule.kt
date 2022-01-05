package com.example.dictionarynew.datasource.di

import com.example.dictionarynew.interactor.MainInteractor
import com.example.dictionarynew.model.DataModel
import com.example.dictionarynew.repositiry.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}
