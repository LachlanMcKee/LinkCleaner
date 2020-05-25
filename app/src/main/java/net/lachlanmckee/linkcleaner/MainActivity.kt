package net.lachlanmckee.linkcleaner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = host.navController
        setupBottomNavMenu(navController)
        setupNavigationVisibilityToggle(navController)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        findViewById<BottomNavigationView>(R.id.bottom_navigation)!!
            .setupWithNavController(navController)
    }

    private fun setupNavigationVisibilityToggle(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
            when (destination.id) {
                R.id.home_dest,
                R.id.settings_dest -> bottomNavigation?.visibility = View.VISIBLE
                else -> bottomNavigation?.visibility = View.GONE
            }
        }
    }
}
