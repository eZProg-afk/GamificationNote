package spiral.bit.dev.gamificationnote.repositories.tasks.inFolder

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder.InFolderCompositeTaskEntity

interface InFolderCompositeTaskRepository {

    suspend fun insert(inFolderCompositeTaskEntity: InFolderCompositeTaskEntity)

    suspend fun update(inFolderCompositeTaskEntity: InFolderCompositeTaskEntity)

    suspend fun delete(inFolderCompositeTaskEntity: InFolderCompositeTaskEntity)

    fun observeAllCompositeTasksInFolder(folderId: Int): Flow<List<InFolderCompositeTaskEntity>>
}