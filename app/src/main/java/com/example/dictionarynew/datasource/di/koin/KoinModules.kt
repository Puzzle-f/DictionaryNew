package com.example.dictionarynew.datasource.di.koin

import androidx.room.Room
import com.example.repository.RetrofitImplementation
import com.example.repository.RoomDataBaseImplementation
import com.example.dictionarynew.interactor.MainInteractor
import com.example.model.DataModelDto
import com.example.repository.HistoryDataBase
import com.example.repository.IRepositoryLocal
import com.example.repository.Repository
import com.example.repository.RepositoryImplementation
import com.example.repository.RepositoryImplementationLocal
import com.example.core.viewmodel.MainViewModel
import org.koin.dsl.module

val application = module {
    // создаём БД
    single {
//        Room.inMemoryDatabaseBuilder(get(), HistoryDataBase::class.java).build()  эта БД будет храниться только в ОП
        Room.databaseBuilder(get(), com.example.repository.HistoryDataBase::class.java, "HistoryDB")
//        .addMigrations()  можно добавить миграцию
//        .fallbackToDestructiveMigration() удаляет старую версию и устанавливает БД заново. Можно исп. вместо миграции
            .build()
    }
    // Получаем DAO
    single { get<com.example.repository.HistoryDataBase>().historyDao() }

    single<com.example.repository.Repository<List<com.example.model.DataModelDto>>> {
        com.example.repository.RepositoryImplementation(
            com.example.repository.RetrofitImplementation()
        )
    }
    single<com.example.repository.IRepositoryLocal<List<com.example.model.DataModelDto>>> {
        com.example.repository.RepositoryImplementationLocal(
            com.example.repository.RoomDataBaseImplementation(
                get()
            )
        )
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}



