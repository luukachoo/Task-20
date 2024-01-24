package com.example.task20.domain.use_case.validator

import javax.inject.Inject

class LastNameValidatorUseCase @Inject constructor() {
    operator fun invoke(lastName: String): Boolean = lastName.isNotBlank()
}