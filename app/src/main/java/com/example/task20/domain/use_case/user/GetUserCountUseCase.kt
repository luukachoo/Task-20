package com.example.task20.domain.use_case.user

import com.example.task20.domain.repository.UserRepository
import javax.inject.Inject

class GetUserCountUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke() =
        userRepository.getUerCount()
}