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

import Loading
import LoginButton
import LoginTextField
import android.app.Application
import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.composedemo.ui.theme.BlogBlue
import com.example.composedemo.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

/**
 * Description:
 * Demo
 *
 * @author ShunMing Hsu
 * Date:    2021/5/1
 */

@HiltAndroidApp
class MyApplication : Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel:LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                ProvideWindowInsets(consumeWindowInsets = true) {
                    MyApp()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release memory
        viewModel.dispose()
    }

}
val PADDING = 40.dp

@Composable
fun MyApp() {
    Log.d("Me", "MyApp")
    Box(modifier = Modifier.fillMaxSize()) {
        LoginScreen()
        LoginStateDialog()
    }
}

//@Preview
@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
    Log.d("Me", "LoginScreen")
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            //.height(IntrinsicSize.Max)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
            .padding(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "登入",
            modifier = Modifier
                .padding(10.dp),
            style = TextStyle(fontSize = 22.sp)
        )
        Divider(
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .width(1.dp))
        Text(
            text = "登入海外博客, 寫出你的故事",
            modifier = Modifier
                .padding(top = 50.dp, bottom = 50.dp),
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
        LoginTextField(
            type = "username",
            onValueChange = { viewModel.setName(it) },
            iconId = R.drawable.ic_account,
            defaultText = "用戶名或郵箱",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                autoCorrect = true,
                imeAction = ImeAction.Next
            ),
            textFieldModifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusRequester.requestFocus()
                }
            ),
        )
        Spacer(modifier = Modifier.height(10.dp))
        LoginTextField(
            type = "password",
            onValueChange = { viewModel.setPassword(it) },
            iconId = R.drawable.ic_password,
            defaultText = "密碼",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                autoCorrect = false,
                imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation(),
            textFieldModifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .focusRequester(focusRequester),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
        )
        Spacer(modifier = Modifier.height(10.dp))
        InfoTextRow()
        Spacer(modifier = Modifier.height(10.dp))
        ButtonList()
    }
}

@Composable
fun InfoTextRow() {
    Log.d("Me", "InfoTextRow")
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .padding(start = PADDING, end = PADDING),

    ) {
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "忘記密碼",
            modifier = Modifier,
            style = TextStyle(fontSize = 14.sp, color = BlogBlue)
        )
        Spacer(modifier = Modifier.weight(1f, true))
        Text(text = "還沒有帳戶?",
            modifier = Modifier,
            style = TextStyle(fontSize = 14.sp, color = Color.Gray)
        )
        Text(text = "前往註冊",
            modifier = Modifier,
            style = TextStyle(fontSize = 14.sp, color = BlogBlue)
        )
        Spacer(modifier = Modifier.height(5.dp))
    }
}

@Composable
fun ButtonList(viewModel: LoginViewModel = viewModel()) {
    Log.d("Me", "ButtonList")
    Column(
        modifier = Modifier
            //.height(IntrinsicSize.Max)
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = PADDING, end = PADDING),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(
            onClick = {
                viewModel.login(_username = viewModel.username.value, viewModel.pwd.value)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            border = BorderStroke(1.dp, BlogBlue),
            shape = RoundedCornerShape(50), // = 50% percent
            //or shape = CircleShape
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = BlogBlue, contentColor = Color.White)
        ){
            Text( text = "登入", style = TextStyle(fontSize = 18.sp) )
        }
        Text(text = "或",
            modifier = Modifier.padding(5.dp),
            style = TextStyle(fontSize = 16.sp, color = Color.Gray)
        )
        LoginButton(
            color = Color.Gray,
            child = { Text( text = "Google 帳號登入", style = TextStyle(fontSize = 18.sp) ) }
        )
        Spacer(modifier = Modifier.height(5.dp))
        LoginButton(
            color = Color.Gray,
            child = { Text( text = "Facebook 帳號登入", style = TextStyle(fontSize = 18.sp) ) }
        )
        Spacer(modifier = Modifier.height(5.dp))
        LoginButton(
            color = Color.Gray,
            child = { Text( text = "Line 帳號登入", style = TextStyle(fontSize = 18.sp) ) }
        )
    }
}

@Composable
fun LoginStateDialog(viewModel: LoginViewModel = viewModel()) {
    Log.d("Me", "LoginStateDialog")
    if (viewModel.tryLogin.value) {
        Dialog(
            onDismissRequest = { viewModel.stopLogin() }
        ) {
            LoginState()
        }
    }
}

@Composable
fun LoginState(viewModel: LoginViewModel = viewModel()) {
    Log.d("Me", "LoginState")
    Box(
        modifier = Modifier
            .height(150.dp)
            .width(150.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            //.fillMaxSize()
            //.padding(70.dp)
            .wrapContentSize(Alignment.Center),
    ) {
        when(viewModel.loginState.value) {
            "success" -> Text("登入成功")
            "fail" -> Text("登入失敗")
            else -> Loading() //FullScreenLoading()
        }
    }
}

@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .height(150.dp)
            .width(150.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            //.fillMaxSize()
            //.padding(70.dp)
            .wrapContentSize(Alignment.Center),
    ) {
        CircularProgressIndicator(color = BlogBlue)
    }
}

//to fix the issue that parent would be re-composition
@Composable
fun Wrapper(content: @Composable () -> Unit) {
    Log.d("Me", "Wrapper")
    content()
}
