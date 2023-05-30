package com.example.jetpack_demos.room.relationships.manytomany

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface PlayerListDao {

    @Insert
    fun insertPlayerList(vararg playlist: Playlist)

    @Insert
    fun insertSong(vararg song: Song)

    @Insert
    fun insertPlayerSongRef(vararg playlistSongCrossRef: PlaylistSongCrossRef)


    @Transaction
    @Query("SELECT * FROM Playlist")
    fun getPlaylistsWithSongs(): List<PlaylistWithSongs>

    @Transaction
    @Query("SELECT * FROM Song")
    fun getSongsWithPlaylists(): List<SongWithPlaylists>
}