package com.example.loveapp.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.loveapp.model.LoveResult
import com.example.loveapp.model.db.dao.LoveDao


@Database(entities = [LoveResult::class], version = 3)
abstract class LoveDatabase : RoomDatabase() {
    abstract fun loveDao(): LoveDao
}