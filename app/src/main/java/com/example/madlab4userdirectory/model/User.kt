package com.example.madlab4userdirectory.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val login: Login,
    @SerializedName("picture")
    val picture: Picture,
    @SerializedName("name")
    val name: Name,
    @SerializedName("email")
    val email: String = "jimmy@jumpboots.org"
)

data class Login(
    @SerializedName("uuid")
    val uuid: String = "453"
)

data class Picture(
    @SerializedName("large")
    val large: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)

data class Name(
    @SerializedName("title")
    val title: String = "Mr",
    @SerializedName("first")
    val first: String = "Jumpboots",
    @SerializedName("last")
    val last: String = "Jimmy"
)

data class Info(
    @SerializedName("results")
    val userCount: String,
    @SerializedName("version")
    val version: String
)

data class UserApiResponse(
    @SerializedName("results")
    val results: List<User>,
    @SerializedName("info")
    val info: Info
)