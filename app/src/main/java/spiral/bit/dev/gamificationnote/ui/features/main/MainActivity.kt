package spiral.bit.dev.gamificationnote.ui.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import spiral.bit.dev.gamificationnote.R
import spiral.bit.dev.gamificationnote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityMainBinding>()
    private var navController: NavController? = null
    private val navHostFragment by lazy { supportFragmentManager.findFragmentById(R.id.mainFragmentContainerView) as NavHostFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupSmoothBottomMenu()
    }

    private fun setupSmoothBottomMenu() = with(binding) {
        navController = navHostFragment.navController
        val popupMenu = PopupMenu(this@MainActivity, null)
        popupMenu.inflate(R.menu.bottom_menu)
        val menu = popupMenu.menu
        binding.bottomNavigationView.setupWithNavController(menu, checkNotNull(navController))
    }

    override fun onSupportNavigateUp(): Boolean {
        return checkNotNull(navController).navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        /* no-op */
    }
}