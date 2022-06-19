package spiral.bit.dev.gamificationnote.ui.features.tasks

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import spiral.bit.dev.gamificationnote.R
import spiral.bit.dev.gamificationnote.databinding.FragmentTasksBinding
import spiral.bit.dev.gamificationnote.ui.base.BaseFragment
import spiral.bit.dev.gamificationnote.ui.base.exceptions.IllegalViewPagerAdapterPositionException

class TasksFragment : BaseFragment<FragmentTasksBinding>(FragmentTasksBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTabLayoutWithViewPager()
    }

    private fun setUpTabLayoutWithViewPager() = requireBinding {
        viewPager.adapter = TasksViewPagerAdapter(requireActivity())
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val tabTitleResource = when (position) {
                0 -> R.string.tab_simple_tasks
                1 -> R.string.tab_repeating_tasks
                2 -> R.string.tab_composite_tasks
                3 -> R.string.tab_completed_tasks
                4 -> R.string.tab_folders
                else -> throw IllegalViewPagerAdapterPositionException("Tab on this position not found")
            }
            tab.text = getString(tabTitleResource)
        }.attach()
    }
}