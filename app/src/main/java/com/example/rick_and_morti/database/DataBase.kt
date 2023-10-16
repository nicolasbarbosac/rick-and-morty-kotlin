package com.example.rick_and_morti.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rick_and_morti.models.Results

@Database(entities = arrayOf(Results::class), version = 1)
abstract class DataBase: RoomDatabase() {
    abstract fun dataBaseDao(): DataBaseDao
}