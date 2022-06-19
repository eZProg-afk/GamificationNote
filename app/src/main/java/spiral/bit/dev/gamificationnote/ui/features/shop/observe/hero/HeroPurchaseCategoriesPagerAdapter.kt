package spiral.bit.dev.gamificationnote.ui.features.shop

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import spiral.bit.dev.gamificationnote.ui.base.exceptions.IllegalViewPagerAdapterPositionException
import spiral.bit.dev.gamificationnote.ui.features.shop.observe.hero.ArmorShopFragment
import spiral.bit.dev.gamificationnote.ui.features.shop.observe.hero.BackgroundShopFragment
import spiral.bit.dev.gamificationnote.ui.features.shop.observe.hero.WeaponShopFragment

class HeroPurchaseCategoriesPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = TAB_ELEMENTS_SIZE

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> WeaponShopFragment()
            1 -> ArmorShopFragment()
            2 -> BackgroundShopFragment()
            else -> throw IllegalViewPagerAdapterPositionException("This position not existed!")
        }
    }

    private companion object {
        private const val TAB_ELEMENTS_SIZE = 3
    }
}