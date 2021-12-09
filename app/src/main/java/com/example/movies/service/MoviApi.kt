package com.example.movies.service

import com.example.movies.model.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviApi{

    @GET("most-popular/")
    suspend fun getAllMovies(@Query("page") page: Int): Response<MovieModel>
}