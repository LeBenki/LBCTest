package com.test.lbc.api

import com.test.lbc.model.Song
import retrofit2.Call
import retrofit2.http.GET

interface SongApiService {
    @GET("technical-test.json")
    fun getSongs(): Call<List<Song>>
}