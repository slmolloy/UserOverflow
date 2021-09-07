package com.scottmolloy.useroverflow

import androidx.lifecycle.MutableLiveData

class UsersDataSource() {
    fun getUsers(liveData: MutableLiveData<List<User>>) = UsersApi.getUsers(liveData)
}