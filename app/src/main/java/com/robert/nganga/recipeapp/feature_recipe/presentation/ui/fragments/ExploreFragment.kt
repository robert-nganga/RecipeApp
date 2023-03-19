package com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.databinding.FragmentExploreBinding
import com.robert.nganga.recipeapp.feature_recipe.presentation.adapter.SearchByIngredientsAdapter
import com.robert.nganga.recipeapp.feature_recipe.presentation.ui.MainActivity
import com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel.SearchByIngredientsViewModel
import dagger.hilt.android.AndroidEntryPoint


class ExploreFragment: Fragment(R.layout.fragment_explore) {

    private lateinit var viewModel: SearchByIngredientsViewModel
    private lateinit var adapter: SearchByIngredientsAdapter

    private var _binding : FragmentExploreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).searchByIngredientsViewModel

        adapter = SearchByIngredientsAdapter()
        binding.rvRecipeByIngredients.adapter = adapter

        binding.btnApply.setOnClickListener {
            val text = binding.etIngredient.text.toString()
            if (text.isNotEmpty()) {
                setChips(text)
                binding.etIngredient.text?.clear()
            }
        }

        binding.floatingActionButton.setOnClickListener {
            val tags = binding.chipGroup.children.map { it as Chip }.map { it.text.toString() }.joinToString(",")
            viewModel.getQuery(tags)
        }

        viewModel.result.observe(viewLifecycleOwner) { response->
            response.data?.let {
                adapter.differ.submitList(it)
            }

        }


    }

    private fun setChips(text: String){
        val chip = layoutInflater.inflate(R.layout.input_chip_item, binding.chipGroup, false) as Chip
        chip.text = text
        binding.chipGroup.addView(chip)
        chip.setOnCloseIconClickListener {
            binding.chipGroup.removeView(chip)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}