package spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks.inFolder

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.BaseDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder.InFolderSimpleTaskEntity

@Dao
interface InFolderSimpleTaskDao : BaseDao<InFolderSimpleTaskEntity> {

    @Query("SELECT * FROM in_folder_simple_tasks WHERE parentFolderId = :folderId")
    fun getAllInFolderSimpleTasks(folderId: Int): Flow<List<InFolderSimpleTaskEntity>>
}