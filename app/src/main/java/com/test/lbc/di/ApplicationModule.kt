package com.test.lbc.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.test.lbc.api.SongApiService
import com.test.lbc.database.SongDb
import com.test.lbc.repository.SongRepository
import com.test.lbc.viewmodel.SongViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
open class ApplicationModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Singleton
    @Provides
    open fun provideSongApi(): SongApiService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://static.leboncoin.fr/img/shared/")
            .build()
        return retrofit.create(SongApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideSongRepository(songApiService: SongApiService, executor: Executor): SongRepository {
        val database = SongDb.getInstance(application)
        return SongRepository(database.songDao(), songApiService, executor)
    }

    @Singleton
    @Provides
    fun provideExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(
        userDataSource: SongRepository,
        application: Application
    ): ViewModelProvider.Factory {
        return SongViewModelFactory(application, userDataSource)
    }
}
