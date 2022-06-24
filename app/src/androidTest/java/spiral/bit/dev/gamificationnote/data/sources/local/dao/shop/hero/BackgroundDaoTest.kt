package spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.hero

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
class BackgroundDaoTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testCoroutineDispatcher)
    private lateinit var shopDatabase: ShopDatabase
    private lateinit var backgroundDao: BackgroundDao

    @Before
    fun setUp() {
        shopDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShopDatabase::class.java
        ).setQueryExecutor(testCoroutineDispatcher.asExecutor()).build()
        backgroundDao = shopDatabase.backgroundDao()
    }

    @Test
    fun insertArmorAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val backgroundEntity = ShopTestUtils.createBackground()

        with(backgroundDao) {
            insert(backgroundEntity)
            getBackgrounds(isFromInventory = true).test {
                assert(expectMostRecentItem().contains(backgroundEntity))
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun deleteBackgroundAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val backgroundEntity = ShopTestUtils.createBackground()

        with(backgroundDao) {
            insert(backgroundEntity)
            delete(backgroundEntity)
            getBackgrounds(isFromInventory = true).test {
                assertThat(expectMostRecentItem()).doesNotContain(backgroundEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun updateBackgroundAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val backgroundEntity = ShopTestUtils.createBackground()
        val updatedBackgroundEntity = ShopTestUtils.createBackground(
            name = "New background name"
        )

        with(backgroundDao) {
            insert(backgroundEntity)
            update(updatedBackgroundEntity)
            getBackgrounds(isFromInventory = true).test {
                assertThat(expectMostRecentItem()[0]).isEqualTo(updatedBackgroundEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @After
    fun tearDown() {
        shopDatabase.close()
    }
}