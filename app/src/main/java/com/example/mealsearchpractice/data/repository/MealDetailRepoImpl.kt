package com.example.mealsearchpractice.data.repository

import com.example.mealsearchpractice.base.network.ApiResponse
import com.example.mealsearchpractice.base.network.BaseRepository
import com.example.mealsearchpractice.data.model.MealsDTO
import com.example.mealsearchpractice.data.remote.MealSearchAPI
import com.example.mealsearchpractice.domain.repository.MealDetailRepository
import javax.inject.Inject

class MealDetailRepoImpl @Inject constructor(private val mealSearchAPI: MealSearchAPI) :
    BaseRepository(), MealDetailRepository {
    override suspend fun getMealDetail(id: String): ApiResponse<MealsDTO> =
        executeSafely(
            call = {
                mealSearchAPI.getMealDetails(id)
            }
        )
}