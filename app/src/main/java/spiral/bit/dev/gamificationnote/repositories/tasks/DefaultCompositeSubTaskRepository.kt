package spiral.bit.dev.gamificationnote.repositories.tasks

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks.CompositeSubTaskDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.CompositeSubTaskEntity

class DefaultCompositeSubTaskRepository(private val compositeSubTaskDao: CompositeSubTaskDao) :
    CompositeSubTaskRepository {

    override suspend fun insert(compositeSubTaskEntity: CompositeSubTaskEntity) {
        compositeSubTaskDao.insert(compositeSubTaskEntity)
    }

    override suspend fun update(compositeSubTaskEntity: CompositeSubTaskEntity) {
        compositeSubTaskDao.update(compositeSubTaskEntity)
    }

    override suspend fun delete(compositeSubTaskEntity: CompositeSubTaskEntity) {
        compositeSubTaskDao.delete(compositeSubTaskEntity)
    }

    override fun observeAllCompositeSubTasks(compositeTaskId: Int): Flow<List<CompositeSubTaskEntity>> {
        return compositeSubTaskDao.getAllCompositeSubTasks(compositeTaskId)
    }
}