package com.test.lbc.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.test.lbc.api.SongApiService
import com.test.lbc.database.SongDao
import com.test.lbc.model.Song
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class SongRepository(
    private val songDao: SongDao,
    private val service: SongApiService,
    private val executor: Executor
) {

    fun insert(songs: List<Song>) {
        songDao.insert(songs)
    }

    fun getAllSongsBy(sorting: SongSorting): LiveData<Flow<PagingData<Song>>> {
        val pager = Pager(PagingConfig(pageSize = 60, enablePlaceholders = true, maxSize = 200)) {
            when (sorting) {
                SongSorting.BY_ALBUM -> songDao.allSongsByAlbum()
                SongSorting.BY_TITLE -> songDao.allSongsByTitle()
            }
        }.flow
        return MutableLiveData(pager)
    }

    fun fetchSongs() {
        val courseRequest = service.getSongs()

        courseRequest.enqueue(object : Callback<List<Song>> {
            override fun onResponse(call: Call<List<Song>>, response: Response<List<Song>>) {
                val songs = response.body()
                if (songs != null)
                    executor.execute {
                        insert(songs)
                    }
            }
            override fun onFailure(call: Call<List<Song>>, t: Throwable) {
            }
        })
    }
}