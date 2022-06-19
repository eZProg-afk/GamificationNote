package spiral.bit.dev.gamificationnote.ui.features.tasks.observe.simple

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import spiral.bit.dev.gamificationnote.R
import spiral.bit.dev.gamificationnote.databinding.FragmentSimpleTasksBinding
import spiral.bit.dev.gamificationnote.ui.base.BaseFragment

class SimpleTasksFragment : BaseFragment<FragmentSimpleTasksBinding>(FragmentSimpleTasksBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClicks()
    }

    private fun setUpClicks() = requireBinding {

    }
}