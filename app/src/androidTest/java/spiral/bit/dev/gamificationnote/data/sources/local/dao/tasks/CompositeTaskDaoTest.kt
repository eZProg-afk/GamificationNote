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
class CompositeTaskDaoTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testCoroutineDispatcher)
    private lateinit var taskDatabase: TaskDatabase
    private lateinit var compositeTaskDao: CompositeTaskDao

    @Before
    fun setUp() {
        taskDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TaskDatabase::class.java
        ).setQueryExecutor(testCoroutineDispatcher.asExecutor()).build()
        compositeTaskDao = taskDatabase.compositeTaskDao()
    }

    @Test
    fun insertCompositeTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val compositeTaskEntity = TaskTestUtils.createCompositeTask()

        with(compositeTaskDao) {
            insert(compositeTaskEntity)
            getAllCompositeTasks().test {
                assert(expectMostRecentItem().contains(compositeTaskEntity))
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun deleteCompositeTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val compositeTaskEntity = TaskTestUtils.createCompositeTask()

        with(compositeTaskDao) {
            insert(compositeTaskEntity)
            delete(compositeTaskEntity)
            getAllCompositeTasks().test {
                assertThat(expectMostRecentItem()).doesNotContain(compositeTaskEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun updateCompositeTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val compositeTaskEntity = TaskTestUtils.createCompositeTask()
        val updatedCompositeTaskEntity = TaskTestUtils.createCompositeTask(
            name = "New task title"
        )

        with(compositeTaskDao) {
            insert(compositeTaskEntity)
            update(updatedCompositeTaskEntity)
            getAllCompositeTasks().test {
                assertThat(expectMostRecentItem()[0]).isEqualTo(updatedCompositeTaskEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @After
    fun tearDown() {
        taskDatabase.close()
    }
}