package com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.databinding.FragmentSearchBinding
import com.robert.nganga.recipeapp.feature_recipe.domain.model.SearchResult
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

        binding.btnRetrySearch.setOnClickListener {
            val query = binding.tvSearch.text.toString()
            viewModel.getSearchResults(query)
        }

        adapter.setOnItemClickListener { searchResult ->
            val bundle = Bundle().apply {
                putInt("id", searchResult.id)
            }
            findNavController().navigate(R.id.action_searchFragment_to_recipeFragment, bundle)
        }

        viewModel.searchResults.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Resource.Status.SUCCESS -> {
                    toggleProgressBar(false)
                    binding.rvSearch.visibility = View.VISIBLE
                    response.data?.let { searchResult ->
                        adapter.differ.submitList(searchResult)
                    }
                }
                Resource.Status.LOADING  -> {
                    toggleProgressBar(true)
                }
                Resource.Status.ERROR  -> {
                    toggleProgressBar(false)
                    showErrorMessage(response)
                }
            }
        }

    }

    private fun toggleProgressBar(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                searchProgressBar.visibility = View.VISIBLE
                tvErrorSearch.visibility = View.INVISIBLE
                btnRetrySearch.visibility = View.INVISIBLE
                rvSearch.visibility = View.INVISIBLE
            } else {
                searchProgressBar.visibility = View.INVISIBLE
            }
        }
    }


    private fun showErrorMessage(response: Resource<List<SearchResult>>) {
        response.message?.let { message ->
            binding.apply {
                tvErrorSearch.text = message
                tvErrorSearch.visibility = View.VISIBLE
                btnRetrySearch.visibility = View.VISIBLE
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