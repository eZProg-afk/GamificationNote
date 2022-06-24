package spiral.bit.dev.gamificationnote.data.sources.local.dao.shop

import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appDecorations.EffectEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appDecorations.MusicEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appDecorations.ThemeEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.appFeatures.AppFeatureEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.hero.ArmorEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.hero.BackgroundEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.shop.hero.WeaponEntity

object ShopTestUtils {
    fun createWeapon(name: String = "Weapon") = WeaponEntity(
        name = name,
        id = 1,
        isAlreadyBought = true,
        description = "Description weapon",
        rarityValue = "common",
        priceInMoney = 200,
        priceInCrystals = 0,
        attackPoints = 330
    )

    fun createArmor(name: String = "Armor") = ArmorEntity(
        name = name,
        id = 1,
        isAlreadyBought = true,
        description = "Description armor",
        rarityValue = "epic",
        priceInMoney = 350,
        priceInCrystals = 10,
        defencePoints = 100
    )

    fun createBackground(name: String = "Background") = BackgroundEntity(
        name = name,
        id = 1,
        isAlreadyBought = true,
        description = "Description background",
        rarityValue = "legendary",
        priceInMoney = 700,
        priceInCrystals = 100,
        endurancePoints = 300
    )

    fun createAppFeature(name: String = "Guild creation feature") = AppFeatureEntity(
        name = name,
        id = 1,
        countOfPartsAlreadyBought = 3,
        countOfPartsNeeded = 3,
        description = "Allow user to create his own guild",
        priceInMoney = 100,
        priceInCrystals = 300
    )

    fun createEffect(name: String = "Effect") = EffectEntity(
        name = name,
        id = 1,
        isAlreadyBought = true,
        description = "Description of effect",
        previewVideoResourceId = 0,
        priceInMoney = 350,
        priceInCrystals = 120
    )

    fun createMusic(name: String = "Music") = MusicEntity(
        name = name,
        id = 1,
        isAlreadyBought = true,
        description = "Description of music",
        previewAudioResourceId = 0,
        priceInMoney = 350,
        priceInCrystals = 120
    )

    fun createTheme(name: String = "Theme") = ThemeEntity(
        name = name,
        id = 1,
        isAlreadyBought = true,
        description = "Description of theme",
        themeResourceId = 0,
        previewVideoResourceId = 0,
        priceInMoney = 350,
        priceInCrystals = 120
    )
}