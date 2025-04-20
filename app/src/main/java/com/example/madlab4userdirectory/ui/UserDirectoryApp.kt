package com.example.madlab4userdirectory.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.madlab4userdirectory.R
import com.example.madlab4userdirectory.ui.screens.MainScreen
import com.example.madlab4userdirectory.ui.screens.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDirectoryApp(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.top_bar)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        val userViewModel: UserViewModel = viewModel(factory = UserViewModel.Factory)
        MainScreen(
            userUiState = userViewModel.userUiState,
            retryMethod = userViewModel::getRandomUsers,
            modifier = modifier.padding(innerPadding)
        )
    }
}