package com.example.dictionarynew

import android.app.Application
import com.example.dictionarynew.datasource.di.koin.application
import com.example.dictionarynew.datasource.di.koin.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TranslatorApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
//            androidContext(this@TranslatorApp)
        }

    }
}