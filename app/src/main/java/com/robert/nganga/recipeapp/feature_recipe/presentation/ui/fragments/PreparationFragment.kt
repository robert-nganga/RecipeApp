package com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.databinding.FragmentPreparationBinding
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import com.robert.nganga.recipeapp.feature_recipe.presentation.adapter.PreparationAdapter

class PreparationFragment: Fragment(R.layout.fragment_preparation) {
    private var _binding: FragmentPreparationBinding? = null
    private val binding get() = _binding!!

    private lateinit var preparationAdapter: PreparationAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPreparationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipe = arguments?.getSerializable("recipe") as Recipe
        preparationAdapter = PreparationAdapter()
        binding.rvPreparation.apply {
            adapter = preparationAdapter
        }
        if (recipe.analyzedInstructions.isNotEmpty()) {
            preparationAdapter.differ.submitList(recipe.analyzedInstructions[0].steps)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}