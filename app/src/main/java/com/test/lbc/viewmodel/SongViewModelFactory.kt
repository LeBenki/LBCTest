package com.test.lbc.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.lbc.repository.SongRepository
import com.test.lbc.ui.SongViewModel
import javax.inject.Inject

class SongViewModelFactory @Inject constructor(
    private var application: Application,
    private var songRepository: SongRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            SongViewModel::class.java -> {
                modelClass.getConstructor(
                    Application::class.java,
                    SongRepository::class.java
                )
                    .newInstance(application, songRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
