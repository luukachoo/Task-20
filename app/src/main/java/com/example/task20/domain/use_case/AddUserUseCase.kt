package com.example.task20.domain.use_case

import com.example.task20.domain.model.GetUser
import com.example.task20.domain.repository.UserRepository
import javax.inject.Inject

class AddUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(user: GetUser) {
        repository.addUser(user)
    }
}