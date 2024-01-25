package com.example.task20.presentation.state

data class MainState(
    val addUserMessage: String? = null,
    val updateUserMessage: String? = null,
    val deleteUserMessage: String? = null,
    val errorMessage: String? = null,
    val fieldsErrorMessage: String? = null
)