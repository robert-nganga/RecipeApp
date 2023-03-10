package com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.databinding.FragmentIngredientsBinding
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel.RecipeViewModel
import com.robert.nganga.recipeapp.feature_recipe.presentation.adapter.IngredientsAdapter
import com.robert.nganga.recipeapp.feature_recipe.presentation.ui.MainActivity

class IngredientsFragment: Fragment(R.layout.fragment_ingredients){
    private lateinit var viewModel: RecipeViewModel
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
        viewModel = (activity as MainActivity).viewModel

        viewModel.recipe.observe(viewLifecycleOwner){ response ->
            response.data?.let { recipe ->
                setupIngredientsRecyclerView(recipe)
            }
        }
    }

    private fun setupIngredientsRecyclerView(recipe: Recipe) {
        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        ingredientsAdapter = IngredientsAdapter()
        binding.rvIngredients.apply {
            adapter = ingredientsAdapter
            addItemDecoration(decoration)
            setHasFixedSize(true)
        }
        ingredientsAdapter.differ.submitList(recipe.extendedIngredients)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}