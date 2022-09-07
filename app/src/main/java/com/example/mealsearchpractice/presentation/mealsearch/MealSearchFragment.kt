package com.example.mealsearchpractice.presentation.mealsearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.mealsearchpractice.R
import com.example.mealsearchpractice.base.BaseFragment
import com.example.mealsearchpractice.databinding.FragmentMealSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealSearchFragment : BaseFragment<FragmentMealSearchBinding>() {

    private val viewModel: MealSearchViewModel by viewModels()
    private val searchAdapter = MealSearchAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mealSearchRecycler.apply {
            adapter = searchAdapter
        }

        binding.mealSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchMealList(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean = false

        })


        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.mealSearchList.collect{
                if (it.isLoading){
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()){
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                it.data?.let {
                    if (it.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    binding.progressMealSearch.visibility = View.GONE
                    searchAdapter.setContentList(it.toMutableList())
                }
            }
        }

        searchAdapter.itemClickListener {
            findNavController()
                .navigate(MealSearchFragmentDirections.actionMealSearchFragmentToMealDetailsFragment(
                    it.mealId
                ))
        }

    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMealSearchBinding.inflate(inflater, container, false)

}