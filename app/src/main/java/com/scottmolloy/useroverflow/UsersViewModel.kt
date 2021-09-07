package com.scottmolloy.useroverflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UsersViewModel : ViewModel() {
    private val mutableUsers = MutableLiveData<List<User>>(listOf())
    val users: LiveData<List<User>> = mutableUsers

    fun refresh() {
        UsersDataSource().getUsers(mutableUsers)
    }
}