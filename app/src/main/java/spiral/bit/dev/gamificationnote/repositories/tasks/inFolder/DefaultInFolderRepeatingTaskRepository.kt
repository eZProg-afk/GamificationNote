package spiral.bit.dev.gamificationnote.repositories.tasks.inFolder

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks.inFolder.InFolderRepeatingTaskDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder.InFolderRepeatingTaskEntity

class DefaultInFolderRepeatingTaskRepository(private val inFolderRepeatingTaskDao: InFolderRepeatingTaskDao) :
    InFolderRepeatingTaskRepository {

    override suspend fun insert(inFolderRepeatingTaskEntity: InFolderRepeatingTaskEntity) {
        inFolderRepeatingTaskDao.insert(inFolderRepeatingTaskEntity)
    }

    override suspend fun update(inFolderRepeatingTaskEntity: InFolderRepeatingTaskEntity) {
        inFolderRepeatingTaskDao.update(inFolderRepeatingTaskEntity)
    }

    override suspend fun delete(inFolderRepeatingTaskEntity: InFolderRepeatingTaskEntity) {
        inFolderRepeatingTaskDao.delete(inFolderRepeatingTaskEntity)
    }

    override fun observeAllRepeatingTasksInFolder(folderId: Int): Flow<List<InFolderRepeatingTaskEntity>> {
        return inFolderRepeatingTaskDao.getAllInFolderRepeatingTasks(folderId)
    }
}