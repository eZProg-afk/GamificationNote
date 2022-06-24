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
class RepeatingTaskDaoTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testCoroutineDispatcher)
    private lateinit var taskDatabase: TaskDatabase
    private lateinit var repeatingTaskDao: RepeatingTaskDao

    @Before
    fun setUp() {
        taskDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TaskDatabase::class.java
        ).setQueryExecutor(testCoroutineDispatcher.asExecutor()).build()
        repeatingTaskDao = taskDatabase.repeatingTaskDao()
    }

    @Test
    fun insertRepeatingTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val repeatingTaskEntity = TaskTestUtils.createRepeatingTask()

        with(repeatingTaskDao) {
            insert(repeatingTaskEntity)
            getAllRepeatingTasks().test {
                assert(expectMostRecentItem().contains(repeatingTaskEntity))
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun deleteRepeatingTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val repeatingTaskEntity = TaskTestUtils.createRepeatingTask()

        with(repeatingTaskDao) {
            insert(repeatingTaskEntity)
            delete(repeatingTaskEntity)
            getAllRepeatingTasks().test {
                assertThat(expectMostRecentItem()).doesNotContain(repeatingTaskEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun updateRepeatingTaskAndObserveIt_returnTrue() = testScope.runBlockingTest {
        val repeatingTaskEntity = TaskTestUtils.createRepeatingTask()
        val updatedRepeatingTaskEntity = TaskTestUtils.createRepeatingTask(
            name = "New task title"
        )

        with(repeatingTaskDao) {
            insert(repeatingTaskEntity)
            update(updatedRepeatingTaskEntity)
            getAllRepeatingTasks().test {
                assertThat(expectMostRecentItem()[0]).isEqualTo(updatedRepeatingTaskEntity)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @After
    fun tearDown() {
        taskDatabase.close()
    }
}