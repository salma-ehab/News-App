package com.example.newsappinkotlin.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class SourceModel (
    @SerializedName("id") var id:String,
    @SerializedName("name") var name:String
)
