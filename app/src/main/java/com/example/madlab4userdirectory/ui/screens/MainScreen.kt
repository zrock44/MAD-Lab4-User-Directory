package com.example.madlab4userdirectory.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            modifier = modifier.fillMaxSize()
        )
        is UserUiState.Success -> UserListScreen(
            users = userUiState.users,
            modifier = modifier.fillMaxWidth()
        )
        is UserUiState.Error -> ErrorScreen(
            retryMethod = retryMethod,
            modifier = modifier.fillMaxSize()
        )
    }

}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    val rotation = remember { Animatable(initialValue = -45f) }

    LaunchedEffect(Unit) {
        rotation.animateTo(
            targetValue = 315f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000, easing = EaseInOut),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(110.dp)
                .rotate(rotation.value),
            painter = painterResource(R.drawable.loading_icon),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            contentDescription = stringResource(R.string.loading_icon_description)
        )
        Spacer(modifier = Modifier.size(30.dp))
        Text(
            color = MaterialTheme.colorScheme.onBackground,
            style = TextStyle(fontSize = 25.sp),
            text = stringResource(R.string.loading_message),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ErrorScreen(
    retryMethod: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(120.dp),
            painter = painterResource(R.drawable.connection_error),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            contentDescription = stringResource(R.string.error_icon_description)
        )
        Spacer(modifier = Modifier.size(15.dp))
        Text(
            color = MaterialTheme.colorScheme.onBackground,
            style = TextStyle(fontSize = 25.sp),
            text = stringResource(R.string.error_message),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(40.dp))
        Button(onClick = retryMethod) {
            Text(text = stringResource(R.string.retry_button_text))
        }
    }
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
    // This is done so that users with a title of "Miss" don't have a period
    // placed after their title.
    val period = if (user.name.title.length > 3) "" else "."

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(
                width = 3.dp,
                color = MaterialTheme.colorScheme.primary,
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
                    text = "${user.name.title}$period ${user.name.first} ${user.name.last}",
                    fontWeight = FontWeight.Bold
                )
                Text(user.email)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenLightPreview() {
    MADLab4UserDirectoryTheme(false) {
        LoadingScreen(modifier = Modifier.fillMaxSize())
    }
}

@Preview(showBackground = false)
@Composable
fun LoadingScreenDarkPreview() {
    MADLab4UserDirectoryTheme(true) {
        LoadingScreen(modifier = Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenLightPreview() {
    MADLab4UserDirectoryTheme(false) {
        ErrorScreen(
            retryMethod = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = false)
@Composable
fun ErrorScreenDarkPreview() {
    MADLab4UserDirectoryTheme(true) {
        ErrorScreen(
            retryMethod = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

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