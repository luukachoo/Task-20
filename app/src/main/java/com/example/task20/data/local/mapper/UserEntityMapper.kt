package com.example.task20.data.local.mapper

import com.example.task20.data.local.model.UserEntity
import com.example.task20.domain.model.GetUser

fun UserEntity.toDomain() = GetUser(
    firstName = firstName,
    lastName = lastName,
    age = age,
    email = email
)


fun GetUser.toData() = UserEntity(
    firstName = firstName,
    lastName = lastName,
    age = age,
    email = email
)