package spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks

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
import spiral.bit.dev.gamificationnote.data.sources.local.database.TaskDatabase

@OptIn(ExperimentalCoroutinesApi::class)
@MediumTest
class FolderDaoTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testCoroutineDispatcher)
    private lateinit var taskDatabase: TaskDatabase
    private lateinit var folderDao: FolderDao

    @Before
    fun setUp() {
        taskDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TaskDatabase::class.java
        ).setQueryExecutor(testCoroutineDispatcher.asExecutor()).build()
        folderDao = taskDatabase.folderDao()
    }

    @Test
    fun insertFolderAndObserveIt_returnTrue() = testScope.runBlockingTest {
        with(folderDao) {
            val folder = TaskTestUtils.createFolder()
            insert(folder)
            getAllFolders().test {
                assert(expectMostRecentItem().contains(folder))
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun deleteFolderAndObserveIt_returnTrue() = testScope.runBlockingTest {
        with(folderDao) {
            val folder = TaskTestUtils.createFolder()
            insert(folder)
            delete(folder)
            getAllFolders().test {
                assertThat(expectMostRecentItem()).doesNotContain(folder)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun updateFolderAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val folderEntity = TaskTestUtils.createFolder()
        val updatedFolderEntity = TaskTestUtils.createFolder(name = "New folder")

        with(folderDao) {
            insert(folderEntity)
            update(updatedFolderEntity)
            getAllFolders().test {
                assertThat(expectMostRecentItem()[0]).isEqualTo(updatedFolderEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @After
    fun tearDown() {
        taskDatabase.close()
    }
}