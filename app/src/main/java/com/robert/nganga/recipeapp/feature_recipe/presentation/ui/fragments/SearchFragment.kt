package com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.databinding.FragmentSearchBinding
import com.robert.nganga.recipeapp.feature_recipe.presentation.adapter.SearchAdapter
import com.robert.nganga.recipeapp.feature_recipe.presentation.adapter.SearchByIngredientsAdapter
import com.robert.nganga.recipeapp.feature_recipe.presentation.ui.MainActivity
import com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel.SearchViewModel

class SearchFragment: Fragment(R.layout.fragment_search) {

    //Setup view binding
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: SearchAdapter
    private lateinit var viewModel: SearchViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).searchViewModel
        setupRecyclerView()

        binding.button.setOnClickListener {
            val query = binding.tvSearch.text.toString()
            viewModel.getSearchResults(query)
        }

        viewModel.searchResults.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Resource.Status.SUCCESS -> {
                    response.data?.let { searchResult ->
                        adapter.differ.submitList(searchResult)
                    }
                }
                Resource.Status.LOADING  -> {
                }
                Resource.Status.ERROR  -> {

                }
            }
        }

    }

    private fun setupRecyclerView() {
        adapter = SearchAdapter()
        binding.rvSearch.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}