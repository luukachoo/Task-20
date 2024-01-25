package com.example.task20.presentation.screen.user

import android.graphics.Color
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.task20.databinding.FragmentUserBinding
import com.example.task20.presentation.base.BaseFragment
import com.example.task20.presentation.event.MainFragmentEvent
import com.example.task20.presentation.extension.hideKeyboard
import com.example.task20.presentation.extension.restoreTextColor
import com.example.task20.presentation.extension.showSnackbar
import com.example.task20.presentation.helper.Listener
import com.example.task20.presentation.helper.Observer
import com.example.task20.presentation.model.User
import com.example.task20.presentation.state.MainState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate), Listener,
    Observer {

    private val viewModel: UserFragmentViewModel by viewModels()

    override fun init() {
        listeners()
        observers()
    }

    override fun listeners() {
        binding.buttonAdd.setOnClickListener {
            viewModel.onEvent(MainFragmentEvent.AddUser(getUser()))
            it.hideKeyboard()
        }

        binding.buttonRemove.setOnClickListener {
            viewModel.onEvent(MainFragmentEvent.DeleteUser(getUser()))
            it.hideKeyboard()
        }

        binding.buttonUpdate.setOnClickListener {
            viewModel.onEvent(MainFragmentEvent.UpdateUser(getUser()))
            it.hideKeyboard()
        }
    }

    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mainState.collect { state ->
                    handleMainState(state)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.usersCount.collect {
                    binding.tvCounter.text = it.toString()
                }
            }
        }
    }

    private fun handleMainState(state: MainState) = viewLifecycleOwner.lifecycleScope.launch {
        with(binding) {
            state.addUserMessage?.let {
                root.showSnackbar(state.addUserMessage)
                tvSuccess.setTextColor(Color.GREEN)
                tvSuccess.restoreTextColor(Color.WHITE)
            }

            state.deleteUserMessage?.let {
                root.showSnackbar(state.deleteUserMessage)
                tvSuccess.setTextColor(Color.GREEN)
                tvSuccess.restoreTextColor(Color.WHITE)
            }

            state.updateUserMessage?.let {
                root.showSnackbar(state.updateUserMessage)
                tvSuccess.setTextColor(Color.GREEN)
                tvSuccess.restoreTextColor(Color.WHITE)
            }

            state.fieldsErrorMessage?.let {
                root.showSnackbar(state.fieldsErrorMessage)
                tvError.setTextColor(Color.RED)
                tvError.restoreTextColor(Color.WHITE)
            }

            state.errorMessage?.let {
                root.showSnackbar(state.errorMessage)
                tvError.setTextColor(Color.RED)
                tvError.restoreTextColor(Color.WHITE)
            }
        }

    }

    private fun getUser() = with(binding) {
        User(
            firstName = etFirstName.text.toString(),
            lastName = etLastName.text.toString(),
            age = etAge.text.toString(),
            email = etEmail.text.toString()
        )
    }
}