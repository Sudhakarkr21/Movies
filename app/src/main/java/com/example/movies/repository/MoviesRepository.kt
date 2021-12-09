package com.example.movies.repository

import androidx.paging.PagingData
import com.example.movies.model.MovieModel
import kotlinx.coroutines.flow.Flow


interface MoviesRepository {
    suspend fun getAllCharacters(): Flow<PagingData<MovieModel>>
}