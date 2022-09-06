package com.example.mealsearchpractice.domain.usecases

import com.bumptech.glide.load.engine.Resource
import com.example.mealsearchpractice.base.ApiResponse
import com.example.mealsearchpractice.common.UIState
import com.example.mealsearchpractice.data.model.toDomainMeal
import com.example.mealsearchpractice.domain.model.Meal
import com.example.mealsearchpractice.domain.repository.MealSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMealSearchListUseCase @Inject constructor(private val mealSearchRepository: MealSearchRepository) {

    operator fun invoke(s: String): Flow<UIState<List<Meal>>> = flow {
        try {
            emit(UIState.Loading())

            when (val response = mealSearchRepository.getMealList(s)) {
                is ApiResponse.Success -> {
                    val list =
                        if (response.data.meals.isNullOrEmpty()) emptyList<Meal>()
                        else response.data.meals.map { it!!.toDomainMeal() }

                    emit(UIState.Success(data = list))
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