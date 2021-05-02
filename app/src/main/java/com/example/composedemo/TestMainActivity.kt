/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.composedemo


import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.composedemo.ui.theme.BlogBlue
import com.example.composedemo.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets


/**
 * Description:
 * Demo
 *
 * @author ShunMing Hsu
 * Date:    2021/5/1
 */


class TestMainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                ProvideWindowInsets(consumeWindowInsets = true) {
                    MyApp()
                }
            }
        }
    }

    companion object {
        //val FRESH_TIMEOUT = TimeUnit.DAYS.toMillis(1)
        val PADDING = 40.dp
    }
}
/*
@Composable
fun MyApp() {
    //var email: String by remember { mutableStateOf("") }
    //var password: String by remember { mutableStateOf("") }
    val navController = rememberNavController()
    NavHost(navController, startDestination = "login") {
        composable("login") {

        }
    }
}

@Composable
fun Wrapper(content: @Composable () -> Unit) {
    Box {
        Log.d("Me", "Box")
        content()
    }
}
*/





