package com.example.githubrepoview.di

import com.example.githubrepoview.repositories.RepositoriesItemRepository
import com.example.githubrepoview.repositories.RepositoriesItemRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideRepositoriesItemRepository(repository: RepositoriesItemRepositoryImpl): RepositoriesItemRepository {
        return repository
    }
}