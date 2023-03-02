package com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.databinding.FragmentRecipeBinding
import com.robert.nganga.recipeapp.feature_recipe.presentation.RecipeViewModel
import com.robert.nganga.recipeapp.feature_recipe.presentation.adapter.PagerAdapter
import com.robert.nganga.recipeapp.feature_recipe.presentation.ui.MainActivity

class RecipeFragment: Fragment(R.layout.fragment_recipe){
    private lateinit var viewModel: RecipeViewModel
    private var _binding : FragmentRecipeBinding? = null
    private val binding get() = _binding!!
    private val args: RecipeFragmentArgs by navArgs()

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

        val screens = listOf(
            "Ingredients",
            "Preparation",
            "Summary"
        )

        val viewPager2 = binding.viewPager
        val tabLayout = binding.tabLayout

        val pagerAdapter = PagerAdapter(childFragmentManager, lifecycle)
        viewPager2.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = screens[position]
        }.attach()

        viewModel.recipe.observe(viewLifecycleOwner) { recipe ->
            recipe.data?.let {
                pagerAdapter.setRecipe(it)
                val time = "${it.readyInMinutes} mins"
                binding.apply {
                    tvRecipeTitle.text = it.title
                    tvRecipeTime.text = time
                    tvServing.text = it.servings.toString()
                    Glide.with(view).load(it.image).into(binding.imgToolBar)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}