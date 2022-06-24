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
class InFolderCompositeTaskDaoTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testCoroutineDispatcher)
    private lateinit var taskDatabase: TaskDatabase
    private lateinit var folderDao: FolderDao
    private lateinit var inFolderCompositeTaskDao: InFolderCompositeTaskDao

    @Before
    fun setUp() {
        taskDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TaskDatabase::class.java
        ).setQueryExecutor(testCoroutineDispatcher.asExecutor()).build()
        folderDao = taskDatabase.folderDao()
        inFolderCompositeTaskDao = taskDatabase.inFolderCompositeTaskDao()
    }

    @Test
    fun insertInFolderCompositeTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        with(inFolderCompositeTaskDao) {
            val inFolderCompositeTaskEntity = TaskTestUtils.createInFolderCompositeTask()
            folderDao.insert(TaskTestUtils.createFolder())
            insert(inFolderCompositeTaskEntity)
            getAllInFolderCompositeTasks(folderId = 1).test {
                assert(expectMostRecentItem().contains(inFolderCompositeTaskEntity))
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun deleteInFolderCompositeTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        with(inFolderCompositeTaskDao) {
            val inFolderCompositeTaskEntity = TaskTestUtils.createInFolderCompositeTask()
            folderDao.insert(TaskTestUtils.createFolder())
            insert(inFolderCompositeTaskEntity)
            delete(inFolderCompositeTaskEntity)
            getAllInFolderCompositeTasks(folderId = 1).test {
                assertThat(expectMostRecentItem()).doesNotContain(inFolderCompositeTaskEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun updateInFolderCompositeTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val inFolderCompositeTaskEntity = TaskTestUtils.createInFolderCompositeTask()
        val updatedInFolderCompositeTaskEntity = TaskTestUtils.createInFolderCompositeTask(
            name = "New folder"
        )

        with(inFolderCompositeTaskDao) {
            folderDao.insert(TaskTestUtils.createFolder())
            insert(inFolderCompositeTaskEntity)
            update(updatedInFolderCompositeTaskEntity)
            getAllInFolderCompositeTasks(folderId = 1).test {
                assertThat(expectMostRecentItem()[0]).isEqualTo(updatedInFolderCompositeTaskEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @After
    fun tearDown() {
        taskDatabase.close()
    }
}