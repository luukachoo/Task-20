package com.example.task20.presentation.event

import com.example.task20.presentation.model.User

sealed class MainFragmentEvent {
    data class AddUser(val user: User) : MainFragmentEvent()
    data class UpdateUser(val user: User) : MainFragmentEvent()
    data class DeleteUser(val user: User) : MainFragmentEvent()
}