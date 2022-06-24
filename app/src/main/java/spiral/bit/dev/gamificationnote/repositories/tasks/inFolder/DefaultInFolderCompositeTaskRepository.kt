package spiral.bit.dev.gamificationnote.repositories.tasks.inFolder

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks.inFolder.InFolderCompositeTaskDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder.InFolderCompositeTaskEntity

class DefaultInFolderCompositeTaskRepository(private val inFolderCompositeTaskDao: InFolderCompositeTaskDao) :
    InFolderCompositeTaskRepository {

    override suspend fun insert(inFolderCompositeTaskEntity: InFolderCompositeTaskEntity) {
        inFolderCompositeTaskDao.insert(inFolderCompositeTaskEntity)
    }

    override suspend fun update(inFolderCompositeTaskEntity: InFolderCompositeTaskEntity) {
        inFolderCompositeTaskDao.update(inFolderCompositeTaskEntity)
    }

    override suspend fun delete(inFolderCompositeTaskEntity: InFolderCompositeTaskEntity) {
        inFolderCompositeTaskDao.delete(inFolderCompositeTaskEntity)
    }

    override fun observeAllCompositeTasksInFolder(folderId: Int): Flow<List<InFolderCompositeTaskEntity>> {
        return inFolderCompositeTaskDao.getAllInFolderCompositeTasks(folderId)
    }
}