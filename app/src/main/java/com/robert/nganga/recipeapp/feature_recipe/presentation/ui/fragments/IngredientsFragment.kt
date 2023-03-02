package com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.databinding.FragmentIngredientsBinding
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe

class IngredientsFragment: Fragment(R.layout.fragment_ingredients){
    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!

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
        binding.tvRecipe.text = recipe.summary

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}