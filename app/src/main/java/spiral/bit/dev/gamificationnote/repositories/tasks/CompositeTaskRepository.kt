package spiral.bit.dev.gamificationnote.repositories.tasks

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.CompositeTaskEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.RepeatingTaskEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.SimpleTaskEntity

interface CompositeTaskRepository {

    suspend fun insert(compositeTaskEntity: CompositeTaskEntity)

    suspend fun update(compositeTaskEntity: CompositeTaskEntity)

    suspend fun delete(compositeTaskEntity: CompositeTaskEntity)

    fun observeAllCompositeTasks(): Flow<List<CompositeTaskEntity>>
}