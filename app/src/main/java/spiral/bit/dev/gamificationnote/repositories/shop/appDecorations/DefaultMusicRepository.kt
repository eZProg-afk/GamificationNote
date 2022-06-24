package spiral.bit.dev.gamificationnote.repositories.shop.appDecorations

import kotlinx.coroutines.flow.Flow
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.appDecorations.MusicDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appDecorations.MusicEntity

class DefaultMusicRepository(private val musicDao: MusicDao) : MusicRepository {

    override suspend fun insert(musicEntity: MusicEntity) {
        musicDao.insert(musicEntity)
    }

    override suspend fun delete(musicEntity: MusicEntity) {
        musicDao.delete(musicEntity)
    }

    override suspend fun update(musicEntity: MusicEntity) {
        musicDao.update(musicEntity)
    }

    override fun observeAllMusic(isFromInventory: Boolean): Flow<List<MusicEntity>> {
        return musicDao.getMusic(isFromInventory)
    }
}