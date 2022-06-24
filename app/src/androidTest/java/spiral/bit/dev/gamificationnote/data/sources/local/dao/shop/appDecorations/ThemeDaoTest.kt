package spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.appDecorations

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.MediumTest
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.ShopTestUtils
import spiral.bit.dev.gamificationnote.data.sources.local.database.ShopDatabase

@OptIn(ExperimentalCoroutinesApi::class)
@MediumTest
class ThemeDaoTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testCoroutineDispatcher)
    private lateinit var shopDatabase: ShopDatabase
    private lateinit var themeDao: ThemeDao

    @Before
    fun setUp() {
        shopDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShopDatabase::class.java
        ).setQueryExecutor(testCoroutineDispatcher.asExecutor()).build()
        themeDao = shopDatabase.themeDao()
    }

    @Test
    fun insertThemeAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val themeEntity = ShopTestUtils.createTheme()

        with(themeDao) {
            insert(themeEntity)
            getThemes(isFromInventory = true).test {
                assert(expectMostRecentItem().contains(themeEntity))
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun deleteThemeAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val themeEntity = ShopTestUtils.createTheme()

        with(themeDao) {
            insert(themeEntity)
            delete(themeEntity)
            getThemes(isFromInventory = true).test {
                assertThat(expectMostRecentItem()).doesNotContain(themeEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun updateThemeAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val themeEntity = ShopTestUtils.createTheme()
        val updatedThemeEntity = ShopTestUtils.createTheme(
            name = "New Theme"
        )

        with(themeDao) {
            insert(themeEntity)
            update(updatedThemeEntity)
            getThemes(isFromInventory = true).test {
                assertThat(expectMostRecentItem()[0]).isEqualTo(updatedThemeEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @After
    fun tearDown() {
        shopDatabase.close()
    }
}