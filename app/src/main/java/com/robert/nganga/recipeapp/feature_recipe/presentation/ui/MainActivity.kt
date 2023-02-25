package com.robert.nganga.recipeapp.feature_recipe.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.databinding.ActivityMainBinding
import com.robert.nganga.recipeapp.feature_recipe.presentation.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: RecipeViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setup nav host fragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        bottomNav.setupWithNavController(navController)

    }
}