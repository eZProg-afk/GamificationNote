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
class EffectDaoTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testCoroutineDispatcher)
    private lateinit var shopDatabase: ShopDatabase
    private lateinit var effectDao: EffectDao

    @Before
    fun setUp() {
        shopDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShopDatabase::class.java
        ).setQueryExecutor(testCoroutineDispatcher.asExecutor()).build()
        effectDao = shopDatabase.effectDao()
    }

    @Test
    fun insertEffectAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val effectEntity = ShopTestUtils.createEffect()

        with(effectDao) {
            insert(effectEntity)
            getEffects(isFromInventory = true).test {
                assert(expectMostRecentItem().contains(effectEntity))
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun deleteEffectAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val effectEntity = ShopTestUtils.createEffect()

        with(effectDao) {
            insert(effectEntity)
            delete(effectEntity)
            getEffects(isFromInventory = true).test {
                assertThat(expectMostRecentItem()).doesNotContain(effectEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun updateEffectAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val effectEntity = ShopTestUtils.createEffect()
        val updatedEffectEntity = ShopTestUtils.createEffect(
            name = "Another effect"
        )

        with(effectDao) {
            insert(effectEntity)
            update(updatedEffectEntity)
            getEffects(isFromInventory = true).test {
                assertThat(expectMostRecentItem()[0]).isEqualTo(updatedEffectEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @After
    fun tearDown() {
        shopDatabase.close()
    }
}