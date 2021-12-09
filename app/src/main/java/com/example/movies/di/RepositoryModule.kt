package com.example.movies.di

import com.example.movies.repository.MovieRepositoryImpl
import com.example.movies.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(impl: MovieRepositoryImpl): MoviesRepository
}