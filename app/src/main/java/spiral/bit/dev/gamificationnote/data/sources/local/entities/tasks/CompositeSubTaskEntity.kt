package spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "composite_subtasks",
    foreignKeys = [ForeignKey(
        entity = CompositeTaskEntity::class,
        parentColumns = ["id"],
        childColumns = ["parentCompositeTaskId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("name", unique = true)]
)
data class CompositeSubTaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val parentCompositeTaskId: Int = 0,
    val name: String
)