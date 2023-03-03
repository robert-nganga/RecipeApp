package com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.databinding.FragmentIngredientsBinding
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import com.robert.nganga.recipeapp.feature_recipe.presentation.adapter.IngredientsAdapter

class IngredientsFragment: Fragment(R.layout.fragment_ingredients){
    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!

    private lateinit var ingredientsAdapter: IngredientsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipe = arguments?.getSerializable("recipe") as Recipe

        ingredientsAdapter = IngredientsAdapter()
        binding.rvIngredients.adapter = ingredientsAdapter
        Log.i("IngredientsFragment", "onViewCreated: ${recipe.title}")
        ingredientsAdapter.differ.submitList(recipe.extendedIngredients)
        //setupIngredientsRecyclerView(recipe)



    }

    private fun setupIngredientsRecyclerView(recipe: Recipe) {
        Log.i("IngredientsFragment", "setupIngredientsRecyclerView: ${recipe.extendedIngredients}")
        ingredientsAdapter = IngredientsAdapter()
        binding.rvIngredients.apply {
            adapter = ingredientsAdapter
            setHasFixedSize(true)
        }
        ingredientsAdapter.differ.submitList(recipe.extendedIngredients)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}