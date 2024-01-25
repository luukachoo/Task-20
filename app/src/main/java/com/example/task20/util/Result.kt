package com.example.task20.util


sealed class Result {
    data class Success(val uiMessage: String) : Result()
    data class Failure(val errorMessage: String) : Result()

}