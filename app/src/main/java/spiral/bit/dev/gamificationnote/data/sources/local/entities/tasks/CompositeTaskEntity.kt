package spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import spiral.bit.dev.gamificationnote.R
import spiral.bit.dev.gamificationnote.data.dto.task.TaskDifficult

@Entity(
    tableName = "composite_tasks",
    indices = [
        Index("name", unique = true),
        Index("description", unique = true)
    ]
)
data class CompositeTaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String? = null,
    val difficult: TaskDifficult = TaskDifficult.COMMON,
    val currentProgress: Int = 0,
    @DrawableRes val iconResource: Int = R.drawable.ic_tasks
)