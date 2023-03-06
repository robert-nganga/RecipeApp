package com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.robert.nganga.recipeapp.BuildConfig
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.core.util.Constants.BASE_RECIPE_WIDGET_URL
import com.robert.nganga.recipeapp.databinding.FragmentSummaryBinding
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import com.robert.nganga.recipeapp.feature_recipe.presentation.RecipeViewModel
import com.robert.nganga.recipeapp.feature_recipe.presentation.ui.MainActivity

class SummaryFragment: Fragment(R.layout.fragment_summary) {
    private lateinit var viewModel: RecipeViewModel
    private var _binding: FragmentSummaryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        viewModel.recipe.observe(viewLifecycleOwner){ response->
            response.data?.let { recipe ->
                val regex = Regex("<.*?>")
                val summary = regex.replace(recipe.summary, "")
                binding.tvRecipeSummary.text = summary

                val imageUrl = "$BASE_RECIPE_WIDGET_URL${recipe.id}/nutritionWidget.png?apiKey=${BuildConfig.API_KEY}"
                Glide.with(requireContext())
                    .load(imageUrl)
                    .into(binding.imgSummary)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}