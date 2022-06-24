package spiral.bit.dev.gamificationnote.repositories.tasks

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks.SimpleTaskDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.SimpleTaskEntity

class DefaultSimpleTaskRepository(private val simpleTaskDao: SimpleTaskDao) : SimpleTaskRepository {

    override suspend fun insert(simpleTaskEntity: SimpleTaskEntity) {
        simpleTaskDao.insert(simpleTaskEntity)
    }

    override suspend fun update(simpleTaskEntity: SimpleTaskEntity) {
        simpleTaskDao.update(simpleTaskEntity)
    }

    override suspend fun delete(simpleTaskEntity: SimpleTaskEntity) {
        simpleTaskDao.delete(simpleTaskEntity)
    }

    override fun observeAllSimpleTasks(): Flow<List<SimpleTaskEntity>> {
        return simpleTaskDao.getAllSimpleTasks()
    }
}