package spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.hero

import androidx.annotation.DrawableRes
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import spiral.bit.dev.gamificationnote.data.dto.item.ItemRarity

@Entity(tableName = "backgrounds", indices = [
    Index("name", unique = true),
    Index("description", unique = true),
    Index("imageResource", unique = true)
])
data class BackgroundEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val isAlreadyBought: Boolean = false,
    val name: String,
    val description: String,
    val rarityValue: String = ItemRarity.COMMON.value,
    @DrawableRes val imageResource: Int,
    val priceInMoney: Int,
    val priceInCrystals: Int,
    val endurancePoints: Int = 0
)
