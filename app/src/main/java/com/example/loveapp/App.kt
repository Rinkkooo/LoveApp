package com.example.loveapp

import android.app.Application
import androidx.room.Room
import com.example.loveapp.model.LoveApiService
import com.example.loveapp.model.db.LoveDatabase
import com.example.loveapp.model.utils.PreferenceHelper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    companion object{
        var loveDatabase: LoveDatabase? = null
    }

    var api: LoveApiService = Retrofit.Builder()
        .baseUrl("https://love-calculator.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(LoveApiService::class.java)

    override fun onCreate() {
        super.onCreate()
        val preferenceHelper = PreferenceHelper().unit(this@App)
        getInstance()
    }

    fun getInstance(): LoveDatabase? {
        if (loveDatabase == null){
            loveDatabase = applicationContext?.let {
                Room.databaseBuilder(
                    it,
                    LoveDatabase::class.java,
                    "love.database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return loveDatabase
    }
}