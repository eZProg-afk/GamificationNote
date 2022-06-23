package spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.appDecorations

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.BaseDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appDecorations.MusicEntity

@Dao
interface MusicDao : BaseDao<MusicEntity> {

    @Query("SELECT * FROM music WHERE isAlreadyBought = :isFromInventory")
    fun getMusic(isFromInventory: Boolean): Flow<List<MusicEntity>>
}
