package com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.databinding.FragmentRecipeBinding
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import com.robert.nganga.recipeapp.feature_recipe.presentation.RecipeViewModel
import com.robert.nganga.recipeapp.feature_recipe.presentation.adapter.PagerAdapter
import com.robert.nganga.recipeapp.feature_recipe.presentation.ui.MainActivity

class RecipeFragment: Fragment(R.layout.fragment_recipe){
    private lateinit var viewModel: RecipeViewModel
    private var _binding : FragmentRecipeBinding? = null
    private val binding get() = _binding!!
    private val args: RecipeFragmentArgs by navArgs()

    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout

    val screens = listOf(
        "Ingredients",
        "Preparation",
        "Summary"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        viewModel.getIds(args.id)

        viewPager2 = binding.viewPager
        tabLayout = binding.tabLayout

        viewModel.recipe.observe(viewLifecycleOwner) { recipe ->
            recipe.data?.let {
                setupViewPager(it)
                binding.bindRecipeData(it)
            }
        }
    }

    private fun FragmentRecipeBinding.bindRecipeData(recipe: Recipe){
        val time = "${recipe.readyInMinutes} mins"
        this.apply {
            tvRecipeTitle.text = recipe.title
            tvRecipeTime.text = time
            tvServing.text = recipe.servings.toString()
            Glide.with(requireContext()).load(recipe.image).into(imgToolBar)
        }
    }

    private fun setupViewPager(recipe: Recipe){
        val pagerAdapter = PagerAdapter(recipe, childFragmentManager, lifecycle)
        viewPager2.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = screens[position]
        }.attach()
    }

    override fun onPause() {
        super.onPause()
        // clear the data in the views
        binding.apply {
            tvRecipeTitle.text = ""
            tvRecipeTime.text = ""
            tvServing.text = ""
            imgToolBar.setImageResource(0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}