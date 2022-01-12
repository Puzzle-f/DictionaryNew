package com.example.dictionarynew.datasource.di.koin

import com.example.dictionarynew.datasource.retrofit.RetrofitImplementation
import com.example.dictionarynew.datasource.room.RoomDataBaseImplementation
import com.example.dictionarynew.interactor.MainInteractor
import com.example.dictionarynew.model.DataModel
import com.example.dictionarynew.repositiry.Repository
import com.example.dictionarynew.repositiry.RepositoryImplementation
import com.example.dictionarynew.viewmodel.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

// Для удобства создадим две переменные: в одной находятся зависимости,
// используемые во всём приложении, во второй - зависимости конкретного экрана
val application = module {
    // Функция single сообщает Koin, что эта зависимость должна храниться
    // в виде синглтона (в Dagger есть похожая аннотация)
    // Аннотация named выполняет аналогичную Dagger функцию
//    <Repository<List<DataModel>>> - тип, к которому должен быть приведён результат
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) { RepositoryImplementation(
        RetrofitImplementation()
    ) }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) { RepositoryImplementation(
        RoomDataBaseImplementation()
    ) }
}
// Функция factory сообщает Koin, что эту зависимость нужно создавать каждый
// раз заново, что как раз подходит для Activity и её компонентов.
val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}
