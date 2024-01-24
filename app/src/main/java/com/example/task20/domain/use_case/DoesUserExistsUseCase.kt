package com.example.task20.domain.use_case

import com.example.task20.domain.repository.UserRepository
import javax.inject.Inject

class DoesUserExistsUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(email: String): Boolean {
        return userRepository.doesUserExists(email)
    }
}