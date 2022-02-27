package com.example.dictionarynew.datasource.di.koin

import android.util.Log
import androidx.room.Room
import com.example.dictionarynew.MainActivity
import com.example.dictionarynew.interactor.MainInteractor
import com.example.dictionarynew.view.MainViewModel
import com.example.history.HistoryActivity
import com.example.model.DataModelDto
import com.example.repository.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun injectDependencies() = loadModules

private val loadModules by lazy {
    loadKoinModules(listOf(application, mainScreen))
}



val application = module {

        single {
            Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB")
//        .addMigrations()  можно добавить миграцию
//        .fallbackToDestructiveMigration() удаляет старую версию и устанавливает БД заново. Можно исп. вместо миграции
                .build()
        }

//    scope(named<MainActivity>()){
//        scoped {
//            Log.d("создание bd", "HistoryDataBase - 1")
//            Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build()
//        }
//    }

    single { get<HistoryDataBase>().historyDao() }

        // создаём БД
        // Получаем DAO

    single<Repository<List<DataModelDto>>> {
            RepositoryImplementation(
                RetrofitImplementation()
            )
        }

    single<IRepositoryLocal<List<DataModelDto>>> {
            RepositoryImplementationLocal(
                RoomDataBaseImplementation(
                    get()
                )
            )
        }
}

//val mainScreen = module {
//    factory { MainViewModel(get()) }
//    factory { MainInteractor(get(), get()) }
//}

val mainScreen = module {
    scope(named<MainActivity>()) {
        scoped { MainInteractor(get(), get()) }
        viewModel { MainViewModel(get()) }
    }
}



