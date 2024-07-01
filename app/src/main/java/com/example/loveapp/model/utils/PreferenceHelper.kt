package com.example.loveapp.model.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper {

    private var preferenceHelper: SharedPreferences? = null

    fun unit(context: Context){
        preferenceHelper = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }

    var isOnboardShown: Boolean
        get() = preferenceHelper!!.getBoolean("isOnboardShown", false)
        set(value) = preferenceHelper!!.edit().putBoolean("isOnboardShown", value).apply()

}