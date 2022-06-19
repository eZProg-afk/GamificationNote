package spiral.bit.dev.gamificationnote.ui.features.shop.observe.appDecoration

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import spiral.bit.dev.gamificationnote.ui.base.exceptions.IllegalViewPagerAdapterPositionException

class AppDecorationsCategoriesPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = TAB_ELEMENTS_SIZE

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ThemeShopFragment()
            1 -> MusicShopFragment()
            2 -> EffectsShopFragment()
            else -> throw IllegalViewPagerAdapterPositionException("This position not existed!")
        }
    }

    private companion object {
        private const val TAB_ELEMENTS_SIZE = 3
    }
}