package spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "in_folder_sub_composite_tasks", foreignKeys = [ForeignKey(
        entity = InFolderCompositeTaskEntity::class,
        parentColumns = ["id"],
        childColumns = ["parentCompositeTaskId"],
        onDelete = ForeignKey.CASCADE,
    )
    ],
    indices = [Index("name", unique = true)]
)
data class InFolderCompositeSubTaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val parentCompositeTaskId: Int = 0,
    val name: String
)
