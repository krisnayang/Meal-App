package com.example.mealapp.di

import com.example.mealapp.data.remote.ApiService
import com.example.mealapp.repository.CategoryRepositoryImpl
import com.example.mealapp.repository.CategoryResository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

  @Provides
  @Singleton
  fun provideNewsRepository(
    api: ApiService
  ): CategoryResository {
    return CategoryRepositoryImpl(api)
  }
}