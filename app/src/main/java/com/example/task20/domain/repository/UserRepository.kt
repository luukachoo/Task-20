package com.example.task20.domain.repository

import com.example.task20.domain.model.GetUser

interface UserRepository {
    suspend fun addUser(userEntity: GetUser)
    suspend fun removeUser(userEntity: GetUser)
    suspend fun updateUser(userEntity: GetUser)
    fun doesUserExists(email: String): Boolean
}