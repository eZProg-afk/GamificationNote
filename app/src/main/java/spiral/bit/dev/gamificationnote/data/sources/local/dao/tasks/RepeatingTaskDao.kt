package spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.BaseDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.RepeatingTaskEntity

@Dao
interface RepeatingTaskDao : BaseDao<RepeatingTaskEntity> {

    @Query("SELECT * FROM repeating_tasks")
    fun getAllRepeatingTasks(): Flow<List<RepeatingTaskEntity>>
}