package spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks.inFolder

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
import spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks.FolderDao
import spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks.TaskTestUtils
import spiral.bit.dev.gamificationnote.data.sources.local.database.TaskDatabase

@OptIn(ExperimentalCoroutinesApi::class)
@MediumTest
class InFolderSimpleTaskDaoTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testCoroutineDispatcher)
    private lateinit var taskDatabase: TaskDatabase
    private lateinit var folderDao: FolderDao
    private lateinit var inFolderSimpleTaskDao: InFolderSimpleTaskDao

    @Before
    fun setUp() {
        taskDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TaskDatabase::class.java
        ).setQueryExecutor(testCoroutineDispatcher.asExecutor()).build()
        folderDao = taskDatabase.folderDao()
        inFolderSimpleTaskDao = taskDatabase.inFolderSimpleTaskDao()
    }

    @Test
    fun insertInFolderSimpleTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        with(inFolderSimpleTaskDao) {
            val inFolderSimpleTaskEntity = TaskTestUtils.createInFolderSimpleTask()
            folderDao.insert(TaskTestUtils.createFolder())
            insert(inFolderSimpleTaskEntity)
            getAllInFolderSimpleTasks(folderId = 1).test {
                assert(expectMostRecentItem().contains(inFolderSimpleTaskEntity))
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun deleteInFolderSimpleTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        with(inFolderSimpleTaskDao) {
            val inFolderSimpleTaskEntity = TaskTestUtils.createInFolderSimpleTask()
            folderDao.insert(TaskTestUtils.createFolder())
            insert(inFolderSimpleTaskEntity)
            delete(inFolderSimpleTaskEntity)
            getAllInFolderSimpleTasks(folderId = 1).test {
                assertThat(expectMostRecentItem()).doesNotContain(inFolderSimpleTaskEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun updateInFolderSimpleTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val inFolderSimpleTaskEntity = TaskTestUtils.createInFolderSimpleTask()
        val updatedInFolderSimpleTaskEntity = TaskTestUtils.createInFolderSimpleTask(name = "New folder")

        with(inFolderSimpleTaskDao) {
            folderDao.insert(TaskTestUtils.createFolder())
            insert(inFolderSimpleTaskEntity)
            update(updatedInFolderSimpleTaskEntity)
            getAllInFolderSimpleTasks(folderId = 1).test {
                assertThat(expectMostRecentItem()[0]).isEqualTo(updatedInFolderSimpleTaskEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @After
    fun tearDown() {
        taskDatabase.close()
    }
}