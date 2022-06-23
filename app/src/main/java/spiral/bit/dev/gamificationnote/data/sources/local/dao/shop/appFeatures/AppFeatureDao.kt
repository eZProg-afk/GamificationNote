package spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.appFeatures

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.BaseDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appFeatures.AppFeatureEntity

@Dao
interface AppFeatureDao : BaseDao<AppFeatureEntity> {

    @Query("SELECT * FROM app_features WHERE isAlreadyBought = :isFromInventory")
    fun getAppFeatures(isFromInventory: Boolean): Flow<List<AppFeatureEntity>>
}
