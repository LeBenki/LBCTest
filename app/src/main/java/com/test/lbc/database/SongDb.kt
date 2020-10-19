package com.test.lbc.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.lbc.model.Song

@Database(entities = [Song::class], version = 1)
abstract class SongDb: RoomDatabase() {

    abstract fun songDao(): SongDao

    companion object {
        private var instance: SongDb? = null

        const val DB_NAME = "SongDatabase"

        @Synchronized
        fun getInstance(context: Context): SongDb {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    SongDb::class.java, DB_NAME
                ).build()
            }
            return instance!!
        }
    }
}