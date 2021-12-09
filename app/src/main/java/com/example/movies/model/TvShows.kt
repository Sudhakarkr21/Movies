package com.example.movies.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShows (

    @SerializedName("id"                   ) var id                 : Int?    = null,
    @SerializedName("name"                 ) var name               : String? = null,
    @SerializedName("permalink"            ) var permalink          : String? = null,
    @SerializedName("start_date"           ) var startDate          : String? = null,
    @SerializedName("end_date"             ) var endDate            : String? = null,
    @SerializedName("country"              ) var country            : String? = null,
    @SerializedName("network"              ) var network            : String? = null,
    @SerializedName("status"               ) var status             : String? = null,
    @SerializedName("image_thumbnail_path" ) var imageThumbnailPath : String? = null

) : Parcelable