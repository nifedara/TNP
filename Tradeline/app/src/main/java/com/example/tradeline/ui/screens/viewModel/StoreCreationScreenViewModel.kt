package com.example.tradeline.ui.screens.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradeline.ui.data.User
import com.example.tradeline.ui.data.UsersRepository
import kotlinx.coroutines.launch


class StoreCreationScreenViewModel(private val usersRepository: UsersRepository) : ViewModel() {

    var storeUiState by mutableStateOf(StoreUiState())
        private set

    fun updateUiState(userDetails: UserFullDetails) {
        storeUiState =
            StoreUiState(userDetails = userDetails, isEntryValid = validateInput(userDetails))
    }

    private fun validateInput(uiState: UserFullDetails = storeUiState.userDetails): Boolean {
        return with(uiState) {
            storeName.isNotBlank() && email.isNotBlank() && phoneNumber.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()
                    && password == confirmPassword && phoneNumber.length == 11
        }
    }

    suspend fun saveUser() {
        viewModelScope.launch {
            if (validateInput()) {
                usersRepository.insertUser(storeUiState.userDetails.toUser())
            }
        }
    }
}

data class StoreUiState(
    val userDetails: UserFullDetails = UserFullDetails(),
    var isEntryValid: Boolean = false
)

data class UserFullDetails(
    var storeName: String = "",
    var email: String = "",
    var phoneNumber: String = "",
    var password: String = "",
    var confirmPassword: String = ""
) {
    fun toUser(): User = User(
        storeName = storeName,
        email = email,
        phoneNumber = phoneNumber,
        password = password
    )
}
