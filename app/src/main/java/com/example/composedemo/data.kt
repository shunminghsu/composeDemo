package com.example.composedemo

import android.util.Log
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import javax.inject.Inject

@ViewModelScoped
class MyRepository  @Inject constructor() {

    suspend fun save(answer: String) {
        Log.v("Api call", "Make a blocking call to an api")
        delay(1000)
    }
}