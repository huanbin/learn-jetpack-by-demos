package c
import androidx.room.migration.Migration
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.platform.app.InstrumentationRegistry
import com.example.jetpack_demos.room.migrations.AppDatabase2
import com.example.jetpack_demos.room.migrations.db_name
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
//@RunWith(AndroidJUnit4::class)
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @get:Rule
    val testHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppDatabase2::class.java,
        emptyList(),
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    fun testMigration1() {
        var db = testHelper.createDatabase(db_name, 2)
        val MIGRATION_1_11 = object : Migration(1, 11) {
            override fun migrate(database: SupportSQLiteDatabase) {
                println("Migration 1 to 11")
            }
        }
        db = testHelper.runMigrationsAndValidate(db_name, 4, false, MIGRATION_1_11);
    }
}