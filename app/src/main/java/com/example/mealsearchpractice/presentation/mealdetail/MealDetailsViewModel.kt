package com.example.mealsearchpractice.presentation.mealdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsearchpractice.common.Resource
import com.example.mealsearchpractice.domain.usecases.GetMealDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(private val mealDetailsUseCase: GetMealDetailUseCase) :
    ViewModel() {


    private val _mealDetails = MutableStateFlow<MealDetailsState>(MealDetailsState())
    val mealDetails: StateFlow<MealDetailsState> = _mealDetails


    fun getMealDetails(id: String) {
        mealDetailsUseCase(id).onEach {
            when (it) {
                is Resource.Loading -> {
                    _mealDetails.value = MealDetailsState(isLoading = true)
                }
                is Resource.Error -> {
                    _mealDetails.value = MealDetailsState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _mealDetails.value = MealDetailsState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }


}