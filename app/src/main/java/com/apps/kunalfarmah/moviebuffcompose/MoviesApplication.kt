package com.apps.kunalfarmah.moviebuffcompose

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.apps.kunalfarmah.moviebuffcompose.di.appModule
import com.apps.kunalfarmah.moviebuffcompose.di.retrofitModule
import com.apps.kunalfarmah.moviebuffcompose.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoviesApplication: Application() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        startKoin {
            androidContext(this@MoviesApplication)
            androidLogger()
            modules(appModule, roomModule, retrofitModule)
        }
    }
}