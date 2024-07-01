package com.example.loveapp.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.loveapp.model.LoveResult

@Dao
interface LoveDao {
    @Query("SELECT * FROM loveResult ORDER BY firstName ASC")
    fun getAll(): LiveData<List<LoveResult>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(loveResult: LoveResult)

    @Delete
    fun delete(loveResult: LoveResult)

    @Query("SELECT * FROM loveResult WHERE id = :id")
    fun get(id: Int): LoveResult

    @Update
    fun update(loveResult: LoveResult)
}