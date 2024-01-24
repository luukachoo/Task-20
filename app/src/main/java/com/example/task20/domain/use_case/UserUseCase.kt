package com.example.task20.domain.use_case

import javax.inject.Inject

data class UserUseCase @Inject constructor(
    val addUser: AddUserUseCase,
    val removeUser: RemoveUserUseCase,
    val updateUser: UpdateUserUseCase,
    val doesUserExists: DoesUserExistsUseCase
)
