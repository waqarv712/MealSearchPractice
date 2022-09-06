package com.example.mealsearchpractice.domain.repository

import com.example.mealsearchpractice.base.ApiResponse
import com.example.mealsearchpractice.data.model.MealsDTO

interface MealDetailRepository {
    suspend fun getMealDetail(id: String): ApiResponse<MealsDTO>
}