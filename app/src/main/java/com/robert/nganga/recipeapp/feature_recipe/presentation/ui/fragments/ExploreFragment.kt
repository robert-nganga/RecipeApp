package com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.databinding.FragmentExploreBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ExploreFragment: Fragment(R.layout.fragment_explore) {

    private var _binding : FragmentExploreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etIngredient.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val text = binding.etIngredient.text.toString()
                if (text.isNotEmpty()) {
                    setChips(text)
                    binding.etIngredient.text?.clear()
                }
            }
            true
        }

        binding.btnApply.setOnClickListener {
            val text = binding.etIngredient.text.toString()
            if (text.isNotEmpty()) {
                setChips(text)
                binding.etIngredient.text?.clear()
            }
        }
    }

    private fun setChips(text: String){
        val chip = layoutInflater.inflate(R.layout.input_chip_item, binding.chipGroup, false) as Chip
        chip.text = text
        binding.chipGroup.addView(chip)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}