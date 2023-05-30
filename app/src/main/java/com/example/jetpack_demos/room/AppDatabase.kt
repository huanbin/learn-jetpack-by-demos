//package com.example.jetpack_demos.room
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.jetpack_demos.room.embed.StudentDao
//import com.example.jetpack_demos.room.relation.onetomany.DogDao
//import com.example.jetpack_demos.room.relation.onetoone.UserOneLibraryDao
//import com.example.jetpack_demos.room.relationships.BookDao
//import com.example.jetpack_demos.room.relationships.UserBookDao
//import com.example.jetpack_demos.room.relationships.Users
//import com.example.jetpack_demos.room.relationships.UsersDao
//import com.example.jetpack_demos.room.relationships.manytomany.PlayerListDao
//import com.example.jetpack_demos.room.view.EmployDao
//import com.example.jetpack_demos.room.view.EmployDetail
//
//@Database(
//    //数据库实体（表）
//    entities = [
//        User::class,
//        People::class,
//        Users::class,
//        Book::class,
//        Student::class,
//        UserOne::class,
//        Library::class,
//        Dog::class,
//        Hourse::class,
//        Playlist::class,
//        Song::class,
//        PlaylistSongCrossRef::class,
//        Employ::class,
//        OfficeCode::class,
//    ],
//    //数据库视图
//    views = [
//        EmployDetail::class,
//    ],
//    //数据库版本
//    version = 3,
//    //是否导出数据库架构信息到指定目录（开启之后可以通过room.schemaLocation给处理器配置，导出到指定目录,可以将不同数据库版本的架构信息提交到版本控制系统中去。）
//    exportSchema = true,
//    //数据库迁移
////    autoMigrations =[]
//)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun userDao(): UserDao
//    abstract fun usersDao(): UsersDao
//    abstract fun bookDao(): BookDao
//    abstract fun userBookDao(): UserBookDao
//    abstract fun userOneLibraryDao(): UserOneLibraryDao
//    abstract fun dogDao(): DogDao
//    abstract fun playerListDao(): PlayerListDao
//    abstract fun studentDao(): StudentDao
//    abstract fun employDao(): EmployDao
//}
//
//
//val db_name = "database-name"
//
//fun Context.db() = Room.databaseBuilder(this, AppDatabase::class.java, db_name)
//    // Caused by: java.lang.IllegalStateException: Cannot access database on the main thread since it may potentially lock the UI for a long period of time.
//    //允许在主线程操作数据库（否则在主线程操作数据库报错）
////    .allowMainThreadQueries()
//    //预填充数据库（加载一些表数据进来）
//    .createFromAsset("database-name.db.sql")
////    .createFromFile(File(""))
////    .createFromInputStream()
//    //回退破坏性迁移（没有迁移path），会丢弃现在数据库所有数据，重建数据库，加载预填充数据到数据库
//    .fallbackToDestructiveMigration()
////    .addMigrations()
//
////    .addCallback()
////    .addTypeConverter()
////    .addAutoMigrationSpec()
////    .enableMultiInstanceInvalidation()
////    .fallbackToDestructiveMigrationOnDowngrade()
////    .fallbackToDestructiveMigrationFrom()
////    .openHelperFactory()
////    .setJournalMode()
////    .setAutoCloseTimeout()
////    .setQueryExecutor()
////    .setQueryCallback()
////    .setTransactionExecutor()
////    .setMultiInstanceInvalidationServiceIntent()
//    .build()
