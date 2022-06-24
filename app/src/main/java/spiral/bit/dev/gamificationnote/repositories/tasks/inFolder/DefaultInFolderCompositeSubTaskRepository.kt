package spiral.bit.dev.gamificationnote.repositories.tasks.inFolder

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks.inFolder.InFolderCompositeSubTaskDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder.InFolderCompositeSubTaskEntity

class DefaultInFolderCompositeSubTaskRepository(private val inFolderCompositeSubTaskDao: InFolderCompositeSubTaskDao) :
    InFolderCompositeSubTaskRepository {

    override suspend fun insert(inFolderCompositeSubTaskEntity: InFolderCompositeSubTaskEntity) {
        inFolderCompositeSubTaskDao.insert(inFolderCompositeSubTaskEntity)
    }

    override suspend fun update(inFolderCompositeSubTaskEntity: InFolderCompositeSubTaskEntity) {
        inFolderCompositeSubTaskDao.update(inFolderCompositeSubTaskEntity)
    }

    override suspend fun delete(inFolderCompositeSubTaskEntity: InFolderCompositeSubTaskEntity) {
        inFolderCompositeSubTaskDao.delete(inFolderCompositeSubTaskEntity)
    }

    override fun observeAllCompositeSubTasksInFolder(compositeTaskId: Int): Flow<List<InFolderCompositeSubTaskEntity>> {
        return inFolderCompositeSubTaskDao.getAllInFolderCompositeSubTasks(compositeTaskId)
    }
}