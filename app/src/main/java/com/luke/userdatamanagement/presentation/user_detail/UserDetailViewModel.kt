package com.luke.userdatamanagement.presentation.user_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luke.userdatamanagement.domain.repository.UserRepository
import com.luke.userdatamanagement.ui.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: UserRepository
): ViewModel() {

    var state by mutableStateOf(UserDetailState())

    init {
        viewModelScope.launch {
            val uuid = savedStateHandle.get<String>("uuid") ?: return@launch
            state = state.copy(isLoading = true)
            when(val user = repository.getUserById(uuid)) {
                is Resource.Error -> {
                    state = state.copy(
                        user = null,
                        isLoading = false,
                        error = user.message
                    )
                }
                is Resource.Success -> {
                    state = state.copy(
                        user = user.data,
                        isLoading = false,
                        error = null
                    )
                }
                else -> Unit
            }
        }
    }


}