package com.example.movies.model

import com.google.gson.annotations.SerializedName

data class MovieModel (

    @SerializedName("total"    ) var total   : String?       = null,
    @SerializedName("page"     ) var page    : Int?          = null,
    @SerializedName("pages"    ) var pages   : Int?          = null,
    @SerializedName("tv_shows" ) var tvShows : List<TvShows> = arrayListOf()

)