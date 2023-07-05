package com.example.tradeline.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradeline.data.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginScreenViewModel(private val usersRepository: UsersRepository, userId: Int) : ViewModel() {
    val loginState = mutableStateOf(LoginState())

    suspend fun login(storeName: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val user = usersRepository.getUserByStoreName(storeName).first()
                if (user != null && user.password == password) {
                    user.id?.let { handleSuccessfulLogin(it) }
                    loginState.value = loginState.value.copy(userId = user.id)
                } else {
                    handleInvalidCredentials()
                }
            }
        }
    }

    private fun handleSuccessfulLogin(userId: Int) {
        loginState.value = loginState.value.copy(
            successfulLogin = true,
            userId = userId
        )
    }

    private fun handleInvalidCredentials() {
        loginState.value = loginState.value.copy(invalidCredentials = true)
    }
}



data class LoginState(
    val successfulLogin: Boolean = false,
    val invalidCredentials: Boolean = false,
    val userId: Int? = null
)

//class LoginScreenViewModel(private val usersRepository: UsersRepository) : ViewModel() {
//
//    val loginState = mutableStateOf(LoginState())
//
//    suspend fun login(storeName: String, password: String) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                val user = usersRepository.getUserByStoreName(storeName).first()
//                if (user != null && user.password == password) {
//                    handleSuccessfulLogin()
//                } else {
//                    handleInvalidCredentials()
//                }
//            }
//        }
//    }
//
//
//    private fun handleSuccessfulLogin() {
//        loginState.value = LoginState(successfulLogin = true)
//    }
//
//    private fun handleInvalidCredentials() {
//        loginState.value = LoginState(invalidCredentials = true)
//    }
//}

