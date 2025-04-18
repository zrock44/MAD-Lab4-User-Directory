package com.example.madlab4userdirectory.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.madlab4userdirectory.UserDirectoryApplication
import com.example.madlab4userdirectory.data.UserRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface UserUiState {
    data class Success(val users: String) : UserUiState
    object Error : UserUiState
    object Loading : UserUiState
}

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    var userUiState: UserUiState by mutableStateOf(UserUiState.Loading)
        private set

    init {
        getRandomUsers()
    }

    fun getRandomUsers() {
        viewModelScope.launch {
            userUiState = UserUiState.Loading
            userUiState = try {
                val listResult = userRepository.getRandomUsers()
                UserUiState.Success(
                    "Success: ${listResult.size} users retrieved."
                )
            } catch (e: IOException) {
                UserUiState.Error
            } catch (e:HttpException) {
                UserUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as UserDirectoryApplication)
                val userRepository = application.container.userRepository
                UserViewModel(userRepository = userRepository)
            }
        }
    }
}