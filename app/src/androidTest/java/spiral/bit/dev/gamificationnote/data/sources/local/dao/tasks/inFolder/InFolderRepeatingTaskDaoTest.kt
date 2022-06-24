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
class InFolderRepeatingTaskDaoTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testCoroutineDispatcher)
    private lateinit var taskDatabase: TaskDatabase
    private lateinit var folderDao: FolderDao
    private lateinit var inFolderRepeatingTaskDao: InFolderRepeatingTaskDao

    @Before
    fun setUp() {
        taskDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TaskDatabase::class.java
        ).setQueryExecutor(testCoroutineDispatcher.asExecutor()).build()
        folderDao = taskDatabase.folderDao()
        inFolderRepeatingTaskDao = taskDatabase.inFolderRepeatingTaskDao()
    }

    @Test
    fun insertInFolderRepeatingTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        with(inFolderRepeatingTaskDao) {
            val inFolderRepeatingTaskEntity = TaskTestUtils.createInFolderRepeatingTask()
            folderDao.insert(TaskTestUtils.createFolder())
            insert(inFolderRepeatingTaskEntity)
            getAllInFolderRepeatingTasks(folderId = 1).test {
                assert(expectMostRecentItem().contains(inFolderRepeatingTaskEntity))
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun deleteInFolderRepeatingTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        with(inFolderRepeatingTaskDao) {
            val inFolderRepeatingTaskEntity = TaskTestUtils.createInFolderRepeatingTask()
            folderDao.insert(TaskTestUtils.createFolder())
            insert(inFolderRepeatingTaskEntity)
            delete(inFolderRepeatingTaskEntity)
            getAllInFolderRepeatingTasks(folderId = 1).test {
                assertThat(expectMostRecentItem()).doesNotContain(inFolderRepeatingTaskEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun updateInFolderRepeatingTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val inFolderRepeatingTaskEntity = TaskTestUtils.createInFolderRepeatingTask()
        val updatedInFolderRepeatingTaskEntity = TaskTestUtils.createInFolderRepeatingTask(
            name = "New folder"
        )

        with(inFolderRepeatingTaskDao) {
            folderDao.insert(TaskTestUtils.createFolder())
            insert(inFolderRepeatingTaskEntity)
            update(updatedInFolderRepeatingTaskEntity)
            getAllInFolderRepeatingTasks(folderId = 1).test {
                assertThat(expectMostRecentItem()[0]).isEqualTo(updatedInFolderRepeatingTaskEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @After
    fun tearDown() {
        taskDatabase.close()
    }
}