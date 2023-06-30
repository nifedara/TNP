package com.example.tradeline.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradeline.data.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginScreenViewModel(private val usersRepository: UsersRepository) : ViewModel() {

    val loginState = mutableStateOf(LoginState())

    suspend fun login(storeName: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val user = usersRepository.getUserByStoreName(storeName).first()
                if (user != null && user.password == password) {
                    handleSuccessfulLogin()
                } else {
                    handleInvalidCredentials()
                }
            }
        }
    }


    private fun handleSuccessfulLogin() {
        loginState.value = LoginState(successfulLogin = true)
    }

    private fun handleInvalidCredentials() {
        loginState.value = LoginState(invalidCredentials = true)
    }
}

data class LoginState(
    val successfulLogin: Boolean = false,
    val invalidCredentials: Boolean = false
)