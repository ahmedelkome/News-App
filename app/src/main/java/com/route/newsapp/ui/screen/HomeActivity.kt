package com.route.newsapp.ui.screen

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.route.newsapp.R
import com.route.newsapp.databinding.ActivityHomeBinding
import com.route.newsapp.ui.fragmnets.categories.CategoriesFragment
import com.route.newsapp.ui.fragmnets.news.NewsFragment
import com.route.newsapp.ui.fragmnets.search.SearchFragment
import com.route.newsapp.ui.fragmnets.settings.SettingsFragment


class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var categoryFragment: CategoriesFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initClickedInCategory()
        pushFragment(categoryFragment)
        showSearchIcon(false)
        selectNavigateItems()
        showIconDrawer()
        startSearchOption()
    }

    private fun startSearchOption() {
        binding.search.setOnClickListener {
            pushFragment(SearchFragment())
        }
    }

    private fun showSearchIcon(isVisible: Boolean) {
        binding.search.isVisible = isVisible
    }

    private fun initClickedInCategory() {
        categoryFragment = CategoriesFragment {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NewsFragment(it.id))
                .addToBackStack("")
                .commit()
            showSearchIcon(true)
        }
    }

    private fun showIconDrawer() {
        setSupportActionBar(binding.toolbar)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, binding.drawer, R.string.nav_open, R.string.nav_close);
        binding.drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }


    private fun selectNavigateItems() {
        binding.navView.setNavigationItemSelectedListener { menuitem ->

            when (menuitem.itemId) {
                R.id.categories -> {
                    pushFragment(categoryFragment!!)
                    showSearchIcon(false)
                    binding.drawer.closeDrawer(GravityCompat.START)

                    true
                }

                R.id.settings -> {
                    pushFragment(SettingsFragment())
                    binding.drawer.closeDrawer(GravityCompat.START)

                    true
                }

                else -> false
            }
        }
    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack("")
            .commit()
    }
}