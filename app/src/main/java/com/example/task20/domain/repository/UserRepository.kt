package com.example.task20.domain.repository

import com.example.task20.domain.model.GetUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun addUser(userEntity: GetUser)
    suspend fun removeUser(userEntity: GetUser)
    suspend fun updateUser(userEntity: GetUser)
    fun doesUserExists(email: String): Boolean
    fun getUerCount(): Flow<Int>
    fun getUserIdByEmail(email: String): Long
}