package com.example.task20.presentation.screen

import android.graphics.Color
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.task20.R
import com.example.task20.databinding.FragmentMainBinding
import com.example.task20.presentation.base.BaseFragment
import com.example.task20.presentation.event.MainFragmentEvent
import com.example.task20.presentation.extension.restoreTextColor
import com.example.task20.presentation.extension.showSnackbar
import com.example.task20.presentation.helper.Listener
import com.example.task20.presentation.helper.Observer
import com.example.task20.presentation.model.User
import com.example.task20.presentation.state.UserState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate), Listener,
    Observer {

    private val viewModel: MainFragmentViewModel by viewModels()
    private var usersAddedCount = 0

    override fun init() {
        listeners()
        observers()
    }

    override fun listeners() {
        binding.buttonAdd.setOnClickListener {
            viewModel.onEvent(MainFragmentEvent.AddUser(getUser()))
        }

        binding.buttonRemove.setOnClickListener {
            viewModel.onEvent(MainFragmentEvent.DeleteUser(getUser()))
        }

        binding.buttonUpdate.setOnClickListener {
            viewModel.onEvent(MainFragmentEvent.UpdateUser(getUser()))
        }
    }

    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userState.collect { state ->
                    with(binding) {
                        when (state) {
                            is UserState.UserAdded -> {
                                root.showSnackbar(getString(R.string.user_added))
                                usersAddedCount++
                                tvCounter.text = "$usersAddedCount"
                                tvSuccess.setTextColor(Color.GREEN)
                                tvSuccess.restoreTextColor(Color.WHITE)
                            }

                            is UserState.Idle -> progressBar.isVisible = false

                            is UserState.UpdateUser -> {
                                root.showSnackbar(getString(R.string.user_updated))
                                tvSuccess.setTextColor(Color.GREEN)
                                tvSuccess.restoreTextColor(Color.WHITE)
                            }

                            is UserState.UserAlreadyExists -> {
                                root.showSnackbar(getString(R.string.user_exists))
                                tvError.setTextColor(Color.RED)
                                tvError.restoreTextColor(Color.WHITE)
                            }

                            is UserState.UserDeleted -> {
                                root.showSnackbar(getString(R.string.user_deleted))
                                usersAddedCount--
                                tvCounter.text = "$usersAddedCount"
                                tvSuccess.setTextColor(Color.GREEN)
                                tvSuccess.restoreTextColor(Color.WHITE)
                            }

                            is UserState.UserNotFound -> {
                                root.showSnackbar(getString(R.string.user_does_not_exist))
                                tvError.setTextColor(Color.RED)
                                tvError.restoreTextColor(Color.WHITE)
                            }

                            is UserState.ValidationError -> {
                                val errorMessages = state.errorMessages
                                root.showSnackbar(errorMessages)
                            }
                        }
                    }

                }
            }
        }
    }

    private fun getUser() = with(binding) {
        User(
            firstName = etFirstName.text.toString(),
            lastName = etLastName.text.toString(),
            age = etAge.text.toString().toInt(),
            email = etEmail.text.toString()
        )
    }
}