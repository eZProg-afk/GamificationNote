package spiral.bit.dev.gamificationnote.repositories.shop.appDecorations

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.appDecorations.ThemeDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appDecorations.ThemeEntity

class DefaultThemeRepository(private val themeDao: ThemeDao) : ThemeRepository {

    override suspend fun insert(themeEntity: ThemeEntity) {
        themeDao.insert(themeEntity)
    }

    override suspend fun delete(themeEntity: ThemeEntity) {
        themeDao.delete(themeEntity)
    }

    override suspend fun update(themeEntity: ThemeEntity) {
        themeDao.update(themeEntity)
    }

    override fun observeAllThemes(isFromInventory: Boolean): Flow<List<ThemeEntity>> {
        return themeDao.getThemes(isFromInventory)
    }
}