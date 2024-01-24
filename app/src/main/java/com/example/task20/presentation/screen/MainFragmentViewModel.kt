package com.example.task20.presentation.screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task20.domain.use_case.UserUseCase
import com.example.task20.domain.use_case.validator.FieldValidatorUseCase
import com.example.task20.presentation.event.MainFragmentEvent
import com.example.task20.presentation.mapper.toDomain
import com.example.task20.presentation.model.User
import com.example.task20.presentation.state.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val fieldValidator: FieldValidatorUseCase
) : ViewModel() {

    private val _userState = MutableStateFlow<UserState>(UserState.Idle)
    val userState get() = _userState

    fun onEvent(event: MainFragmentEvent) {
        when (event) {
            is MainFragmentEvent.AddUser -> {
                validateForms(
                    event.user.firstName,
                    event.user.lastName,
                    event.user.age.toString(),
                    event.user.email
                )
                val userExists = userUseCase.doesUserExists(event.user.email)

                if (!userExists) {
                    addUser(event.user)
                    _userState.value = UserState.UserAdded
                } else {
                    _userState.value = UserState.UserAlreadyExists
                }
            }

            is MainFragmentEvent.DeleteUser -> {
                validateForms(
                    event.user.firstName,
                    event.user.lastName,
                    event.user.age.toString(),
                    event.user.email
                )
                val userExists = userUseCase.doesUserExists(event.user.email)
                if (userExists) {
                    deleteUser(event.user)
                    _userState.value = UserState.UserDeleted
                } else {
                    _userState.value = UserState.UserNotFound
                }
            }

            is MainFragmentEvent.UpdateUser -> {
                validateForms(
                    event.user.firstName,
                    event.user.lastName,
                    event.user.age.toString(),
                    event.user.email
                )

                val userExists = userUseCase.doesUserExists(event.user.email)
                if (userExists) {
                    updateUser(event.user)
                    _userState.value = UserState.UpdateUser
                } else {
                    _userState.value = UserState.UserNotFound
                }
            }

            is MainFragmentEvent.CheckUserExists -> checkUserExists(event.email)
        }
    }

    private fun addUser(user: User) = viewModelScope.launch {
        userUseCase.addUser(user.toDomain())
    }

    private fun deleteUser(user: User) = viewModelScope.launch {
        userUseCase.removeUser(user.toDomain())
    }

    private fun updateUser(user: User) = viewModelScope.launch {
        userUseCase.updateUser(user.toDomain())
    }

    private fun checkUserExists(email: String) = viewModelScope.launch {
        userUseCase.doesUserExists(email)
    }

    private fun validateForms(firstName: String, lastName: String, age: String, email: String) {
        val isFirstnameValid = fieldValidator.firstNameValidator(firstName)
        val isLastNameValid = fieldValidator.lastNameValidator(lastName)
        val isAgeValid = fieldValidator.ageValidator(age)
        val isEmailValid = fieldValidator.emailValidator(email)

        val areFieldsValid =
            listOf(isFirstnameValid, isLastNameValid, isAgeValid, isEmailValid).all { it }

        if (!areFieldsValid) {
            val errorMessages = mutableListOf<String>()
            if (!isFirstnameValid) errorMessages.add("Invalid first name")
            if (!isLastNameValid) errorMessages.add("Invalid last name")
            if (!isAgeValid) errorMessages.add("Invalid age")
            if (!isEmailValid) errorMessages.add("Invalid email")

            _userState.value = UserState.ValidationError(errorMessages.joinToString(", "))
            return
        }
    }
}