package spiral.bit.dev.gamificationnote.di

import org.kodein.di.DI
import org.kodein.di.bindMultiton
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.appDecorations.EffectDao
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.appDecorations.MusicDao
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.appDecorations.ThemeDao
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.appFeatures.AppFeatureDao
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.hero.ArmorDao
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.hero.BackgroundDao
import spiral.bit.dev.gamificationnote.data.sources.local.dao.shop.hero.WeaponDao
import spiral.bit.dev.gamificationnote.repositories.shop.appDecorations.DefaultEffectRepository
import spiral.bit.dev.gamificationnote.repositories.shop.appDecorations.DefaultMusicRepository
import spiral.bit.dev.gamificationnote.repositories.shop.appDecorations.DefaultThemeRepository
import spiral.bit.dev.gamificationnote.repositories.shop.appFeatures.DefaultAppFeatureRepository
import spiral.bit.dev.gamificationnote.repositories.shop.hero.DefaultArmorRepository
import spiral.bit.dev.gamificationnote.repositories.shop.hero.DefaultBackgroundRepository
import spiral.bit.dev.gamificationnote.repositories.shop.hero.DefaultWeaponRepository

val repositoryModule = DI.Module(name = "repository") {
    bindRepositories()
}

fun DI.Builder.bindRepositories() {
    bindShopRepositories()
    bindTaskRepositories()
}

fun DI.Builder.bindShopRepositories() {
    bindMultiton("theme_repository") { themeDao: ThemeDao ->
        DefaultThemeRepository(themeDao)
    }

    bindMultiton("music_repository") { musicDao: MusicDao ->
        DefaultMusicRepository(musicDao)
    }

    bindMultiton("effect_repository") { effectDao: EffectDao ->
        DefaultEffectRepository(effectDao)
    }

    bindMultiton("app_feature_repository") { appFeatureDao: AppFeatureDao ->
        DefaultAppFeatureRepository(appFeatureDao)
    }

    bindMultiton("armor_repository") { armorDao: ArmorDao ->
        DefaultArmorRepository(armorDao)
    }

    bindMultiton("weapon_repository") { weaponDao: WeaponDao ->
        DefaultWeaponRepository(weaponDao)
    }

    bindMultiton("background_repository") { backgroundDao: BackgroundDao ->
        DefaultBackgroundRepository(backgroundDao)
    }
}

fun DI.Builder.bindTaskRepositories() {

}