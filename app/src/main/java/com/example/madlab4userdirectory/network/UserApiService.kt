package com.example.madlab4userdirectory.network

import com.example.madlab4userdirectory.model.User
import retrofit2.http.GET

interface UserApiService {
    @GET("api/?results=20")
    suspend fun getUsers(): List<User>
}