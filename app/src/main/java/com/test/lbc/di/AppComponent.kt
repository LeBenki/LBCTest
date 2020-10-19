package com.test.lbc.di

import com.test.lbc.viewmodel.SongViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface AppComponent {
    val viewModelFactory: SongViewModelFactory?
}