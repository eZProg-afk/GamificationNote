package spiral.bit.dev.gamificationnote.repositories.tasks

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.CompositeSubTaskEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.CompositeTaskEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.RepeatingTaskEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.SimpleTaskEntity

interface CompositeSubTaskRepository {

    suspend fun insert(compositeSubTaskEntity: CompositeSubTaskEntity)

    suspend fun update(compositeSubTaskEntity: CompositeSubTaskEntity)

    suspend fun delete(compositeSubTaskEntity: CompositeSubTaskEntity)

    fun observeAllCompositeSubTasks(compositeTaskId: Int): Flow<List<CompositeSubTaskEntity>>
}