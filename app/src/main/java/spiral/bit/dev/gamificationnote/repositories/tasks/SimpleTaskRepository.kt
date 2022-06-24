package spiral.bit.dev.gamificationnote.repositories.tasks

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.SimpleTaskEntity

interface SimpleTaskRepository {

    suspend fun insert(simpleTaskEntity: SimpleTaskEntity)

    suspend fun update(simpleTaskEntity: SimpleTaskEntity)

    suspend fun delete(simpleTaskEntity: SimpleTaskEntity)

    fun observeAllSimpleTasks(): Flow<List<SimpleTaskEntity>>
}