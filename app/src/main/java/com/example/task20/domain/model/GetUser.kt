package com.example.task20.domain.model

data class GetUser(
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val email: String
)
