package com.luke.userdatamanagement.presentation.user_listing

import com.luke.userdatamanagement.domain.models.User

data class UserListingState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
