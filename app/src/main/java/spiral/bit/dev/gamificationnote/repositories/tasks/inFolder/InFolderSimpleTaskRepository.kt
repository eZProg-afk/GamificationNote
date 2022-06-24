package spiral.bit.dev.gamificationnote.repositories.tasks.inFolder

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.SimpleTaskEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder.InFolderSimpleTaskEntity

interface InFolderSimpleTaskRepository {

    suspend fun insert(inFolderSimpleTaskEntity: InFolderSimpleTaskEntity)

    suspend fun update(inFolderSimpleTaskEntity: InFolderSimpleTaskEntity)

    suspend fun delete(inFolderSimpleTaskEntity: InFolderSimpleTaskEntity)

    fun observeAllSimpleTasksInFolder(folderId: Int): Flow<List<InFolderSimpleTaskEntity>>
}