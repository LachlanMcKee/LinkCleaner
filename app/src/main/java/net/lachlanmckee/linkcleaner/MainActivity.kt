package net.lachlanmckee.linkcleaner

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import net.lachlanmckee.linkcleaner.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
  private lateinit var binding: MainActivityBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = MainActivityBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val host: NavHostFragment = supportFragmentManager
      .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

    val navController = host.navController
    setupBottomNavMenu(navController)
    setupNavigationVisibilityToggle(navController)
  }

  private fun setupBottomNavMenu(navController: NavController) {
    binding.bottomNavigation.setupWithNavController(navController)
  }

  private fun setupNavigationVisibilityToggle(navController: NavController) {
    navController.addOnDestinationChangedListener { _, destination, _ ->
      binding.bottomNavigation.visibility = when (destination.id) {
        R.id.home_dest,
        R.id.settings_dest -> View.VISIBLE
        else -> View.GONE
      }
    }
  }
}
