package com.example.tradeline.ui.screens.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradeline.data.User
import com.example.tradeline.data.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class StoreCreationScreenViewModel(private val usersRepository: UsersRepository) : ViewModel() {

    val currentUser = MutableStateFlow<User?>(null)
    var userId : Int? = null

    suspend fun createNewStore(storeName: String, email: String, phoneNumber: String, password: String, confirmPassword: String) {
        if (password == confirmPassword) {
            viewModelScope.launch {
                val user = User(storeName = storeName, email = email, phoneNumber = phoneNumber, password = password)
                usersRepository.insertUser(user)
                setCurrentUser(user)
            }
        }
    }

    private  fun setCurrentUser(user: User){
        currentUser.value = user
        userId = user.id
    }

}

//View Model to validate and insert users in the Room database.
//class StoreCreationScreenViewModel(private val usersRepository: UsersRepository) : ViewModel() {
//
//    suspend fun createNewStore(storeName: String, email: String, phoneNumber: String, password: String, confirmPassword: String) {
//        if (password == confirmPassword) {
//            viewModelScope.launch {
//                val user = User(storeName, email, phoneNumber, password)
//                usersRepository.insertUser(user)
////                val existingStore = usersRepository.getUserByStoreName(storeName)
////                if (existingStore == null) {
////                    val user = User(storeName, email, phoneNumber, password)
////                    usersRepository.insertUser(user)
////                } else{
////                    //
////                }
//
//                //TODO validation that user is nonexistent, and that all fields are filled
//            }
//        }
//    }
//}


