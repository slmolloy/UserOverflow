package com.scottmolloy.useroverflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UsersViewModel : ViewModel() {
    private val mutableUsers = MutableLiveData<List<User>>(listOf())
    val users: LiveData<List<User>> = mutableUsers

    fun refresh() {
        UsersDataSource.getUsers(mutableUsers)
    }

    private val mutableViewState = MutableLiveData<UserViewState>(UserViewState.ListView)
    val viewState: LiveData<UserViewState> = mutableViewState
    fun showDetails(user: User) {
        mutableViewState.postValue(UserViewState.DetailsView(user))
    }
    fun showList() {
        mutableViewState.postValue(UserViewState.ListView)
    }
}

sealed class UserViewState {
    object ListView : UserViewState()
    class DetailsView(val user: User) : UserViewState()
}