package com.example.jetpack_demos.room.migrations

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameTable
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.jetpack_demos.room.converter.Converters

private const val DatabaseVersion = 3

//注意自动迁移本质是从生成的schema中获得迁移信息的，
// 所以说你得现在表Entity类中修改声明，再配合自动迁移注解，完成迁移。
//演示数据库迁移的
@Database(
    //数据库实体（表）
    entities = [
        Animals::class,
        Categories::class,
        Song::class,
    ],
    //数据库版本
    //6改列名称
    //7加一张新表
    version = DatabaseVersion,
    //自动迁移
    autoMigrations = [
        AutoMigration(1, 2, AppDatabase2.MyAutoMigration::class),
        //测试添加新列
        AutoMigration(2, 3),
    ],
    exportSchema = true
)
//类型转换器，这里是Room负责实例化类型转换器
@TypeConverters(value = [Converters::class])
abstract class AppDatabase2 : RoomDatabase() {


    abstract fun animalDao(): AnimalDao

    /**
     * 自动迁移规格
     */
    @RenameTable(fromTableName = "Song", toTableName = "songs")
    class MyAutoMigration : AutoMigrationSpec {

        //在当前自动迁移完成之后，执行的动作
        override fun onPostMigrate(db: SupportSQLiteDatabase) {
            super.onPostMigrate(db)
            print("MyAutoMigration success complete!")
        }
    }
}


const val db_name = "database-migration"

//val migrations = arrayOf(
//    object : Migration(3, 4) {
//        override fun migrate(database: SupportSQLiteDatabase) {
//            val values1 =
//                contentValuesOf(
//                    "id" to 2,
//                    "name" to "dog类"
//                )
//            val values2 = contentValuesOf(
//                "id" to 3,
//                "name" to "cat类"
//            )
//            database.insert("Categories", SQLiteDatabase.CONFLICT_REPLACE, values1)
//            database.insert("Categories", SQLiteDatabase.CONFLICT_REPLACE, values2)
//        }
//    }, object : Migration(4, 5) {
//        override fun migrate(database: SupportSQLiteDatabase) {
//            val values1 =
//                contentValuesOf(
//                    "id" to 1,
//                    "name" to "比特犬",
//                    "category_id" to 2
//                )
//            val values2 = contentValuesOf(
//                "id" to 2,
//                "name" to "牧羊犬",
//                "category_id" to 2
//            )
//            database.insert("Animals", SQLiteDatabase.CONFLICT_REPLACE, values1)
//            database.insert("Animals", SQLiteDatabase.CONFLICT_REPLACE, values2)
//        }
//    }, object : Migration(10, 11) {
//        override fun migrate(database: SupportSQLiteDatabase) {
//            database.execSQL("create table if not exists user(id long primary key,name text not null,birth_day long default 0)")
//        }
//    }, object : Migration(5, 11) {
//        override fun migrate(database: SupportSQLiteDatabase) {
//
//        }
//    }
//)

val callback = object : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        println("callback onCreate")
    }

    override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
        super.onDestructiveMigration(db)
        println("callback onDestructiveMigration")
    }

    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
        println("callback onOpen")
    }
}

fun Context.db2() = Room.databaseBuilder(this, AppDatabase2::class.java, db_name)
    // Caused by: java.lang.IllegalStateException: Cannot access database on the main thread since it may potentially lock the UI for a long period of time.
    //允许在主线程操作数据库（否则在主线程操作数据库报错）
    .allowMainThreadQueries()
    //预填充数据库（加载一些表数据进来）
//    .createFromAsset("test.db")
//    .createFromFile(File(""))
//    .createFromInputStream()
    //回退破坏性迁移（没有迁移path时自动启用），会丢弃现在数据库所有数据，重建数据库，可能加载预打包数据库填充到数据库（当预打包数据库文件与app中@Database中的版本相同时，会加载数据）
//    .fallbackToDestructiveMigration()
//   当数据库进行降级(迁移不可用时),回退破坏性迁移
//    .fallbackToDestructiveMigrationOnDowngrade()
//   当数据库从有问题的版本中进行迁移时，使用回退破坏性迁移
//    .fallbackToDestructiveMigrationFrom(1, 2, 3)//没有测试
//  添加迁移path，手动迁移（针对比较复杂的迁移情况）
//    .addMigrations(*migrations)
//        添加自动迁移规格（针对简单的迁移，4个注解）,这里与注解中的autoMigrations有什么区别？？？
//    .addAutoMigrationSpec(AppDatabase2.MyAutoMigration())
    .addCallback(callback)
    //添加类型转换器，与@TypeConverters注解作用基本一样
    // 但是这里是自己控制转换器的实例化
//    .addTypeConverter(Converters())
//    .enableMultiInstanceInvalidation()
//    .openHelperFactory()
//    .setJournalMode()
//    .setAutoCloseTimeout()
//    .setQueryExecutor()
//    .setQueryCallback()
//    .setTransactionExecutor()
//    .setMultiInstanceInvalidationServiceIntent()
    .build()
