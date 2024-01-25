package com.example.task20.data.local.repository

import com.example.task20.data.local.dao.UserDao
import com.example.task20.data.local.mapper.toData
import com.example.task20.domain.model.GetUser
import com.example.task20.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {

    override suspend fun addUser(userEntity: GetUser) {
        return userDao.addUser(userEntity.toData())
    }

    override suspend fun removeUser(userEntity: GetUser) {
        return userDao.removeUser(userEntity.toData())
    }

    override suspend fun updateUser(userEntity: GetUser) {
        return userDao.updateUser(userEntity.toData())
    }

    override fun doesUserExists(email: String): Boolean {
        return userDao.doesUserExist(email)
    }

    override fun getUerCount(): Flow<Int> {
        return userDao.getUserCount()
    }

    override fun getUserIdByEmail(email: String): Long {
        return userDao.getUserIdByEmail(email)
    }

}
