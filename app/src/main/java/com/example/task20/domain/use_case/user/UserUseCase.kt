package com.example.task20.domain.use_case.user

import javax.inject.Inject

data class UserUseCase @Inject constructor(
    val addUser: AddUserUseCase,
    val removeUser: DeleteUserUseCase,
    val updateUser: UpdateUserUseCase,
    val getUserCount: GetUserCountUseCase,
)
