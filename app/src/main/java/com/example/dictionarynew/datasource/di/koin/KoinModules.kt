package com.example.dictionarynew.datasource.di.koin

import androidx.room.Room
import com.example.dictionarynew.datasource.retrofit.RetrofitImplementation
import com.example.dictionarynew.datasource.room.RoomDataBaseImplementation
import com.example.dictionarynew.interactor.MainInteractor
import com.example.dictionarynew.model.DataModelDto
import com.example.dictionarynew.model.room.HistoryDataBase
import com.example.dictionarynew.repositiry.IRepositoryLocal
import com.example.dictionarynew.repositiry.Repository
import com.example.dictionarynew.repositiry.RepositoryImplementation
import com.example.dictionarynew.repositiry.RepositoryImplementationLocal
import com.example.dictionarynew.viewmodel.MainViewModel
import org.koin.dsl.module

val application = module {
    // создаём БД
    single {
//        Room.inMemoryDatabaseBuilder(get(), HistoryDataBase::class.java).build()  эта БД будет храниться только в ОП
        Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB")
//        .addMigrations()  можно добавить миграцию
//        .fallbackToDestructiveMigration() удаляет старую версию и устанавливает БД заново. Можно исп. вместо миграции
            .build()
    }
    // Получаем DAO
    single { get<HistoryDataBase>().historyDao() }

    single<Repository<List<DataModelDto>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<IRepositoryLocal<List<DataModelDto>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}



