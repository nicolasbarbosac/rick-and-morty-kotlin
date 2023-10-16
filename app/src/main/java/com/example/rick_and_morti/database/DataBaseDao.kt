package com.example.rick_and_morti.database

import androidx.room.*
import com.example.rick_and_morti.models.Results

@Dao
interface DataBaseDao {
    @Query("SELECT * FROM Results")
    fun getResults(): List<Results>

    @Query("SELECT * FROM Results WHERE id=:id LIMIT 1")
    fun getResult(id: Int): Results

//    @Query("SELECT * FROM AutResponseEntity WHERE id like:data or rrn like:data ")
//    fun getTransaccionFilter( data:String): List<AutResponseEntity>

    @Insert
    fun addResult(autResponseEntity: Results)

    @Update
    fun updateResult(autResponseEntity: Results)

    @Delete
    fun deleteResult(autResponseEntity: Results)
}