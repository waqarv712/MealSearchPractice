package com.example.mealsearchpractice.di

import com.example.mealsearchpractice.data.remote.MealSearchAPI
import com.example.mealsearchpractice.data.repository.MealDetailRepoImpl
import com.example.mealsearchpractice.data.repository.MealSearchRepoImpl
import com.example.mealsearchpractice.domain.repository.MealDetailRepository
import com.example.mealsearchpractice.domain.repository.MealSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideMealSearchRepository(mealSearchAPI: MealSearchAPI): MealSearchRepository{
        return MealSearchRepoImpl(mealSearchAPI)
    }

    @Provides
    fun provideMealDetailRepository(mealSearchAPI: MealSearchAPI): MealDetailRepository{
        return MealDetailRepoImpl(mealSearchAPI)
    }
}