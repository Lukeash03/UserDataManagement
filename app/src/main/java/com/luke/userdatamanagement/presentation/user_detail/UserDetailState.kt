package com.luke.userdatamanagement.presentation.user_detail

import com.luke.userdatamanagement.domain.models.User

data class UserDetailState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)
