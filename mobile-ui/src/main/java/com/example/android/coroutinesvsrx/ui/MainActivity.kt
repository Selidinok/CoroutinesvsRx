package com.example.android.coroutinesvsrx.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.android.coroutinesvsrx.R
import com.example.android.coroutinesvsrx.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout


        navigationController = Navigation.findNavController(this, R.id.navContainer)

        toolbar.title = "Repositories"
        setSupportActionBar(binding.toolbar)

        NavigationUI.setupActionBarWithNavController(this, navigationController, drawerLayout)

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.repositoriesFragment -> navigationController.navigate(R.id.repositoriesFragment)
                R.id.searchFragment -> navigationController.navigate(R.id.searchFragment)
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onNavigateUp(): Boolean {
        return NavigationUI.navigateUp(drawerLayout, navigationController)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
