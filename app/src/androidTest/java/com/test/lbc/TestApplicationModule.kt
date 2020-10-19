package com.test.lbc

import android.app.Application
import com.test.lbc.api.SongApiService
import com.test.lbc.di.ApplicationModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class TestApplicationModule(application: Application) : ApplicationModule(application) {
    @Provides
    @Singleton
    override fun provideSongApi(): SongApiService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://localhost:8080/")
            .build()
        return retrofit.create(SongApiService::class.java)
    }
}