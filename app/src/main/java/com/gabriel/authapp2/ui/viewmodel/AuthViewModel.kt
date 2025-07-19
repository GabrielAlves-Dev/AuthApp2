package com.gabriel.authapp2.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.authapp2.data.AuthRepository
import com.gabriel.authapp2.data.RegistrationResult
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    private fun handleResult(onResult: (Boolean) -> Unit, result: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                onResult(result)
            }
        }
    }

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = repository.loginUser(email, password)
            handleResult(onResult, success)
        }
    }

    // MODIFICADO: A assinatura do onResult agora é (RegistrationResult) -> Unit
    fun register(email: String, password: String, name: String, onResult: (RegistrationResult) -> Unit) {
        viewModelScope.launch {
            val result = repository.registerUser(email, password, name)
            withContext(Dispatchers.Main) {
                onResult(result)
            }
        }
    }

    fun resetPassword(email: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = repository.resetPassword(email)
            handleResult(onResult, success)
        }
    }

    fun getUserName(onResult: (String?) -> Unit) {
        viewModelScope.launch {
            val name = repository.getUserName()
            withContext(Dispatchers.Main) {
                onResult(name)
            }
        }
    }

    fun loginWithGoogle(idToken: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = repository.loginWithGoogle(idToken)
            handleResult(onResult, success)
        }
    }

    fun getGoogleSignInClient(context: Context): GoogleSignInClient {
        return repository.getGoogleSignInClient(context)
    }

    fun logout(context: Context) {
        repository.logout(context)
    }

    fun isUserLogged(): Boolean {
        return repository.isUserLogged()
    }
}