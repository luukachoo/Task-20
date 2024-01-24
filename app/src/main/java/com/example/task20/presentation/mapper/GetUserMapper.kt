package com.example.task20.presentation.mapper

import com.example.task20.domain.model.GetUser
import com.example.task20.presentation.model.User

fun GetUser.toPresentation() = User(
    firstName = firstName,
    lastName = lastName,
    age = age,
    email = email
)

fun User.toDomain() = GetUser(
    firstName = firstName,
    lastName = lastName,
    age = age,
    email = email
)