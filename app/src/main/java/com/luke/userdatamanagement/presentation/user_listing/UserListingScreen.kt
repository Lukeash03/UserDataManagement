package com.luke.userdatamanagement.presentation.user_listing

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.luke.userdatamanagement.presentation.destinations.UserDetailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun UserListingScreen(
    navigator: DestinationsNavigator,
    viewModel: UserListingsViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.state.isRefreshing)
    val state = viewModel.state

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                viewModel.onEvent(UserListingEvent.OnSearchQueryChange(it))
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true
        )

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(UserListingEvent.Refresh)
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
//                item {
//                    val staticUser = state.users[1]
//                    UserItem(
//                        user = staticUser,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clickable {
//                                Log.i("UserListingScreen", "Navigating to ${staticUser.uuid}")
//                                navigator.navigate(
//                                    UserDetailScreenDestination(staticUser.uuid)
//                                )
//                            }
//                    )
//                }
                items(state.users.size) { i ->
                    val user = state.users[i]
                    Log.i("UserListingScreen", "User data: ${user.name}, $i")
                    UserItem(user = user,
                        modifier = Modifier
                            .fillMaxWidth()
//                            .padding(16.dp)
                            .clickable {
                                Log.i("UserListingScreen", "Navigating to ${user.uuid}")
                                navigator.navigate(
                                    UserDetailScreenDestination(user.uuid)
                                )
                            }
                    )
                    if (i < state.users.size) {
                        Divider(modifier = Modifier.padding(horizontal = 16.dp))
                    }
                }
            }
        }
    }
}