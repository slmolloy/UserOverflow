package com.scottmolloy.useroverflow.data

import androidx.lifecycle.MutableLiveData

/**
 * This data source can be updated to handle the integration of persistent storage and api requests.
 * Currently using in memory caching of users.
 */
object UsersDataSource {
    // In memory storage of user list
    var users: List<User>? = null

    fun getUsers(liveData: MutableLiveData<List<User>>) {
        (users ?: UsersApi.getUsers().also { users = it }).let {
            liveData.postValue(it)
        }
    }

    /**
     * This can be used to load test data in the app. Update UserViewModel to use this instead of
     * getUsers
     */
    fun getUsersTest(liveData: MutableLiveData<List<User>>) {
        liveData.postValue(UserTestData.sampleUserList())
    }
}