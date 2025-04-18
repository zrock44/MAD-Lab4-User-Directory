package com.example.madlab4userdirectory

import android.app.Application
import com.example.madlab4userdirectory.data.AppContainer
import com.example.madlab4userdirectory.data.DefaultAppContainer

class UserDirectoryApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}