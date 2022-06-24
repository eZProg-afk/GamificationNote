package spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.appFeatures

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
class AppFeatureDaoTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testCoroutineDispatcher)
    private lateinit var shopDatabase: ShopDatabase
    private lateinit var appFeatureDao: AppFeatureDao

    @Before
    fun setUp() {
        shopDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShopDatabase::class.java
        ).setQueryExecutor(testCoroutineDispatcher.asExecutor()).build()
        appFeatureDao = shopDatabase.appFeatureDao()
    }

    @Test
    fun insertAppFeatureAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val appFeatureEntity = ShopTestUtils.createAppFeature()

        with(appFeatureDao) {
            insert(appFeatureEntity)
            getAppFeatures(isFromInventory = true).test {
                assert(expectMostRecentItem().contains(appFeatureEntity))
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun deleteAppFeatureAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val appFeatureEntity = ShopTestUtils.createAppFeature()

        with(appFeatureDao) {
            insert(appFeatureEntity)
            delete(appFeatureEntity)
            getAppFeatures(isFromInventory = true).test {
                assertThat(expectMostRecentItem()).doesNotContain(appFeatureEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun updateAppFeatureAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val appFeatureEntity = ShopTestUtils.createAppFeature()
        val updatedAppFeatureEntity = ShopTestUtils.createAppFeature(
            name = "Another app feature"
        )

        with(appFeatureDao) {
            insert(appFeatureEntity)
            update(updatedAppFeatureEntity)
            getAppFeatures(isFromInventory = true).test {
                assertThat(expectMostRecentItem()[0]).isEqualTo(updatedAppFeatureEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @After
    fun tearDown() {
        shopDatabase.close()
    }
}