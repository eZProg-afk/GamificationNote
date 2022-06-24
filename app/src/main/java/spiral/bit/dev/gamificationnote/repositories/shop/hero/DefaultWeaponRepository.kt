package spiral.bit.dev.gamificationnote.repositories.shop.hero

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.hero.WeaponDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.hero.WeaponEntity

class DefaultWeaponRepository(private val weaponDao: WeaponDao) : WeaponRepository {

    override suspend fun insert(weaponEntity: WeaponEntity) {
        weaponDao.insert(weaponEntity)
    }

    override suspend fun delete(weaponEntity: WeaponEntity) {
        weaponDao.delete(weaponEntity)
    }

    override suspend fun update(weaponEntity: WeaponEntity) {
        weaponDao.update(weaponEntity)
    }

    override fun observeAllWeapons(isFromInventory: Boolean): Flow<List<WeaponEntity>> {
        return weaponDao.getWeapons(isFromInventory = isFromInventory)
    }
}