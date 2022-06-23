package spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "folders", )
data class FolderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    @DrawableRes val iconResource: Int
)
