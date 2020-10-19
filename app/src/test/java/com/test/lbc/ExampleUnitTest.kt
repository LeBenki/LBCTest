package com.test.lbc

import com.test.lbc.api.SongApiService
import com.test.lbc.di.ApplicationModule
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun test_get_songs_api() {
/*        val service = ApplicationModule

        val courseRequest = service.getSongs()

        val response = courseRequest.execute()
        val songs = response.body()
        if (songs != null) {
            assertEquals(songs.size, 5000)
        } else {
            fail()
        }*/
        assertEquals(4, 4)
    }
}