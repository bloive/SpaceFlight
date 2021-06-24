package com.example.spaceflight

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spaceflight.adapters.DrawerAdapter
import com.example.spaceflight.databinding.ActivityMainBinding
import com.example.spaceflight.ui.MenuItemModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val menuItems = mutableListOf<MenuItemModel>()
    private lateinit var adapter:DrawerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDrawer()
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun initDrawer() {
        menuItems.add(MenuItemModel(R.drawable.ic_baseline_insert_emoticon_24, "Emoticon"))
        menuItems.add(MenuItemModel(R.drawable.ic_baseline_pest_control_rodent_24, "Pest"))
        binding.menuRecycler.layoutManager = LinearLayoutManager(this)
        adapter = DrawerAdapter(menuItems)
        adapter.menuItemOnClick = {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        binding.menuRecycler.adapter = adapter
    }
}
