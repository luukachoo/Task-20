package com.example.task20.domain.use_case.validator

import javax.inject.Inject

data class FieldValidatorUseCase @Inject constructor(
    val firstNameValidator: FirstNameValidatorUseCase,
    val lastNameValidator: LastNameValidatorUseCase,
    val ageValidator: AgeValidatorUseCase,
    val emailValidator: EmailValidatorUseCase
)
