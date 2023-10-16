package com.example.rick_and_morti.models

import com.google.gson.annotations.SerializedName


data class MainResponse(

    @SerializedName("info") var info: Info? = Info(),
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf()

)