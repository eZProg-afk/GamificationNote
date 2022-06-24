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
class InFolderCompositeSubTaskDaoTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testCoroutineDispatcher)
    private lateinit var taskDatabase: TaskDatabase
    private lateinit var folderDao: FolderDao
    private lateinit var inFolderCompositeTaskDao: InFolderCompositeTaskDao
    private lateinit var inFolderCompositeSubTaskDao: InFolderCompositeSubTaskDao

    @Before
    fun setUp() {
        taskDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TaskDatabase::class.java
        ).setQueryExecutor(testCoroutineDispatcher.asExecutor()).build()
        folderDao = taskDatabase.folderDao()
        inFolderCompositeTaskDao = taskDatabase.inFolderCompositeTaskDao()
        inFolderCompositeSubTaskDao = taskDatabase.inFolderCompositeSubTaskDao()
    }

    @Test
    fun insertInFolderCompositeSubTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val inFolderCompositeSubTaskEntity = TaskTestUtils.createInFolderCompositeSubTask()

        with(inFolderCompositeSubTaskDao) {
            folderDao.insert(TaskTestUtils.createFolder())
            inFolderCompositeTaskDao.insert(TaskTestUtils.createInFolderCompositeTask())
            insert(inFolderCompositeSubTaskEntity)
            getAllInFolderCompositeSubTasks(taskId = 1).test {
                assert(expectMostRecentItem().contains(inFolderCompositeSubTaskEntity))
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun deleteInFolderCompositeSubTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val inFolderCompositeSubTaskEntity = TaskTestUtils.createInFolderCompositeSubTask()

        with(inFolderCompositeSubTaskDao) {
            folderDao.insert(TaskTestUtils.createFolder())
            inFolderCompositeTaskDao.insert(TaskTestUtils.createInFolderCompositeTask())
            insert(inFolderCompositeSubTaskEntity)
            delete(inFolderCompositeSubTaskEntity)
            getAllInFolderCompositeSubTasks(taskId = 1).test {
                assertThat(expectMostRecentItem()).doesNotContain(inFolderCompositeSubTaskEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun updateInFolderCompositeSubTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val inFolderCompositeSubTaskEntity = TaskTestUtils.createInFolderCompositeSubTask()
        val updatedInFolderCompositeSubTaskEntity = TaskTestUtils.createInFolderCompositeSubTask(
            name = "New task title"
        )

        with(inFolderCompositeSubTaskDao) {
            folderDao.insert(TaskTestUtils.createFolder())
            inFolderCompositeTaskDao.insert(TaskTestUtils.createInFolderCompositeTask())
            insert(inFolderCompositeSubTaskEntity)
            update(updatedInFolderCompositeSubTaskEntity)
            getAllInFolderCompositeSubTasks(taskId = 1).test {
                assertThat(expectMostRecentItem()[0]).isEqualTo(
                    updatedInFolderCompositeSubTaskEntity
                )
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @After
    fun tearDown() {
        taskDatabase.close()
    }
}