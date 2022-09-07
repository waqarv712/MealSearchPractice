package com.example.mealsearchpractice.data.repository

import com.example.mealsearchpractice.base.network.ApiResponse
import com.example.mealsearchpractice.base.network.BaseRepository
import com.example.mealsearchpractice.data.model.MealsDTO
import com.example.mealsearchpractice.data.remote.MealSearchAPI
import com.example.mealsearchpractice.domain.repository.MealSearchRepository
import javax.inject.Inject

class MealSearchRepoImpl @Inject constructor(private val mealSearchAPI: MealSearchAPI) :
    BaseRepository(), MealSearchRepository {
    override suspend fun getMealList(s: String): ApiResponse<MealsDTO> =
        executeSafely(
            call = {
                mealSearchAPI.getSearchMealList(s)
            }
        )

}