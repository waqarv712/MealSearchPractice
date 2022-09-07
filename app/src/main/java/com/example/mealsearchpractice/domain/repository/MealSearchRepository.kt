package com.example.mealsearchpractice.domain.repository

import com.example.mealsearchpractice.base.network.ApiResponse
import com.example.mealsearchpractice.data.model.MealsDTO

interface MealSearchRepository {
    suspend fun getMealList(s: String): ApiResponse<MealsDTO>
}