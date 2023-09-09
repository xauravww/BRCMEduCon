package com.education.brcmeducorn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class DashboardActivity : AppCompatActivity() {
    lateinit var toolbar:Toolbar
    lateinit var drawerLayout:DrawerLayout
    lateinit var navigationView:NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        toolbar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        setupToolbar()
        setupNaviagtion()
    }

    private fun setupNaviagtion() {
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.logOut -> Toast.makeText(this@DashboardActivity,"Log Out Feature",Toast.LENGTH_SHORT).show()
            }
            return@setNavigationItemSelectedListener true
        }
    }

    private fun setupToolbar(){
         setSupportActionBar(toolbar)
        supportActionBar?.title=""
        supportActionBar?.setHomeButtonEnabled(true)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var actionBarDrawerToggle =ActionBarDrawerToggle(this@DashboardActivity,drawerLayout,R.string.open_drawer,R.string.close_drawer)
       drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
}