package com.example.loveapp

import com.google.gson.annotations.SerializedName

class LoveResult (
    @SerializedName("fname")
    val firstName: String,
    @SerializedName("sname")
    val secondName: String,
    val percentage: String,
    val result: String
)