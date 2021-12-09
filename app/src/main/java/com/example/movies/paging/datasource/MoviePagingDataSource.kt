package com.example.movies.paging.datasource

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movies.model.MovieModel
import com.example.movies.service.MoviApi

public class MoviePagingDataSource(private val moviApi: MoviApi) :
        PagingSource<Int , MovieModel>(){

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val pageNumber = params.key ?: 1

        return try {
            val response = moviApi.getAllMovies(pageNumber)
            val pagedResponse = response.body()
            val data = pagedResponse

           /* var nextPageNumber: Int? = null
            if (pagedResponse != null) {
                val uri = Uri.parse(pagedResponse.page.toString())
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }*/
            val responseData = mutableListOf<MovieModel>()
            if (data != null) {
                responseData.add(data)
            }
            LoadResult.Page(
                data = responseData,
                prevKey = if (pageNumber == 1) null else pageNumber -1,
                nextKey = if (pageNumber < responseData[0].pages!!)
                    responseData[0].page?.plus(1) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

        /*return try {
            val response = moviApi.getAllMovies(pageNumber)
            val pagedResponse = response.body()
            val responseData = mutableListOf<MovieModel>()
            responseData.add((pagedResponse?: emptyArray<MovieModel>()) as MovieModel)
         *//*   var nextPageNumber: Int? = null
            if (pagedResponse?.page != null) {
                val uri = Uri.parse(pagedResponse.page.toString())
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }*//*

            val prevKey = if (pageNumber == 1) null else pageNumber - 1


            LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = pageNumber.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }*/
    }
}