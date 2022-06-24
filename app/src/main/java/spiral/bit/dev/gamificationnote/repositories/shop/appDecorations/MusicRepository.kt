package spiral.bit.dev.gamificationnote.repositories.shop.appDecorations

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appDecorations.MusicEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appDecorations.ThemeEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.hero.ArmorEntity

interface MusicRepository {

    suspend fun insert(musicEntity: MusicEntity)

    suspend fun delete(musicEntity: MusicEntity)

    suspend fun update(musicEntity: MusicEntity)

    fun observeAllMusic(isFromInventory: Boolean): Flow<List<MusicEntity>>
}