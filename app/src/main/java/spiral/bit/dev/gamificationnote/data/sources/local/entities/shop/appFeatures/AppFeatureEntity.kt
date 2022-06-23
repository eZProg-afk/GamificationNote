package spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appFeatures

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "app_features", indices = [
        Index("name", unique = true),
        Index("description", unique = true)
    ]
)
data class AppFeatureEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val countOfPartsAlreadyBought: Int = 0,
    val countOfPartsNeeded: Int = 1,
    val isAlreadyBought: Boolean = countOfPartsAlreadyBought == countOfPartsNeeded,
    val name: String,
    val description: String,
    val priceInMoney: Int,
    val priceInCrystals: Int
)
