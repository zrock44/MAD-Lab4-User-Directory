package com.example.madlab4userdirectory.data

import com.example.madlab4userdirectory.model.User
import com.example.madlab4userdirectory.network.UserApiService

interface UserRepository {
    suspend fun getRandomUsers(): List<User>
}

class NetworkUserRepository(private val userApiService: UserApiService) : UserRepository {
    override suspend fun getRandomUsers(): List<User> = userApiService.getUsers()
}