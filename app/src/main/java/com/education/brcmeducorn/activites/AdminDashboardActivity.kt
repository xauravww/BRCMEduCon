package com.education.brcmeducorn.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.education.brcmeducorn.R
import com.education.brcmeducorn.fragments.admin_dashboard_fragments.AdminDashboardFragment
import com.education.brcmeducorn.fragments.EventsFragment
import com.education.brcmeducorn.utils.SharedPrefs
import com.google.android.material.appbar.AppBarLayout
 import com.google.android.material.navigation.NavigationView

class AdminDashboardActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var frameLayout: FrameLayout
    private lateinit var appBar: AppBarLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        toolbar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        frameLayout = findViewById(R.id.frameLayout)
        appBar = findViewById(R.id.appBar)
        setupToolbar()
        setupNavigation()
        onBackPressedHandler()
        openHome()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, AdminDashboardFragment())
 
                .commit()
        }
    }

    private fun onBackPressedHandler() {
        onBackPressedDispatcher.addCallback(this,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                when(supportFragmentManager.findFragmentById(R.id.frameLayout)){
                    !is AdminDashboardFragment -> {
                        openHome()
                    }
                    else-> finish()
                }
            }
        })
    }

    private fun setupNavigation() {
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> openHome()
                R.id.events -> supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, EventsFragment()).commit()

                R.id.logOut -> SharedPrefs(this).logOut(this)

            }
            drawerLayout.closeDrawers()
            return@setNavigationItemSelectedListener true
        }
    }

    private fun openHome() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, AdminDashboardFragment()).commit()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setHomeButtonEnabled(true)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appBar.outlineProvider = null
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@AdminDashboardActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

}
