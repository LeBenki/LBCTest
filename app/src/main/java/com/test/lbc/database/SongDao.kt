package com.test.lbc.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.lbc.model.Song

@Dao
interface SongDao {
    @Query("SELECT * FROM Song ORDER BY title")
    fun allSongsByTitle(): PagingSource<Int, Song>

    @Query("SELECT * FROM Song ORDER BY albumId")
    fun allSongsByAlbum(): PagingSource<Int, Song>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(songs: List<Song>)
}
