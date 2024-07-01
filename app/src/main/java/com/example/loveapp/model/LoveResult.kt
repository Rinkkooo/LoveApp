package com.example.loveapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "loveResult")
class LoveResult (
    @SerializedName("fname")
    val firstName: String,
    @SerializedName("sname")
    val secondName: String,
    val percentage: String,
    val result: String
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}