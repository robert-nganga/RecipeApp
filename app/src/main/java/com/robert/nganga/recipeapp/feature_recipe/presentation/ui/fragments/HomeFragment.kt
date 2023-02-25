package com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.databinding.FragmentHomeBinding
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Category
import com.robert.nganga.recipeapp.feature_recipe.presentation.RecipeViewModel
import com.robert.nganga.recipeapp.feature_recipe.presentation.adapter.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.fragment_home) {
    private val viewModel: RecipeViewModel by viewModels()
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryAdapter = CategoryAdapter(initializeCategoryData())
        categoryAdapter.setOnItemClickListener { category ->
            viewModel.getTags(category.title)
        }

        setupCategoryRecyclerView()
//        viewModel.getTags(categoryAdapter.getSelectedCategory().title)

        viewModel.recipes.observe(viewLifecycleOwner){ response ->
            response.data?.let { recipes ->
                if(recipes.isNotEmpty()){
                    binding.textView2.text = recipes[0].title
                }
            }
        }
    }

    private fun setupCategoryRecyclerView() {
        binding.rvCategory.apply {
            adapter = categoryAdapter
        }
    }

    private fun initializeCategoryData(): List<Category> {
        val categories = arrayListOf<Category>()
        categories.add(Category(title ="bbq", image = R.drawable.bbq))
        categories.add(Category(title ="vegan", image = R.drawable.veg))
        categories.add(Category(title ="dessert", image = R.drawable.dessert))
        categories.add(Category(title ="sea Food", image = R.drawable.sea_food))
        categories.add(Category(title ="chicken", image = R.drawable.chicken))
        return categories
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}