package spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appDecorations

import androidx.annotation.RawRes
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "effects",
    indices = [
        Index("name", unique = true),
        Index("description", unique = true),
        Index("previewVideoResourceId", unique = true)
    ]
)
data class EffectEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val isAlreadyBought: Boolean = false,
    val name: String,
    val description: String,
    @RawRes val previewVideoResourceId: Int,
    val priceInMoney: Int,
    val priceInCrystals: Int
)
