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
class WeaponDaoTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testCoroutineDispatcher)
    private lateinit var shopDatabase: ShopDatabase
    private lateinit var weaponDao: WeaponDao

    @Before
    fun setUp() {
        shopDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShopDatabase::class.java
        ).setQueryExecutor(testCoroutineDispatcher.asExecutor()).build()
        weaponDao = shopDatabase.weaponDao()
    }

    @Test
    fun insertWeaponAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val weaponEntity = ShopTestUtils.createWeapon()

        with(weaponDao) {
            insert(weaponEntity)
            getWeapons(isFromInventory = true).test {
                assert(expectMostRecentItem().contains(weaponEntity))
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun deleteWeaponAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val weaponEntity = ShopTestUtils.createWeapon()

        with(weaponDao) {
            insert(weaponEntity)
            delete(weaponEntity)
            getWeapons(isFromInventory = true).test {
                assertThat(expectMostRecentItem()).doesNotContain(weaponEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun updateWeaponAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val weaponEntity = ShopTestUtils.createWeapon()
        val updatedWeaponEntity = ShopTestUtils.createWeapon(
            name = "New Weapon"
        )

        with(weaponDao) {
            insert(weaponEntity)
            update(updatedWeaponEntity)
            getWeapons(isFromInventory = true).test {
                assertThat(expectMostRecentItem()[0]).isEqualTo(updatedWeaponEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @After
    fun tearDown() {
        shopDatabase.close()
    }
}