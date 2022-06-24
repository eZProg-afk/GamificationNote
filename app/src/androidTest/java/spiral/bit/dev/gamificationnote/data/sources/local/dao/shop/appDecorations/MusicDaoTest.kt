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
class MusicDaoTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testCoroutineDispatcher)
    private lateinit var shopDatabase: ShopDatabase
    private lateinit var musicDao: MusicDao

    @Before
    fun setUp() {
        shopDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShopDatabase::class.java
        ).setQueryExecutor(testCoroutineDispatcher.asExecutor()).build()
        musicDao = shopDatabase.musicDao()
    }

    @Test
    fun insertMusicAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val musicEntity = ShopTestUtils.createMusic()

        with(musicDao) {
            insert(musicEntity)
            getMusic(isFromInventory = true).test {
                assert(expectMostRecentItem().contains(musicEntity))
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun deleteMusicAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val musicEntity = ShopTestUtils.createMusic()

        with(musicDao) {
            insert(musicEntity)
            delete(musicEntity)
            getMusic(isFromInventory = true).test {
                assertThat(expectMostRecentItem()).doesNotContain(musicEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun updateMusicAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val musicEntity = ShopTestUtils.createMusic()
        val updatedMusicEntity = ShopTestUtils.createMusic(
            name = "New music"
        )

        with(musicDao) {
            insert(musicEntity)
            update(updatedMusicEntity)
            getMusic(isFromInventory = true).test {
                assertThat(expectMostRecentItem()[0]).isEqualTo(updatedMusicEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @After
    fun tearDown() {
        shopDatabase.close()
    }
}