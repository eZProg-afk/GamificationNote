package spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.BaseDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder.FolderEntity

@Dao
interface FolderDao : BaseDao<FolderEntity> {

    @Query("SELECT * FROM folders")
    fun getAllFolders(): Flow<List<FolderEntity>>
}