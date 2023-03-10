package com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.databinding.FragmentFavoriteBinding
import com.robert.nganga.recipeapp.feature_recipe.presentation.adapter.FavoriteAdapter
import com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoritesFragment: Fragment(R.layout.fragment_favorite) {

    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var favoriteAdapter: FavoriteAdapter
    private var _binding : FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFavoriteRecyclerView()

        binding.floatingActionButton.setOnClickListener {
            showConfirmDialog()
        }

        viewModel.favorites.observe(viewLifecycleOwner) { favorites ->
            favorites.data?.let {
                favoriteAdapter.differ.submitList(it)
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val favorite = favoriteAdapter.differ.currentList[viewHolder.adapterPosition]
            viewModel.deleteFavoriteRecipe(favorite)
            Snackbar.make(requireView(), "Deleted", Snackbar.LENGTH_LONG)
                .setAction("Undo"){
                    viewModel.insertFavoriteRecipe(favorite)
                }.setAnchorView(binding.floatingActionButton)
                .show()
        }
    })

    private fun showConfirmDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.message))
            .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                // Respond to neutral button press
            }
            .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                // Respond to positive button press
                viewModel.deleteAllFavoriteRecipes()
            }
            .show()
    }

    private fun setupFavoriteRecyclerView() {
        favoriteAdapter = FavoriteAdapter()
        binding.rvFavorites.apply {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemTouchHelper.attachToRecyclerView(this)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}