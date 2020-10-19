package com.test.lbc

import com.test.lbc.api.SongApiService
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun test_get_songs_api() {
        val retrofit  = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://static.leboncoin.fr/img/shared/")
            .build()
        val service = retrofit.create(SongApiService::class.java)

        val courseRequest = service.getSongs()

        val response = courseRequest.execute()
        val songs = response.body()
        if (songs != null) {
            assertEquals(songs.size, 5000)
        } else {
            fail()
        }
    }
}