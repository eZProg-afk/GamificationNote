package spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import spiral.bit.dev.gamificationnote.R
import spiral.bit.dev.gamificationnote.data.dto.task.TaskAutoCreationInterval
import spiral.bit.dev.gamificationnote.data.dto.task.TaskDifficult

@Entity(
    tableName = "in_folder_repeating_tasks", foreignKeys = [ForeignKey(
        entity = FolderEntity::class,
        parentColumns = ["id"],
        childColumns = ["parentFolderId"],
        onDelete = ForeignKey.CASCADE
    )
    ],
    indices = [
        Index("name", unique = true),
        Index("description", unique = true)
    ]
)
data class InFolderRepeatingTaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val parentFolderId: Int = 0,
    val name: String,
    val description: String? = null,
    val difficult: TaskDifficult = TaskDifficult.COMMON,
    @DrawableRes val iconResource: Int = R.drawable.ic_tasks,
    val autoCreationInterval: TaskAutoCreationInterval = TaskAutoCreationInterval.NOT_DEFINED,
    val nextAutoCreationDateInMillisTask: Long? = null
)
