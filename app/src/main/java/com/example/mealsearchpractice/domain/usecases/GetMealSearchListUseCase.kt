package com.example.mealsearchpractice.domain.usecases

import com.example.mealsearchpractice.base.network.ApiResponse
import com.example.mealsearchpractice.common.Resource
import com.example.mealsearchpractice.data.model.toDomainMeal
import com.example.mealsearchpractice.domain.model.Meal
import com.example.mealsearchpractice.domain.repository.MealSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMealSearchListUseCase @Inject constructor(private val mealSearchRepository: MealSearchRepository) {

    operator fun invoke(s: String): Flow<Resource<List<Meal>>> = flow {
        try {
            emit(Resource.Loading())

            when (val response = mealSearchRepository.getMealList(s)) {
                is ApiResponse.Success -> {
                    val list =
                        if (response.data.meals.isNullOrEmpty()) emptyList<Meal>()
                        else response.data.meals.map { it!!.toDomainMeal() }

                    emit(Resource.Success(data = list))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(message = response.error.message))
                }
            }

        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: ""))
        }
    }

}