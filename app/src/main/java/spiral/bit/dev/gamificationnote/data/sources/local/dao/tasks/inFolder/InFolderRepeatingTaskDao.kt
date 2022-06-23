package spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks.inFolder

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.BaseDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder.InFolderRepeatingTaskEntity

@Dao
interface InFolderRepeatingTaskDao : BaseDao<InFolderRepeatingTaskEntity> {

    @Query("SELECT * FROM in_folder_repeating_tasks WHERE parentFolderId = :folderId")
    fun getAllInFolderRepeatingTasks(folderId: Int): Flow<List<InFolderRepeatingTaskEntity>>
}