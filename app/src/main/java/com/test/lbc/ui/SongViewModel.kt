package com.test.lbc.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagingData
import com.test.lbc.model.Song
import com.test.lbc.repository.SongRepository
import com.test.lbc.repository.SongSorting
import kotlinx.coroutines.flow.Flow

class SongViewModel(application: Application,
                    private val songRepository: SongRepository) : AndroidViewModel(application) {

    private val sortType = MutableLiveData(SongSorting.BY_ALBUM)

    val allSongs: LiveData<Flow<PagingData<Song>>> = Transformations.switchMap(sortType) {
        songRepository.getAllSongsBy(it)
    }

    fun fetchSongs() {
        songRepository.fetchSongs()
    }

    fun setSortType(sort: SongSorting) {
        sortType.value = sort
    }
}