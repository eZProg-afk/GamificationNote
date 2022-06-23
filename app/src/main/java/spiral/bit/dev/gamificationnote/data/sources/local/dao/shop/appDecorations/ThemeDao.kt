package spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.appDecorations

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.BaseDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appDecorations.ThemeEntity

@Dao
interface ThemeDao : BaseDao<ThemeEntity> {

    @Query("SELECT * FROM themes WHERE isAlreadyBought = :isFromInventory")
    fun getThemes(isFromInventory: Boolean): Flow<List<ThemeEntity>>
}
