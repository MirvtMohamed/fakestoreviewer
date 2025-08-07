package com.example.fakestoreviewer.di

import com.example.fakestoreviewer.data.remote.api.ProductApi
import com.example.fakestoreviewer.data.remote.api.RetrofitProvider
import com.example.fakestoreviewer.data.repository.ProductRepositoryImpl
import com.example.fakestoreviewer.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import jakarta.inject.Singleton

import dagger.hilt.components.SingletonComponent
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi = RetrofitProvider.api

    @Provides
    @Singleton
    fun provideProductRepository(api: ProductApi): ProductRepository = ProductRepositoryImpl(api)
}
