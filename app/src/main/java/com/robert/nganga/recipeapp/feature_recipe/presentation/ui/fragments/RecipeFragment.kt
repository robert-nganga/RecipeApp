package com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments

import android.content.Context
import android.content.Intent
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

    private val screens = listOf(
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

        binding.imgShareRecipe.setOnClickListener {
            viewModel.recipe.value?.data?.sourceUrl?.let { url->
                val context = requireContext()
                context.shareRecipe(url)
            }
        }
    }

    private fun Context.shareRecipe(recipeUrl: String){
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, recipeUrl)
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun FragmentRecipeBinding.bindRecipeData(recipe: Recipe){
        val time = "${recipe.readyInMinutes} mins"
        val servings = "${recipe.servings} servings"
        this.apply {
            tvRecipeTitle.text = recipe.title
            tvRecipeTime.text = time
            tvServing.text = servings
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}