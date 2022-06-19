package spiral.bit.dev.gamificationnote.ui.features.tasks

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import spiral.bit.dev.gamificationnote.ui.base.exceptions.IllegalViewPagerAdapterPositionException
import spiral.bit.dev.gamificationnote.ui.features.tasks.observe.completed.CompletedTasksFragment
import spiral.bit.dev.gamificationnote.ui.features.tasks.observe.composite.CompositeTasksFragment
import spiral.bit.dev.gamificationnote.ui.features.tasks.observe.folders.FoldersFragment
import spiral.bit.dev.gamificationnote.ui.features.tasks.observe.repeating.RepeatingTasksFragment
import spiral.bit.dev.gamificationnote.ui.features.tasks.observe.simple.SimpleTasksFragment

class TasksViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = TAB_ELEMENTS_SIZE

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SimpleTasksFragment()
            1 -> RepeatingTasksFragment()
            2 -> CompositeTasksFragment()
            3 -> FoldersFragment()
            4 -> CompletedTasksFragment()
            else -> throw IllegalViewPagerAdapterPositionException("This position not existed!")
        }
    }

    private companion object {
        private const val TAB_ELEMENTS_SIZE = 5
    }
}