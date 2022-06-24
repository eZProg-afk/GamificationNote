package spiral.bit.dev.gamificationnote.repositories.shop.appDecorations

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appDecorations.ThemeEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.hero.ArmorEntity

interface ThemeRepository {

    suspend fun insert(themeEntity: ThemeEntity)

    suspend fun delete(themeEntity: ThemeEntity)

    suspend fun update(themeEntity: ThemeEntity)

    fun observeAllThemes(isFromInventory: Boolean): Flow<List<ThemeEntity>>
}