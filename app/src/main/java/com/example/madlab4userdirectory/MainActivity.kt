package com.example.madlab4userdirectory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.madlab4userdirectory.model.User
import com.example.madlab4userdirectory.ui.theme.MADLab4UserDirectoryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MADLab4UserDirectoryTheme {
                DirectoryApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DirectoryApp(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("User Directory")
                }
            )
        }
    ) { innerPadding ->
        DirectoryContent(modifier = modifier.padding(innerPadding))
    }
}

@Composable
fun DirectoryContent(modifier: Modifier = Modifier) {
    UserList(modifier = modifier)
}

@Composable
fun UserList(modifier: Modifier = Modifier) {
    UserCard(User(), modifier = modifier)
}

@Composable
fun UserCard(user: User, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(8.dp)) {
            Text(user.image, Modifier.align(Alignment.CenterVertically))
            Spacer(Modifier.size(30.dp))
            Column() {
                Text(
                    text = "${user.title}. ${user.first} ${user.last}",
                    fontWeight = FontWeight.Bold
                )
                Text(user.email)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DirectoryPreview() {
    MADLab4UserDirectoryTheme {
        DirectoryApp()
    }
}