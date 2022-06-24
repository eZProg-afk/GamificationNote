package spiral.bit.dev.gamificationnote.repositories.tasks.inFolder

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder.InFolderCompositeSubTaskEntity

interface InFolderCompositeSubTaskRepository {

    suspend fun insert(inFolderCompositeSubTaskEntity: InFolderCompositeSubTaskEntity)

    suspend fun update(inFolderCompositeSubTaskEntity: InFolderCompositeSubTaskEntity)

    suspend fun delete(inFolderCompositeSubTaskEntity: InFolderCompositeSubTaskEntity)

    fun observeAllCompositeSubTasksInFolder(compositeTaskId: Int): Flow<List<InFolderCompositeSubTaskEntity>>
}