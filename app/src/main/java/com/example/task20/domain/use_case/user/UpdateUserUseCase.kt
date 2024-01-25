package com.example.task20.domain.use_case.user

import com.example.task20.domain.model.GetUser
import com.example.task20.domain.repository.UserRepository
import com.example.task20.util.Result
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(user: GetUser, email: String): Result {
        val userExists = repository.doesUserExists(email)
        return if (userExists) {
            val userId = repository.getUserIdByEmail(user.email)
            repository.updateUser(user.copy(id = userId))
            Result.Success("User updated successfully!")
        } else {
            Result.Failure("User does not exists!")
        }
    }
}