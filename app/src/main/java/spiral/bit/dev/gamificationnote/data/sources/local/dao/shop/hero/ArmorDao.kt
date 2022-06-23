package spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.hero

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.BaseDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.hero.ArmorEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder.FolderEntity

@Dao
interface ArmorDao : BaseDao<ArmorEntity> {

    @Query("SELECT * FROM armor WHERE isAlreadyBought = :isFromInventory")
    fun getArmor(isFromInventory: Boolean): Flow<List<ArmorEntity>>
}
