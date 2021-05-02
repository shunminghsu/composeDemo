package com.example.composedemo

import android.util.Log

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class LoginRepo @Inject constructor(
//private val webservice: Webservice,
) {

    fun login(username: String, password:String): Flow<String> {
      return loginSuccess();
    }

    private fun loginSuccess():Flow<String> = flow {
        emit("waiting")
        delay(3000)
        emit("success")
    }

    private suspend fun test(username: String, password: String): String {
        Log.v("Api call", "Make a blocking call to an api")
        delay(1000)

        return "success";
    }
}
