package com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.databinding.FragmentHomeBinding
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import com.robert.nganga.recipeapp.feature_recipe.presentation.adapter.RecipeAdapter
import com.robert.nganga.recipeapp.feature_recipe.presentation.ui.MainActivity
import com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel.FavoriteViewModel
import com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel.RecipeViewModel
import java.util.ArrayList


class HomeFragment: Fragment(R.layout.fragment_home) {
    private lateinit var viewModel: RecipeViewModel
    private lateinit var favoriteViewModel: FavoriteViewModel
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recipeAdapter: RecipeAdapter

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
        viewModel = (activity as MainActivity).viewModel
        favoriteViewModel = (activity as MainActivity).favoriteViewModel
        recipeAdapter = RecipeAdapter()
        binding.rvRecipe.adapter = recipeAdapter


        recipeAdapter.setOnItemClickListener { recipe->
            val bundle = Bundle().apply {
                putInt("id", recipe.id)
            }
            findNavController().navigate(R.id.action_homeFragment_to_recipeFragment, bundle)
        }

        recipeAdapter.setOnFavoriteClickListener {
            favoriteViewModel.toggleFavorite(it)
        }

        setupChipGroup()

        viewModel.recipes.observe(viewLifecycleOwner){ response ->
            when(response.status){
                 Resource.Status.SUCCESS-> {
                     hideViews()
                     displayData(response)
                }

                Resource.Status.ERROR-> {
                    toggleProgressBar(false)
                    displayData(response)
                    response.message?.let { message ->
                        if (response.data.isNullOrEmpty()) {
                            showErrorMessage(message)
                        }
                    }
                }

                Resource.Status.LOADING-> {
                    displayData(response)
                    if (response.data.isNullOrEmpty()) {
                        toggleProgressBar(true)
                    }
                }
            }
            response.data?.let { recipes ->
                if(recipes.isNotEmpty()){
                    recipeAdapter.differ.submitList(recipes)
                }
            }
        }
    }

    private fun displayData(response: Resource<List<Recipe>>) {
        response.data?.let { recipes ->
            if(recipes.isNotEmpty()){
                recipeAdapter.differ.submitList(recipes)
            }
        }
    }

    private fun showErrorMessage(message: String){
        binding.tvError.text = message
        binding.tvError.visibility = View.VISIBLE
        binding.btnRetry.visibility = View.VISIBLE
    }

    private fun toggleProgressBar(isLoading: Boolean){
        if(isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    private fun hideViews(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.tvError.visibility = View.INVISIBLE
        binding.btnRetry.visibility = View.INVISIBLE
    }

    private fun setupChipGroup() {
        val category = viewModel.tag.value
        getSelectedCategoryChip(category)?.let { binding.chipGroup.check(it) }
        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            if(checkedIds.isNotEmpty()){
                val chip = group.findViewById<Chip>(checkedIds.first())
                val tag = chip.text.toString()
                Toast.makeText(context, tag, Toast.LENGTH_SHORT).show()
                viewModel.updateCategory(tag)
            }
        }
    }

    private fun getSelectedCategoryChip(tag: String?): Int? {
        val chips = ArrayList<View>()
        binding.chipGroup.findViewsWithText(chips, tag, View.FIND_VIEWS_WITH_TEXT)

        return if (chips.isNotEmpty()) {
            val chip = chips[0] as Chip
            chip.id
        }else{
            null
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}