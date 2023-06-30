package com.example.tradeline.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tradeline.data.User
import com.example.tradeline.data.UsersRepository

/**
 * View Model to validate and insert items in the Room database.
 */
class StoreCreationScreenViewModel(private val usersRepository: UsersRepository) : ViewModel() {


    /**
     * Holds current item ui state
     */
    var newUserUiState by mutableStateOf(NewUserUiState())
        private set

    /**
     * Updates the [newUserUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(userDetails: UserDetails) {
        newUserUiState =
            NewUserUiState(userDetails = userDetails, isEntryValid = validateInput(userDetails))
    }

    /**
     * Inserts a [User] in the database
     */
    suspend fun saveUser() {
        if (validateInput()) {
            usersRepository.insertUser(newUserUiState.userDetails.toUser())
        }
    }

    private fun validateInput(uiState: UserDetails = newUserUiState.userDetails): Boolean {
        return with(uiState) {
            storeName.isNotBlank() && email.isNotBlank() && phoneNumber.isNotBlank() && password.isNotBlank()
        }
    }
}

/**
 * Represents Ui State for an Item.
 */
data class NewUserUiState(
    val userDetails: UserDetails = UserDetails(),
    val isEntryValid: Boolean = false
)

data class UserDetails(
    val storeName: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val password: String = "",
)

/**
 * Extension function to convert [NewUserUiState] to [User] */
fun UserDetails.toUser(): User = User(
    storeName = storeName,
    email = email,
    phoneNumber = phoneNumber,
    password = password,
)

/**
 * Extension function to convert [User] to [NewUserUiState]
 */
fun User.toNewUserUiState(isEntryValid: Boolean = false): NewUserUiState = NewUserUiState(
    userDetails = this.toUserDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [User] to [UserDetails]
 */
fun User.toUserDetails(): UserDetails = UserDetails(
    storeName = storeName,
    email = email.toString(),
    phoneNumber = phoneNumber.toString(),
    password = password.toString()
)


