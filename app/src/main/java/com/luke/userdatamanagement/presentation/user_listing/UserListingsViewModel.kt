package com.luke.userdatamanagement.presentation.user_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luke.userdatamanagement.domain.repository.UserRepository
import com.luke.userdatamanagement.ui.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListingsViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {

    var state by mutableStateOf(UserListingState())
    private var searchJob: Job? = null

    init {
        getUserListings()
    }

    fun onEvent(event: UserListingEvent) {
        when(event) {
            is UserListingEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500)
                    getUserListings()
                }
            }
            UserListingEvent.Refresh -> {
                getUserListings(fetchFromRemote = true)
            }
        }
    }

    private fun getUserListings(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository.getUserListings(fetchFromRemote, query)
                .collect { result ->
                    when(result) {
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    users = listings
                                )
                            }
                        }
                    }
                }
        }
    }
}