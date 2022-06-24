package spiral.bit.dev.gamificationnote.repositories.tasks

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks.CompositeTaskDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.CompositeTaskEntity

class DefaultCompositeTaskRepository(private val compositeTaskDao: CompositeTaskDao) : CompositeTaskRepository {

    override suspend fun insert(compositeTaskEntity: CompositeTaskEntity) {
        compositeTaskDao.insert(compositeTaskEntity)
    }

    override suspend fun update(compositeTaskEntity: CompositeTaskEntity) {
        compositeTaskDao.update(compositeTaskEntity)
    }

    override suspend fun delete(compositeTaskEntity: CompositeTaskEntity) {
        compositeTaskDao.delete(compositeTaskEntity)
    }

    override fun observeAllCompositeTasks(): Flow<List<CompositeTaskEntity>> {
        return compositeTaskDao.getAllCompositeTasks()
    }
}