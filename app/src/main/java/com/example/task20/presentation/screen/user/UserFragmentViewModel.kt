package com.example.task20.presentation.screen.user


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task20.domain.use_case.user.UserUseCase
import com.example.task20.domain.use_case.validator.FieldValidatorUseCase
import com.example.task20.presentation.event.MainFragmentEvent
import com.example.task20.presentation.mapper.toDomain
import com.example.task20.presentation.model.User
import com.example.task20.presentation.state.MainState
import com.example.task20.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFragmentViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val fieldValidator: FieldValidatorUseCase
) : ViewModel() {

    private val _mainState = MutableSharedFlow<MainState>()
    val mainState get() = _mainState

    val usersCount = userUseCase.getUserCount()

    fun onEvent(event: MainFragmentEvent) {
        when (event) {
            is MainFragmentEvent.AddUser -> {
                validateForms(
                    event.user.firstName,
                    event.user.lastName,
                    event.user.age,
                    event.user.email
                ) {
                    addUser(event.user)
                }
            }

            is MainFragmentEvent.DeleteUser -> {
                validateForms(
                    event.user.firstName,
                    event.user.lastName,
                    event.user.age,
                    event.user.email
                ) {
                    deleteUser(event.user)
                }
            }

            is MainFragmentEvent.UpdateUser -> {
                validateForms(
                    event.user.firstName,
                    event.user.lastName,
                    event.user.age,
                    event.user.email
                ) {
                    updateUser(event.user)
                }
            }
        }
    }

    private fun addUser(user: User) = viewModelScope.launch {
        when (val result = userUseCase.addUser(user.toDomain(), user.email)) {
            is Result.Success -> {
                _mainState.emit(MainState(addUserMessage = result.uiMessage))
            }

            is Result.Failure -> {
                _mainState.emit(MainState(errorMessage = result.errorMessage))
            }
        }
    }

    private fun deleteUser(user: User) = viewModelScope.launch {
        when (val result = userUseCase.removeUser(user.toDomain(), user.email)) {
            is Result.Success -> {
                _mainState.emit(MainState(deleteUserMessage = result.uiMessage))
            }

            is Result.Failure -> {
                _mainState.emit(MainState(errorMessage = result.errorMessage))
            }
        }
    }

    private fun updateUser(user: User) = viewModelScope.launch {
        when (val result = userUseCase.updateUser(user.toDomain(), user.email)) {
            is Result.Success -> {
                _mainState.emit(MainState(updateUserMessage = result.uiMessage))
            }

            is Result.Failure -> {
                _mainState.emit(MainState(errorMessage = result.errorMessage))
            }
        }
    }

    private fun validateForms(
        firstName: String,
        lastName: String,
        age: String,
        email: String,
        onValidationResult: (Boolean) -> Unit
    ) = viewModelScope.launch {
        val isFirstnameValid = fieldValidator.firstNameValidator(firstName)
        val isLastNameValid = fieldValidator.lastNameValidator(lastName)
        val isAgeValid = fieldValidator.ageValidator(age)
        val isEmailValid = fieldValidator.emailValidator(email)

        val areFieldsValid =
            listOf(isFirstnameValid, isLastNameValid, isAgeValid, isEmailValid).all { it }

        if (!areFieldsValid) {
            updateErrorMessage(message = "Fields are not valid")
            return@launch
        }
        onValidationResult(areFieldsValid)
    }

    private suspend fun updateErrorMessage(message: String) {
        _mainState.emit(MainState(fieldsErrorMessage = message))
    }
}