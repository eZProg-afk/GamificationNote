package spiral.bit.dev.gamificationnote.repositories.shop.appDecorations

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appDecorations.EffectEntity

interface EffectRepository {

    suspend fun insert(effectEntity: EffectEntity)

    suspend fun delete(effectEntity: EffectEntity)

    suspend fun update(effectEntity: EffectEntity)

    fun observeAllEffects(isFromInventory: Boolean): Flow<List<EffectEntity>>
}