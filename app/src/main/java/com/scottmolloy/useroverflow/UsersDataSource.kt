package com.scottmolloy.useroverflow

import androidx.lifecycle.MutableLiveData

object UsersDataSource {
    var users: List<User>? = null

    fun getUsers(liveData: MutableLiveData<List<User>>) {
        (users ?: UsersApi.getUsers().also { users = it }).let {
            liveData.postValue(it)
        }
    }
}