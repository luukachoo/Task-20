package com.example.task20.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.task20.data.local.model.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addUser(userEntity: UserEntity)
    @Delete
    suspend fun removeUser(userEntity: UserEntity)
    @Update
    suspend fun updateUser(userEntity: UserEntity): UserEntity
}