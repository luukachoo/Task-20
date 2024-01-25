package com.example.task20.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.task20.data.local.dao.UserDao
import com.example.task20.data.local.model.UserEntity

@Database(entities = [UserEntity::class], version = 3)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}