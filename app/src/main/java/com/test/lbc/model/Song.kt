package com.test.lbc.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Song(@PrimaryKey val id: Long, val albumId: Long, val title: String, val url: String, val thumbnailUrl: String)