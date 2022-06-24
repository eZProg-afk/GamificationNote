package spiral.bit.dev.gamificationnote.repositories.shop.hero

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.hero.ArmorDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.hero.ArmorEntity

class DefaultArmorRepository(private val armorDao: ArmorDao) : ArmorRepository {

    override suspend fun insert(armorEntity: ArmorEntity) {
        armorDao.insert(armorEntity)
    }

    override suspend fun delete(armorEntity: ArmorEntity) {
        armorDao.delete(armorEntity)
    }

    override suspend fun update(armorEntity: ArmorEntity) {
        armorDao.update(armorEntity)
    }

    override fun observeAllArmor(isFromInventory: Boolean): Flow<List<ArmorEntity>> {
        return armorDao.getArmor(isFromInventory)
    }
}