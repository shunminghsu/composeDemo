package com.example.composedemo

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repo: LoginRepo,
): ViewModel() {
    private val _tryLogin = mutableStateOf(false)
    val tryLogin: State<Boolean> = _tryLogin

    private val _loginState = mutableStateOf<String>("")
    val loginState: State<String> = _loginState

    private val _showRegister = mutableStateOf(false)
    val showRegister: State<Boolean> = _showRegister

    private val _username = mutableStateOf("")
    val username: State<String> = _username
    //var username: String by mutableStateOf("")

    private val _password = mutableStateOf("")
    val pwd: State<String> = _password
    //var pwd: String by mutableStateOf("")


    fun login(_username:String = "", _password:String = "") {
        Log.d("Me", "$_username $_password")
        startLogin()
        viewModelScope.launch {
            repo.login(_username, _password).collect {
                println(it)
                _loginState.value = it
            }
        }
    }

    fun startLogin() {
        _tryLogin.value = true;
    }

    fun stopLogin() {
        _tryLogin.value = false;
    }

    fun setName(username:String) {
        _username.value = username;
    }

    fun setPassword(password:String) {
        _password.value = password;
    }

    fun test() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
               // repository.save(text)
            }
        }
    }

    fun dispose() {
        //todo
    }
}