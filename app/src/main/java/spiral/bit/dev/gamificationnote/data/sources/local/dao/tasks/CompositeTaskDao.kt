package spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.BaseDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.CompositeTaskEntity

@Dao
interface CompositeTaskDao : BaseDao<CompositeTaskEntity> {

    @Query("SELECT * FROM composite_tasks")
    fun getAllCompositeTasks(): Flow<List<CompositeTaskEntity>>
}