package com.scottmolloy.useroverflow

class UsersDataSource() {
    fun getUsers(): List<User> = UserList.sampleUserList()
}