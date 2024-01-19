package com.example.task20.data.local.mapper

import com.example.task20.data.local.model.UserEntity
import com.example.task20.domain.model.GetUser

fun UserEntity.toDomain() = GetUser(
    id = id,
    firstName = firstName,
    lastName = lastName,
    age = age,
    email = email
)


fun GetUser.toData() = UserEntity(
    id= id,
    firstName = firstName,
    lastName = lastName,
    age = age,
    email = email
)