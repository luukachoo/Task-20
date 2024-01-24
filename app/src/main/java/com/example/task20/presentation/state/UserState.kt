package com.example.task20.presentation.state

sealed class UserState {
    data object Idle : UserState()
    data object UserAdded : UserState()
    data object UserAlreadyExists : UserState()
    data object UpdateUser : UserState()
    data object UserDeleted : UserState()
    data object UserNotFound : UserState()
    data class ValidationError(val errorMessages: String) : UserState()
}