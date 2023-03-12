package com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.databinding.FragmentRecipeBinding
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import com.robert.nganga.recipeapp.feature_recipe.presentation.adapter.PagerAdapter
import com.robert.nganga.recipeapp.feature_recipe.presentation.ui.MainActivity
import com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel.FavoriteViewModel
import com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeFragment: Fragment(R.layout.fragment_recipe){

    private val favoriteViewModel: FavoriteViewModel by viewModels()
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

    private var recipe: Recipe? = null

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
        setupViewPager()

        binding.toolbar.inflateMenu(R.menu.top_app_bar)
        binding.toolbar.setOnMenuItemClickListener { it ->
            when(it.itemId){
                R.id.favoriteIcon -> {
                    recipe?.let { recipe->
                        favoriteViewModel.insertFavoriteRecipe(recipe.toFavorite())
                    }
                    Toast.makeText(requireContext(), "Recipe added to favorites", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

        binding.extendedFab.setOnClickListener {
            recipe?.sourceUrl?.let { recipeUrl ->
                openWebPage(recipeUrl)
            }
        }

        viewModel.recipe.observe(viewLifecycleOwner) { recipe ->
            recipe.data?.let {
                binding.bindRecipeData(it)
                this.recipe = it
            }
        }

        binding.imgShareRecipe.setOnClickListener {
            recipe?.sourceUrl?.let { recipeUrl ->
                val context = requireContext()
                context.shareRecipe(recipeUrl)
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

    private fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        val packageManager = requireActivity().packageManager
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun FragmentRecipeBinding.bindRecipeData(recipe: Recipe){
        val icFavoriteFilledImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_favorite_24, null)
        val icFavoriteBorderImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_favorite_border_white_24, null)
        val time = "${recipe.readyInMinutes} mins"
        val servings = "${recipe.servings} servings"
        this.apply {
            extendedFab.text = recipe.sourceName
            tvRecipeTitle.text = recipe.title
            tvRecipeTime.text = time
            tvServing.text = servings
            Glide.with(requireContext()).load(recipe.image).into(imgToolBar)
        }
    }


    private fun setupViewPager(){
        val pagerAdapter = PagerAdapter(childFragmentManager, lifecycle)
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