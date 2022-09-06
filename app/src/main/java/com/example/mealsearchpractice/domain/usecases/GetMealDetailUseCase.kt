package com.example.mealsearchpractice.domain.usecases

import com.example.mealsearchpractice.base.ApiResponse
import com.example.mealsearchpractice.common.UIState
import com.example.mealsearchpractice.data.model.toDomainMeal
import com.example.mealsearchpractice.data.model.toDomainMealDetails
import com.example.mealsearchpractice.domain.model.Meal
import com.example.mealsearchpractice.domain.model.MealDetail
import com.example.mealsearchpractice.domain.repository.MealDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMealDetailUseCase @Inject constructor(private val mealDetailRepository: MealDetailRepository) {

    operator fun invoke(id: String): Flow<UIState<MealDetail>> = flow {
        try {
            emit(UIState.Loading())

            when (val response = mealDetailRepository.getMealDetail(id)) {
                is ApiResponse.Success -> {
                    val list = response.data.meals?.get(0)?.toDomainMealDetails()

                    emit(UIState.Success(data = list!!))
                }
                is ApiResponse.Error -> {
                    emit(UIState.Error(message = response.error.message))
                }
            }

        } catch (e: Exception) {
            emit(UIState.Error(message = e.localizedMessage ?: ""))
        }
    }

}