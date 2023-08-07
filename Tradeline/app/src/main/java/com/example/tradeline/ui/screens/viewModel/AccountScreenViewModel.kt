package com.example.tradeline.ui.screens.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradeline.ui.data.User
import com.example.tradeline.ui.data.UsersRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AccountScreenViewModel(usersRepository: UsersRepository, storeName: String) : ViewModel()
{
    val accountUiState: StateFlow<AccountUiState> =

        usersRepository.getUserByStoreName(storeName).map { AccountUiState(userDetails = it!!.toUserDetails()) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = AccountUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}

//Ui State for InventoryHomeScreen
data class AccountUiState(val userDetails: UserDetails = UserDetails())

data class UserDetails(
    val id: Int = 0,
    var storeName: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val password: String = ""
)

//Extension function to convert [Product] to [ProductDetails]
fun User.toUserDetails(): UserDetails = UserDetails(
    id = id!!,
    storeName = storeName,
    email = email,
    phoneNumber = phoneNumber,
    password = password
)