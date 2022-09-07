package com.example.mealsearchpractice.presentation.mealdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mealsearchpractice.base.BaseFragment
import com.example.mealsearchpractice.databinding.FragmentMealDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetailsFragment : BaseFragment<FragmentMealDetailsBinding>() {

    private val viewModel: MealDetailsViewModel by viewModels()

    private val args: MealDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        args.mealId?.let {
            viewModel.getMealDetails(it)
        }


        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.mealDetails.collect { it ->
                if (it.isLoading) {
                }
                if (it.error.isNotBlank()) {
                    Toast.makeText(requireContext(),it.error,Toast.LENGTH_SHORT).show()
                }
                it.data?.let {
                    binding.mealDetails = it
                }
            }
        }


        binding.detailsBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }


    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentMealDetailsBinding.inflate(inflater, container, false)

}