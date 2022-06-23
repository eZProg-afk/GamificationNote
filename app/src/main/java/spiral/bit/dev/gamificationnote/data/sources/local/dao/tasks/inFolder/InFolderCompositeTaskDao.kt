package spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks.inFolder

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.BaseDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder.InFolderCompositeTaskEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder.InFolderRepeatingTaskEntity

@Dao
interface InFolderCompositeTaskDao : BaseDao<InFolderCompositeTaskEntity> {

    @Query("SELECT * FROM in_folder_composite_tasks WHERE parentFolderId = :folderId")
    fun getAllInFolderCompositeTasks(folderId: Int): Flow<List<InFolderCompositeTaskEntity>>
}