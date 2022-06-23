package spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.BaseDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.SimpleTaskEntity

@Dao
interface SimpleTaskDao : BaseDao<SimpleTaskEntity> {

    @Query("SELECT * FROM simple_tasks")
    fun getAllSimpleTasks(): Flow<List<SimpleTaskEntity>>
}