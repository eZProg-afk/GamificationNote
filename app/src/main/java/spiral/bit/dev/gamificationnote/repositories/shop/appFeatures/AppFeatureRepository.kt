package spiral.bit.dev.gamificationnote.repositories.shop.appFeatures

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appFeatures.AppFeatureEntity

interface AppFeatureRepository {

    suspend fun insert(appFeatureEntity: AppFeatureEntity)

    suspend fun delete(appFeatureEntity: AppFeatureEntity)

    suspend fun update(appFeatureEntity: AppFeatureEntity)

    fun observeAllAppFeatures(isFromInventory: Boolean): Flow<List<AppFeatureEntity>>
}