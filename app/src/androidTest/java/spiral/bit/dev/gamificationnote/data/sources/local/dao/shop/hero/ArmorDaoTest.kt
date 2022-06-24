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
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.hero.ArmorEntity

@OptIn(ExperimentalCoroutinesApi::class)
@MediumTest
class ArmorDaoTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testCoroutineDispatcher)
    private lateinit var shopDatabase: ShopDatabase
    private lateinit var armorDao: ArmorDao

    @Before
    fun setUp() {
        shopDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShopDatabase::class.java
        ).setQueryExecutor(testCoroutineDispatcher.asExecutor()).build()
        armorDao = shopDatabase.armorDao()
    }

    @Test
    fun insertArmorAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val armorEntity = ShopTestUtils.createArmor()

        with(armorDao) {
            insert(armorEntity)
            getArmor(isFromInventory = true).test {
                assert(expectMostRecentItem().contains(armorEntity))
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun deleteArmorAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val armorEntity = ShopTestUtils.createArmor()

        with(armorDao) {
            insert(armorEntity)
            delete(armorEntity)
            getArmor(isFromInventory = true).test {
                assertThat(expectMostRecentItem()).doesNotContain(armorEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun updateArmorAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val armorEntity = ShopTestUtils.createArmor()
        val updatedArmorEntity = ShopTestUtils.createArmor(
            name = "New Armor"
        )

        with(armorDao) {
            insert(armorEntity)
            update(updatedArmorEntity)
            getArmor(isFromInventory = true).test {
                assertThat(expectMostRecentItem()[0]).isEqualTo(updatedArmorEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @After
    fun tearDown() {
        shopDatabase.close()
    }
}