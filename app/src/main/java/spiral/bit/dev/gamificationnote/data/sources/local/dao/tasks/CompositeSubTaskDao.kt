package spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.BaseDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.CompositeSubTaskEntity

@Dao
interface CompositeSubTaskDao : BaseDao<CompositeSubTaskEntity> {

    @Query("SELECT * FROM composite_subtasks WHERE id = :taskId")
    fun getAllCompositeSubTasks(taskId: Int): Flow<List<CompositeSubTaskEntity>>
}