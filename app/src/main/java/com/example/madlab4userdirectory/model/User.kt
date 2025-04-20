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
    val email: String
)

data class Login(
    @SerializedName("uuid")
    val uuid: String
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
    val title: String,
    @SerializedName("first")
    val first: String,
    @SerializedName("last")
    val last: String
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

fun createMockUserList(
    listSize: Int,
    title: String = "Mr",
    firstName: String = "Jumpboots",
    lastName: String = "Jimmy",
    uuid: String = "104",
    email: String = "jimmy@jumpboots.org",
    version: String = "1.04"
): UserApiResponse {
    val mockName = Name(
        title = title,
        first = firstName,
        last = lastName
    )
    val mockPicture = Picture(
        large = "",
        medium = "",
        thumbnail = ""
    )
    val mockLogin = Login(
        uuid = uuid
    )
    val mockUser = User(
        login = mockLogin,
        picture = mockPicture,
        name = mockName,
        email = email
    )
    val mockInfo = Info(
        userCount = listSize.toString(),
        version = version
    )

    val mockUsers = UserApiResponse(
        results = List(listSize) { mockUser },
        info = mockInfo
    )
    return mockUsers
}