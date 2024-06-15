package com.apps.kunalfarmah.moviebuffcompose.di

import androidx.room.Room
import com.apps.kunalfarmah.moviebuffcompose.database.FavouritesDatabase
import com.apps.kunalfarmah.moviebuffcompose.database.MoviesDatabase
import com.apps.kunalfarmah.moviebuffcompose.repository.MoviesRepository
import com.apps.kunalfarmah.moviebuffcompose.retrofit.MovieRetrofit
import com.apps.kunalfarmah.moviebuffcompose.util.Constants
import com.apps.kunalfarmah.moviebuffcompose.viewmodel.MoviesViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single{
        // will pick up retrofit instance using retrofitModule
        MoviesRepository(get(),get())
    }
    single{
        // will pick up repository from above
        MoviesViewModel(get())
    }
}

val retrofitModule = module {
    single{
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(MovieRetrofit::class.java)
    }
}

val roomModule = module {
    single{
        Room.databaseBuilder(
            androidContext(),
            MoviesDatabase::class.java, "movies.db"
        ).build()
    }

    single{
        Room.databaseBuilder(
            androidContext(),
            FavouritesDatabase::class.java, "favourites.db"
        ).build()
    }

}