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
class CompositeSubTaskDaoTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testCoroutineDispatcher)
    private lateinit var taskDatabase: TaskDatabase
    private lateinit var compositeTaskDao: CompositeTaskDao
    private lateinit var compositeSubTaskDao: CompositeSubTaskDao

    @Before
    fun setUp() {
        taskDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TaskDatabase::class.java
        ).setQueryExecutor(testCoroutineDispatcher.asExecutor()).build()
        compositeTaskDao = taskDatabase.compositeTaskDao()
        compositeSubTaskDao = taskDatabase.compositeSubTaskDao()
    }

    @Test
    fun insertCompositeSubTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val compositeSubTaskEntity = TaskTestUtils.createCompositeSubTask()

        with(compositeSubTaskDao) {
            compositeTaskDao.insert(TaskTestUtils.createCompositeTask())
            insert(compositeSubTaskEntity)
            getAllCompositeSubTasks(taskId = 1).test {
                assert(expectMostRecentItem().contains(compositeSubTaskEntity))
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun deleteCompositeSubTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val compositeSubTaskEntity = TaskTestUtils.createCompositeSubTask()

        with(compositeSubTaskDao) {
            compositeTaskDao.insert(TaskTestUtils.createCompositeTask())
            insert(compositeSubTaskEntity)
            delete(compositeSubTaskEntity)
            getAllCompositeSubTasks(taskId = 1).test {
                assertThat(expectMostRecentItem()).doesNotContain(compositeSubTaskEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun updateCompositeSubTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val compositeSubTaskEntity = TaskTestUtils.createCompositeSubTask()
        val updatedCompositeSubTaskEntity = TaskTestUtils.createCompositeSubTask(
            name = "New task title"
        )

        with(compositeSubTaskDao) {
            compositeTaskDao.insert(TaskTestUtils.createCompositeTask())
            insert(compositeSubTaskEntity)
            update(updatedCompositeSubTaskEntity)
            getAllCompositeSubTasks(taskId = 1).test {
                assertThat(expectMostRecentItem()[0]).isEqualTo(updatedCompositeSubTaskEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @After
    fun tearDown() {
        taskDatabase.close()
    }
}