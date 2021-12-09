package com.example.movies.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movies.model.MovieModel
import com.example.movies.paging.datasource.MoviePagingDataSource
import com.example.movies.service.MoviApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviApi: MoviApi
) : MoviesRepository {
    override suspend fun getAllCharacters(): Flow<PagingData<MovieModel>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { MoviePagingDataSource(moviApi) }
    ).flow
}