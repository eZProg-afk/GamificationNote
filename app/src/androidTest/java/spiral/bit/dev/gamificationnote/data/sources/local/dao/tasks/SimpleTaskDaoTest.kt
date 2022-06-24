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
class SimpleTaskDaoTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testCoroutineDispatcher)
    private lateinit var taskDatabase: TaskDatabase
    private lateinit var simpleTaskDao: SimpleTaskDao

    @Before
    fun setUp() {
        taskDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TaskDatabase::class.java
        ).setQueryExecutor(testCoroutineDispatcher.asExecutor()).build()
        simpleTaskDao = taskDatabase.simpleTaskDao()
    }

    @Test
    fun insertSimpleTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val simpleTaskEntity = TaskTestUtils.createSimpleTask()

        with(simpleTaskDao) {
            insert(simpleTaskEntity)
            getAllSimpleTasks().test {
                assert(expectMostRecentItem().contains(simpleTaskEntity))
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun deleteSimpleTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val simpleTaskEntity = TaskTestUtils.createSimpleTask()

        with(simpleTaskDao) {
            insert(simpleTaskEntity)
            delete(simpleTaskEntity)
            getAllSimpleTasks().test {
                assertThat(expectMostRecentItem()).doesNotContain(simpleTaskEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun updateSimpleTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val simpleTaskEntity = TaskTestUtils.createSimpleTask()
        val updatedSimpleTaskEntity = TaskTestUtils.createSimpleTask(
            name = "New task title"
        )

        with(simpleTaskDao) {
            insert(simpleTaskEntity)
            update(updatedSimpleTaskEntity)
            getAllSimpleTasks().test {
                assertThat(expectMostRecentItem()[0]).isEqualTo(updatedSimpleTaskEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @After
    fun tearDown() {
        taskDatabase.close()
    }
}