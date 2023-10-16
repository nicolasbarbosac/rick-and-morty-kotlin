package com.example.rick_and_morti.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Results")
data class Results(

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("species")
    var species: String? = null,
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("gender")
    var gender: String? = null,
//    @SerializedName("origin"   ) var origin   : Origin?           = Origin(),
//    @SerializedName("location" ) var location : Location?         = Location(),
    @SerializedName("image")
    var image: String? = null,
    //   @SerializedName("episode"  ) var episode  : ArrayList<String> = arrayListOf(),
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("created")
    var created: String? = null

)