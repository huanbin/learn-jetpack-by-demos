package com.example.jetpack_demos.room.relationships.manytomany

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Playlist(
    @PrimaryKey val playlistId: Long,
    val playlistName: String
)