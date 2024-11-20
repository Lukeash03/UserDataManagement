package com.luke.userdatamanagement.presentation.user_listing

sealed class UserListingEvent {
    data object Refresh: UserListingEvent()
    data class OnSearchQueryChange(val query: String): UserListingEvent()
}