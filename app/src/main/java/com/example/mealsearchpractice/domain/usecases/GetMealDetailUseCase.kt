package com.example.mealsearchpractice.domain.usecases

import com.example.mealsearchpractice.base.network.ApiResponse
import com.example.mealsearchpractice.common.Resource
import com.example.mealsearchpractice.data.model.toDomainMealDetails
import com.example.mealsearchpractice.domain.model.MealDetail
import com.example.mealsearchpractice.domain.repository.MealDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMealDetailUseCase @Inject constructor(private val mealDetailRepository: MealDetailRepository) {

    operator fun invoke(id: String): Flow<Resource<MealDetail>> = flow {
        try {
            emit(Resource.Loading())

            when (val response = mealDetailRepository.getMealDetail(id)) {
                is ApiResponse.Success -> {
                    val list = response.data.meals?.get(0)?.toDomainMealDetails()

                    emit(Resource.Success(data = list!!))
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