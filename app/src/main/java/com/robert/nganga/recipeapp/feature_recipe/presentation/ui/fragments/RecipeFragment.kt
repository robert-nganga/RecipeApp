package com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.databinding.FragmentRecipeBinding
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import com.robert.nganga.recipeapp.feature_recipe.presentation.adapter.PagerAdapter
import com.robert.nganga.recipeapp.feature_recipe.presentation.ui.MainActivity
import com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel.FavoriteViewModel
import com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel.RecipeViewModel


class RecipeFragment: Fragment(R.layout.fragment_recipe){

    private lateinit var favoriteViewModel: FavoriteViewModel
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
        favoriteViewModel = (activity as MainActivity).favoriteViewModel
        viewModel.getRecipe(args.id)
        viewPager2 = binding.viewPager
        tabLayout = binding.tabLayout
        setupViewPager()

        setupListeners()

        viewModel.recipe.observe(viewLifecycleOwner) { response ->
            when(response.status){
                Resource.Status.SUCCESS -> {
                    binding.progressBarRecipe.visibility = View.GONE
                    binding.makeViewsVisible()
                    response.data?.let {
                        binding.bindRecipeData(it)
                        this.recipe = it
                    }
                }
                Resource.Status.ERROR -> {
                    binding.progressBarRecipe.visibility = View.GONE
                    if (response.data == null){
                        binding.makeViewsInvisible()
                        response.message?.let { message ->
                            showErrorMessages(message)
                        }
                    }else{
                        binding.makeViewsVisible()
                        response.data.let {
                            binding.bindRecipeData(it)
                            this.recipe = it
                        }
                    }
                }
                Resource.Status.LOADING -> {
                    binding.tvErrorRecipe.visibility = View.INVISIBLE
                    binding.btnRetryRecipe.visibility = View.INVISIBLE
                    if (response.data == null) {
                        binding.makeViewsInvisible()
                        binding.progressBarRecipe.visibility = View.VISIBLE
                    }else{
                        binding.makeViewsVisible()
                        response.data.let {
                            binding.bindRecipeData(it)
                            this.recipe = it
                        }

                    }
                }
            }
        }
    }

    private fun showErrorMessages(message: String){
        binding.tvErrorRecipe.text = message
        binding.tvErrorRecipe.visibility = View.VISIBLE
        binding.btnRetryRecipe.visibility = View.VISIBLE
    }

    private fun setupListeners() {

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.imgIsFavorite.setOnClickListener {
            recipe?.let { recipe->
                handleFavorite(recipe)
            }
        }

        binding.btnRetryRecipe.setOnClickListener {
            viewModel.getRecipe(args.id)
        }

        binding.extendedFab.setOnClickListener {
            recipe?.sourceUrl?.let { recipeUrl ->
                openWebPage(recipeUrl)
            }
        }

        binding.imgShareRecipe.setOnClickListener {
            recipe?.sourceUrl?.let { recipeUrl ->
                val context = requireContext()
                context.shareRecipe(recipeUrl)
            }
        }
    }

    private fun handleFavorite(favorite: Recipe) {
        if (favorite.isFavorite) {
            favoriteViewModel.deleteFavoriteRecipe(favorite)
            Snackbar.make(requireView(), "Recipe removed from favorites", Snackbar.LENGTH_SHORT)
                .setAnchorView(binding.extendedFab)
                .show()
        } else {
            favoriteViewModel.insertFavoriteRecipe(favorite)
            Snackbar.make(requireView(), "Recipe added to favorites", Snackbar.LENGTH_SHORT)
                .setAnchorView(binding.extendedFab)
                .show()
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

    @SuppressLint("QueryPermissionsNeeded")
    private fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        val packageManager = requireActivity().packageManager
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun FragmentRecipeBinding.makeViewsInvisible(){
        this.apply {
            tvRecipeTitle.visibility = View.INVISIBLE
            tvRecipeTime.visibility = View.INVISIBLE
            tvServing.visibility = View.INVISIBLE
            imgToolBar.visibility = View.INVISIBLE
            extendedFab.visibility = View.INVISIBLE
            imgShareRecipe.visibility = View.INVISIBLE
            tabLayout.visibility = View.INVISIBLE
            viewPager.visibility = View.INVISIBLE
            imageView4.visibility = View.INVISIBLE
            imageView7.visibility = View.INVISIBLE
            imgIsFavorite.visibility = View.INVISIBLE
        }
    }

    private fun FragmentRecipeBinding.makeViewsVisible(){
        this.apply {
            imageView4.visibility = View.VISIBLE
            imageView7.visibility = View.VISIBLE
            tvRecipeTitle.visibility = View.VISIBLE
            tvRecipeTime.visibility = View.VISIBLE
            tvServing.visibility = View.VISIBLE
            imgToolBar.visibility = View.VISIBLE
            extendedFab.visibility = View.VISIBLE
            imgShareRecipe.visibility = View.VISIBLE
            tabLayout.visibility = View.VISIBLE
            viewPager.visibility = View.VISIBLE
            imgIsFavorite.visibility = View.VISIBLE
        }
    }

    private fun FragmentRecipeBinding.bindRecipeData(recipe: Recipe){
        val icFavoriteFilledImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_favorite_24, null)
        val icFavoriteBorderImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_favorite_border_white_24, null)
        val time = "${recipe.readyInMinutes} mins"
        val servings = "${recipe.servings} servings"
        this.apply {
            if (recipe.isFavorite){
                imgIsFavorite.setImageDrawable(icFavoriteFilledImage)
            }else{
                imgIsFavorite.setImageDrawable(icFavoriteBorderImage)
            }
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