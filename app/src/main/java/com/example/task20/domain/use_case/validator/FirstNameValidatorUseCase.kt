package com.example.task20.domain.use_case.validator

import javax.inject.Inject

class FirstNameValidatorUseCase @Inject constructor() {
    operator fun invoke(firstName: String): Boolean = firstName.isNotBlank()
}