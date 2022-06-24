package spiral.bit.dev.gamificationnote.repositories.tasks

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.RepeatingTaskEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.SimpleTaskEntity

interface RepeatingTaskRepository {

    suspend fun insert(repeatingTaskEntity: RepeatingTaskEntity)

    suspend fun update(repeatingTaskEntity: RepeatingTaskEntity)

    suspend fun delete(repeatingTaskEntity: RepeatingTaskEntity)

    fun observeAllRepeatingTasks(): Flow<List<RepeatingTaskEntity>>
}