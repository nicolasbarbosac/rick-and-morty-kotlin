package com.example.rick_and_morti.models

import com.google.gson.annotations.SerializedName


data class Info (

    @SerializedName("count" ) var count : Int?    = null,
    @SerializedName("pages" ) var pages : Int?    = null,
    @SerializedName("next"  ) var next  : String? = null,
    @SerializedName("prev"  ) var prev  : String? = null

)