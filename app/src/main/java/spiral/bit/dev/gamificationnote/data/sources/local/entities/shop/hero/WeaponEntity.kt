package spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.hero

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import spiral.bit.dev.gamificationnote.R
import spiral.bit.dev.gamificationnote.data.dto.item.ItemRarity

@Entity(tableName = "weapons", indices = [
    Index("name", unique = true),
    Index("description", unique = true),
    Index("imageResource", unique = true)
])
data class WeaponEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val isAlreadyBought: Boolean = false,
    val name: String,
    val description: String,
    val rarityValue: String = ItemRarity.COMMON.value,
    @DrawableRes val imageResource: Int = R.drawable.empty_placeholder,
    val priceInMoney: Int,
    val priceInCrystals: Int,
    val attackPoints: Int = 0
)
