package com.camihruiz24.instagramcopy.feature_login.presentation

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camihruiz24.instagramcopy.feature_login.domain.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    val loginUseCase : LoginUseCase = LoginUseCase()

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _isLoginEnabled = MutableLiveData<Boolean>()
    val isLoginEnabled : LiveData<Boolean> = _isLoginEnabled

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLoginEnabled.value = enableLogin(email, password)
    }

    private fun enableLogin(email: String, password: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 8

    fun onLoginButtonPressed(){
        _isLoading.value = true

        Thread.sleep(800)

        viewModelScope.launch {
            val response = loginUseCase(email.value!!, password.value!!)
            if (response){
                // TODO: Navegar a la siguiente pantalla
                Log.i("Resultado de Login", "Result OK")
            }
        }

        _isLoading.value = false

    }

}