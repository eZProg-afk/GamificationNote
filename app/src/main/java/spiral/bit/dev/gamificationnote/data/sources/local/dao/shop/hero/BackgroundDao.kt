package spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.hero

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.BaseDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.hero.BackgroundEntity

@Dao
interface BackgroundDao : BaseDao<BackgroundEntity> {

    @Query("SELECT * FROM backgrounds WHERE isAlreadyBought = :isFromInventory")
    fun getBackgrounds(isFromInventory: Boolean): Flow<List<BackgroundEntity>>
}
