package spiral.bit.dev.gamificationnote.repositories.shop.hero

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.hero.WeaponEntity

interface WeaponRepository {

    suspend fun insert(weaponEntity: WeaponEntity)

    suspend fun delete(weaponEntity: WeaponEntity)

    suspend fun update(weaponEntity: WeaponEntity)

    fun observeAllWeapons(isFromInventory: Boolean): Flow<List<WeaponEntity>>
}