package com.example.madlab4userdirectory.data

import com.example.madlab4userdirectory.model.UserApiResponse
import com.example.madlab4userdirectory.network.UserApiService

interface UserRepository {
    suspend fun getRandomUsers(): UserApiResponse
}

class NetworkUserRepository(private val userApiService: UserApiService) : UserRepository {
    override suspend fun getRandomUsers(): UserApiResponse = userApiService.getUsers()
}