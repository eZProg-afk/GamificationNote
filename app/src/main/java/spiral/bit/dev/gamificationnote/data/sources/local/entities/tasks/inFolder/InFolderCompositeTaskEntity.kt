package spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import spiral.bit.dev.gamificationnote.R
import spiral.bit.dev.gamificationnote.data.dto.task.TaskDifficult

@Entity(
    tableName = "in_folder_composite_tasks", foreignKeys = [ForeignKey(
        entity = FolderEntity::class,
        parentColumns = ["id"],
        childColumns = ["parentFolderId"],
        onDelete = ForeignKey.CASCADE
    )
    ],
    indices = [
        Index("name", unique = true),
        Index("description", unique = true),
        Index("parentFolderId", unique = true)
    ]
)
data class InFolderCompositeTaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val parentFolderId: Int = 0,
    val name: String,
    val description: String? = null,
    val difficult: TaskDifficult = TaskDifficult.COMMON,
    val currentProgress: Int = 0,
    @DrawableRes val iconResource: Int = R.drawable.ic_tasks
)
