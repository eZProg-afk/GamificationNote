package spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks.inFolder

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.BaseDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder.InFolderCompositeSubTaskEntity

@Dao
interface InFolderCompositeSubTaskDao : BaseDao<InFolderCompositeSubTaskEntity> {

    @Query("SELECT * FROM in_folder_sub_composite_tasks WHERE parentCompositeTaskId = :taskId")
    fun getAllInFolderCompositeSubTasks(taskId: Int): Flow<List<InFolderCompositeSubTaskEntity>>
}