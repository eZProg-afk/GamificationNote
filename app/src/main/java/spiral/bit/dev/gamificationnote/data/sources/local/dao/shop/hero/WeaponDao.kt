package spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.hero

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.BaseDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.hero.WeaponEntity

@Dao
interface WeaponDao : BaseDao<WeaponEntity> {

    @Query("SELECT * FROM weapons WHERE isAlreadyBought = :isFromInventory")
    fun getWeapons(isFromInventory: Boolean): Flow<List<WeaponEntity>>
}
