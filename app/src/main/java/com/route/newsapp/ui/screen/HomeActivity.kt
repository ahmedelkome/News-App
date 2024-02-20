package com.route.newsapp.ui.screen

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.route.newsapp.R
import com.route.newsapp.api.ApiManager
import com.route.newsapp.databinding.ActivityHomeBinding
import com.route.newsapp.ui.fragmnets.CategoriesFragment
import com.route.newsapp.ui.fragmnets.NewsFragment
import com.route.newsapp.ui.fragmnets.SettingsFragment


class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    val newsFragment = NewsFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        pushFragment(newsFragment)
        binding.title.text = "News App"
        selectNavigateItems()
    }

    private fun selectNavigateItems() {
        binding.navView.setNavigationItemSelectedListener { menuitem ->

            when (menuitem.itemId) {
                R.id.categories -> {
                    pushFragment(CategoriesFragment())
                    binding.drawer.closeDrawer(GravityCompat.START)
                    binding.title.text = "News App"
                    true
                }

                R.id.settings -> {
                    pushFragment(SettingsFragment())
                    binding.drawer.closeDrawer(GravityCompat.START)
                    binding.title.text = "Settings"
                    true
                }

                else -> false
            }
        }
    }

    private fun pushFragment(fragment:Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }
}