package spiral.bit.dev.gamificationnote.data.sources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.appDecorations.EffectDao
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.appDecorations.MusicDao
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.appDecorations.ThemeDao
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.appFeatures.AppFeatureDao
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.hero.ArmorDao
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.hero.BackgroundDao
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.hero.WeaponDao
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appDecorations.EffectEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appDecorations.MusicEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appDecorations.ThemeEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appFeatures.AppFeatureEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.hero.ArmorEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.hero.BackgroundEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.hero.WeaponEntity

@Database(
    entities = [
        WeaponEntity::class, ArmorEntity::class, BackgroundEntity::class,
        ThemeEntity::class, MusicEntity::class, EffectEntity::class,
        AppFeatureEntity::class
    ], version = 1, exportSchema = false
)
abstract class ShopDatabase : RoomDatabase() {
    abstract fun weaponDao(): WeaponDao
    abstract fun armorDao(): ArmorDao
    abstract fun backgroundDao(): BackgroundDao
    abstract fun themeDao(): ThemeDao
    abstract fun musicDao(): MusicDao
    abstract fun effectDao(): EffectDao
    abstract fun appFeatureDao(): AppFeatureDao
}