package spiral.bit.dev.gamificationnote.repositories.tasks.inFolder

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks.SimpleTaskDao
import spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks.inFolder.InFolderSimpleTaskDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.SimpleTaskEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder.InFolderSimpleTaskEntity

class DefaultInFolderSimpleTaskRepository(private val inFolderSimpleTaskDao: InFolderSimpleTaskDao) : InFolderSimpleTaskRepository {

    override suspend fun insert(inFolderSimpleTaskEntity: InFolderSimpleTaskEntity) {
        inFolderSimpleTaskDao.insert(inFolderSimpleTaskEntity)
    }

    override suspend fun update(inFolderSimpleTaskEntity: InFolderSimpleTaskEntity) {
        inFolderSimpleTaskDao.update(inFolderSimpleTaskEntity)
    }

    override suspend fun delete(inFolderSimpleTaskEntity: InFolderSimpleTaskEntity) {
        inFolderSimpleTaskDao.delete(inFolderSimpleTaskEntity)
    }

    override fun observeAllSimpleTasksInFolder(folderId: Int): Flow<List<InFolderSimpleTaskEntity>> {
        return inFolderSimpleTaskDao.getAllInFolderSimpleTasks(folderId)
    }
}