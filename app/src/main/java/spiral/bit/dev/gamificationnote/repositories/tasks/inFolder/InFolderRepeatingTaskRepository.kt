package spiral.bit.dev.gamificationnote.repositories.tasks.inFolder

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.SimpleTaskEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder.InFolderRepeatingTaskEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder.InFolderSimpleTaskEntity

interface InFolderRepeatingTaskRepository {

    suspend fun insert(inFolderRepeatingTaskEntity: InFolderRepeatingTaskEntity)

    suspend fun update(inFolderRepeatingTaskEntity: InFolderRepeatingTaskEntity)

    suspend fun delete(inFolderRepeatingTaskEntity: InFolderRepeatingTaskEntity)

    fun observeAllRepeatingTasksInFolder(folderId: Int): Flow<List<InFolderRepeatingTaskEntity>>
}