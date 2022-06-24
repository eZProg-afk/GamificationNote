package spiral.bit.dev.gamificationnote.repositories.tasks

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks.RepeatingTaskDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.RepeatingTaskEntity

class DefaultRepeatingTaskRepository(private val repeatingTaskDao: RepeatingTaskDao) : RepeatingTaskRepository {

    override suspend fun insert(repeatingTaskEntity: RepeatingTaskEntity) {
        repeatingTaskDao.insert(repeatingTaskEntity)
    }

    override suspend fun update(repeatingTaskEntity: RepeatingTaskEntity) {
        repeatingTaskDao.update(repeatingTaskEntity)
    }

    override suspend fun delete(repeatingTaskEntity: RepeatingTaskEntity) {
        repeatingTaskDao.delete(repeatingTaskEntity)
    }

    override fun observeAllRepeatingTasks(): Flow<List<RepeatingTaskEntity>> {
        return repeatingTaskDao.getAllRepeatingTasks()
    }
}