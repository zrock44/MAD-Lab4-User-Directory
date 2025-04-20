package com.example.madlab4userdirectory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.compose.MADLab4UserDirectoryTheme
import com.example.madlab4userdirectory.ui.UserDirectoryApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MADLab4UserDirectoryTheme {
                UserDirectoryApp()
            }
        }
    }
}