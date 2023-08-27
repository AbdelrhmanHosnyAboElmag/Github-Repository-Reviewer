package com.example.githubrepoview.di

import android.content.Context
import androidx.room.Room
import com.example.githubrepoview.data.local.database.DatabaseRepositories
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DatabaseRepositories {
        return Room.databaseBuilder(
            context.applicationContext,
            DatabaseRepositories::class.java,
            "asteroids"
        ).build()
    }

}