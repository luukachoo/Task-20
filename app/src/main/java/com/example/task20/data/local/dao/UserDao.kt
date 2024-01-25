package com.example.task20.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.task20.data.local.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(userEntity: UserEntity)

    @Delete
    suspend fun removeUser(userEntity: UserEntity)

    @Update
    suspend fun updateUser(userEntity: UserEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE email = :email)")
    fun doesUserExist(email: String): Boolean

    @Query("SELECT COUNT(*) FROM users")
    fun getUserCount(): Flow<Int>

    @Query("SELECT id FROM users WHERE email = :email")
    fun getUserIdByEmail(email: String): Long
}