package com.example.madlab4userdirectory.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.compose.MADLab4UserDirectoryTheme
import com.example.madlab4userdirectory.R
import com.example.madlab4userdirectory.model.User
import com.example.madlab4userdirectory.model.UserApiResponse
import com.example.madlab4userdirectory.model.createMockUserList
import com.example.madlab4userdirectory.ui.theme.AppShapes

@Composable
fun MainScreen(
    userUiState: UserUiState,
    retryMethod: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (userUiState) {
        is UserUiState.Loading -> LoadingScreen(
            modifier = modifier
        )
        is UserUiState.Success -> UserListScreen(
            users = userUiState.users,
            modifier = modifier
        )
        is UserUiState.Error -> ErrorScreen(
            retryMethod = retryMethod,
            modifier = modifier
        )
    }

}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {

}

@Composable
fun ErrorScreen(
    retryMethod: () -> Unit,
    modifier: Modifier = Modifier
) {

}

@Composable
fun UserListScreen(
    users: UserApiResponse,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(users.results) {
            UserCard(user = it)
        }
    }
}

@Composable
fun UserCard(user: User, modifier: Modifier = Modifier) {

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(
                width = 3.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = AppShapes.CardShape
            )
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Spacer(Modifier.size(6.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.picture.large)
                    .build(),
                placeholder = painterResource(R.drawable.placeholder),
                contentDescription = stringResource(R.string.user_picture_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.size(14.dp))
            Column() {
                Text(
                    text = "${user.name.title}. ${user.name.first} ${user.name.last}",
                    fontWeight = FontWeight.Bold
                )
                Text(user.email)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun LoadingScreenPreview() {
//    MADLab4UserDirectoryTheme {
//        LoadingScreen()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ErrorScreenPreview() {
//    MADLab4UserDirectoryTheme {
//        ErrorScreen({})
//    }
//}

@Preview(showBackground = true)
@Composable
fun UserListScreenLightPreview() {
    MADLab4UserDirectoryTheme(false) {
        val mockUserList = createMockUserList(5)
        UserListScreen(mockUserList)
    }
}

@Preview(showBackground = false)
@Composable
fun UserListScreenDarkPreview() {
    MADLab4UserDirectoryTheme(true) {
        val mockUserList = createMockUserList(5)
        UserListScreen(mockUserList)
    }
}