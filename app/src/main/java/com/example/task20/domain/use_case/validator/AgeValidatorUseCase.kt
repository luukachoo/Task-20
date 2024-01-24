package com.example.task20.domain.use_case.validator

import javax.inject.Inject

class AgeValidatorUseCase @Inject constructor() {
    operator fun invoke(age: String): Boolean = age.isNotBlank()
}