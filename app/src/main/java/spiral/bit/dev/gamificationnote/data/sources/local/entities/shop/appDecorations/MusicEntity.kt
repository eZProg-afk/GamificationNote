package spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appDecorations

import androidx.annotation.RawRes
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "music", indices = [
        Index("name", unique = true),
        Index("description", unique = true),
        Index("previewAudioResourceId", unique = true)
    ]
)
data class MusicEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val isAlreadyBought: Boolean = false,
    val name: String,
    val description: String,
    @RawRes val previewAudioResourceId: Int,
    val priceInMoney: Int,
    val priceInCrystals: Int
)
