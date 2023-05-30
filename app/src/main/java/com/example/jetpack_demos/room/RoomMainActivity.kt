package com.example.jetpack_demos.room

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack_demos.databinding.ActivityRoomMainBinding
import com.example.jetpack_demos.room.relation.onetomany.Dog
import com.example.jetpack_demos.room.relation.onetomany.DogDao
import com.example.jetpack_demos.room.relation.onetomany.Hourse
import com.example.jetpack_demos.room.relation.onetoone.Library
import com.example.jetpack_demos.room.relation.onetoone.UserOne
import com.example.jetpack_demos.room.relation.onetoone.UserOneLibraryDao
import com.example.jetpack_demos.room.relationships.Book
import com.example.jetpack_demos.room.relationships.BookDao
import com.example.jetpack_demos.room.relationships.UserBookDao
import com.example.jetpack_demos.room.relationships.Users
import com.example.jetpack_demos.room.relationships.UsersDao
import com.example.jetpack_demos.room.relationships.manytomany.PlayerListDao
import com.example.jetpack_demos.room.relationships.manytomany.Playlist
import com.example.jetpack_demos.room.relationships.manytomany.PlaylistSongCrossRef
import com.example.jetpack_demos.room.relationships.manytomany.Song
import kotlin.random.Random

class RoomMainActivity : AppCompatActivity() {

    lateinit var roomMainBinding: ActivityRoomMainBinding

    lateinit var userDao: UserDao
    lateinit var usersDao: UsersDao
    lateinit var bookDao: BookDao
    lateinit var userBookDao: UserBookDao
    lateinit var userOneLibraryDao: UserOneLibraryDao
    lateinit var dogDao: DogDao
    lateinit var playerListDao: PlayerListDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        roomMainBinding = ActivityRoomMainBinding.inflate(layoutInflater)
        setContentView(roomMainBinding.root)

//        val db = db()

//        userDao = db.userDao()
//        usersDao = db.usersDao()
//        bookDao = db.bookDao()
//        userBookDao = db.userBookDao()
//        userOneLibraryDao = db.userOneLibraryDao()
//        dogDao = db.dogDao()
//        playerListDao = db.playerListDao()
//        db.clearAllTables()
//        initDbDatas()
    }

    private fun initDbDatas() {
        val users = List(6) { index -> Users(index + 1, "user$index") }
        usersDao.insertUsers(*users.toTypedArray())
        val books =
            List(10) { index -> Book(index + 1, "booke name-$index", Random.nextInt(6) + 1) }
        bookDao.insertBooks(*books.toTypedArray())


        //添加UserOne和Library数据
        val userOneList = List(4) { index -> UserOne(index + 1, "user-one-name-$index") }
        userOneLibraryDao.insertUserOne(*userOneList.toTypedArray())
        val libraryList = listOf(
            Library(1, "library-name-1", 3),
            Library(2, "library-name-2", 2),
            Library(3, "library-name-3", 1),
            Library(4, "library-name-4", 4),
        )
        userOneLibraryDao.insertLirary(*libraryList.toTypedArray())


        //添加Dog 和Hourse数据
        dogDao.inserHourses(
            *arrayOf(
                Hourse(1L, "hourse-1", 1L),
                Hourse(2L, "hourse-2", 3L),
                Hourse(3L, "hourse-3", 2L),
                Hourse(4L, "hourse-4", 1L),
                Hourse(5L, "hourse-5", 1L),
                Hourse(6L, "hourse-6", 2L),
            )
        )
        dogDao.inserDogs(
            *arrayOf(
                Dog(1, "dog-1"),
                Dog(2, "dog-2"),
                Dog(3, "dog-3"),
            )
        )
        //添加playerlist和song
        playerListDao.insertSong(
            *arrayOf(
                Song(1, "第一次", "薛晓风"),
                Song(2, "青花", "周传雄"),
                Song(3, "红尘来去一场空", "吉他的天空"),
                Song(4, "兄弟努力吧", "耀阳一"),
            )
        )

        playerListDao.insertPlayerList(
            *arrayOf(
                Playlist(1, "努力"),
                Playlist(2, "怀念"),
                Playlist(3, "红尘"),
            )
        )

        playerListDao.insertPlayerSongRef(
            *arrayOf(
                PlaylistSongCrossRef(1, 1),
                PlaylistSongCrossRef(1, 2),
                PlaylistSongCrossRef(1, 3),
                PlaylistSongCrossRef(1, 4),
                PlaylistSongCrossRef(2, 2),
                PlaylistSongCrossRef(2, 3),
                PlaylistSongCrossRef(3, 4),
            )
        )
    }

    fun delete(view: View) {
        userDao.delete(User(3, "json2", "yang"))
    }

    fun insert(view: View) {
        val userAll = buildList<User> {
            add(User(1, "json", "yang"))
            add(User(2, "json1", "yang1"))
            add(User(3, "json2", "yang"))
            add(User(4, "json3", "yang1"))
        }
        userDao.insertAll(users = userAll.toTypedArray())
    }

    fun queryAll(view: View) {
        val all = userDao.getAll()
        println(all)
    }

    fun queryById(view: View) {
        val all = userDao.loadAllByIds(intArrayOf(1))
        println(all)
    }

    fun moreTableSelect(view: View) {

        val loadUserAndBookNames = userBookDao.loadUserAndBookNames()
        println("loadUserAndBookNames=$loadUserAndBookNames")


        val mapResult = userBookDao.loadUserAndBookNames2()
        mapResult.forEach { (t, u) ->
            println("key:$t ,value:$u")
        }
    }

    fun oneToOneTableSelect(view: View) {
        val userOneList = userOneLibraryDao.getUserOne()
        userOneList.forEach {
            println(it)
        }
    }

    fun oneToOneTableSelect2(view: View) {
        val userOneList = userOneLibraryDao.getUserOne2()
        userOneList.forEach {
            println(it)
        }
    }

    fun oneToManyTableSelect3(view: View) {

        val dogList = dogDao.getDog()
        dogList.forEach {
            println(it)
        }

        val hourseList = dogDao.getHourseList(1)
        hourseList.forEach {
            println("${it.key}")
            println("${it.value}")
        }
    }

    fun oneToManyTableSelect4(view: View) {
        val playlistsWithSongs = playerListDao.getPlaylistsWithSongs()
        playlistsWithSongs.forEach {
            println(it)
        }
    }

}