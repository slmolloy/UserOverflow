package com.scottmolloy.useroverflow.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scottmolloy.useroverflow.data.User
import com.scottmolloy.useroverflow.data.UsersDataSource

/**
 * ViewModel to contain user list and view state for Views.
 */
class UserViewModel : ViewModel() {
    private val mutableUsers = MutableLiveData<List<User>>(listOf()).also {
        UsersDataSource.getUsers(it)
    }
    val users: LiveData<List<User>> = mutableUsers

    private val mutableViewState = MutableLiveData<UserViewState>(UserViewState.ListView)
    val viewState: LiveData<UserViewState> = mutableViewState

    /**
     * Call showDetails when user clicks on a user
     */
    fun showDetails(user: User) {
        mutableViewState.postValue(UserViewState.DetailsView(user))
    }

    /**
     * Call showList when user is navigating away from a user details
     */
    fun showList() {
        mutableViewState.postValue(UserViewState.ListView)
    }
}

sealed class UserViewState {
    object ListView : UserViewState()
    class DetailsView(val user: User) : UserViewState()
}