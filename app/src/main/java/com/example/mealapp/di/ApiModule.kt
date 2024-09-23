package com.example.mealapp.di

import com.example.mealapp.data.remote.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
  @Provides
  @Singleton
  fun provideMyApi(): ApiService {
    return Retrofit.Builder().addConverterFactory(
      MoshiConverterFactory.create(
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
      )
    )
      .baseUrl("https://themealdb.com/api/json/v1/1/")
      .build()
      .create(ApiService::class.java)
  }
}