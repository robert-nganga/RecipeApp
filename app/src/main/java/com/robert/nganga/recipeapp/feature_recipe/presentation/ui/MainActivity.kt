package com.robert.nganga.recipeapp.feature_recipe.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.databinding.ActivityMainBinding
import com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel.FavoriteViewModel
import com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel.RecipeViewModel
import com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel.SearchByIngredientsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: RecipeViewModel by viewModels()
    val favoriteViewModel: FavoriteViewModel by viewModels()
    val searchByIngredientsViewModel: SearchByIngredientsViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setup nav host fragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        val navController = navHostFragment.navController

        //make bottom nav view invisible when on recipe fragment
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when(destination.id){
                R.id.recipeFragment -> {binding.bottomNavView.visibility = View.GONE}
                else -> {binding.bottomNavView.visibility = View.VISIBLE}
            }

        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        bottomNav.setupWithNavController(navController)

    }
}