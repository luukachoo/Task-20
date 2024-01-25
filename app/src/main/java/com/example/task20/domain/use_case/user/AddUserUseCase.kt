package com.example.task20.domain.use_case.user

import com.example.task20.domain.model.GetUser
import com.example.task20.domain.repository.UserRepository
import com.example.task20.util.Result
import javax.inject.Inject

class AddUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(user: GetUser, email: String): Result {
        val userExists = repository.doesUserExists(email)
        return if (!userExists) {
            repository.addUser(user)
            Result.Success("User added successfully!")
        } else {
            Result.Failure("User already exists")
        }
    }
}