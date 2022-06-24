package spiral.bit.dev.gamificationnote.repositories.shop.appDecorations

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.appDecorations.EffectDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appDecorations.EffectEntity

class DefaultEffectRepository(private val effectDao: EffectDao) : EffectRepository {

    override suspend fun insert(effectEntity: EffectEntity) {
        effectDao.insert(effectEntity)
    }

    override suspend fun delete(effectEntity: EffectEntity) {
        effectDao.delete(effectEntity)
    }

    override suspend fun update(effectEntity: EffectEntity) {
        effectDao.update(effectEntity)
    }

    override fun observeAllEffects(isFromInventory: Boolean): Flow<List<EffectEntity>> {
        return effectDao.getEffects(isFromInventory)
    }
}