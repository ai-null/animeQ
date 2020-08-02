package com.gabutproject.animeq.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.gabutproject.animeq.R
import com.gabutproject.animeq.ui.fragment.BookmarkFragment
import com.gabutproject.animeq.ui.fragment.MainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set layout for this activity
        setContentView(R.layout.activity_main)

        // find navigation controller to work with fragment navigation library
        val navController = this.findNavController(R.id.navHostFragment)

        // setup for back arrow function
        NavigationUI.setupActionBarWithNavController(this, navController)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHostFragment)
        return navController.navigateUp()
    }
}