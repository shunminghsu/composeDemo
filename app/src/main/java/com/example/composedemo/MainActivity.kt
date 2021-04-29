package com.example.composedemo

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.*
import com.example.composedemo.ui.theme.BlogBlue
import com.example.composedemo.ui.theme.ComposeDemoTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mvvmViewModel:MyMvvmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MyApp(mvvmViewModel)
                }
            }
        }
    }
}

@Composable
fun MyApp(viewModel: MyMvvmViewModel) {
    loginScreen()
}

const val padding1 = 40
@Preview
@Composable
fun loginScreen() {
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
        NameEditRow()
        Spacer(modifier = Modifier.height(10.dp))
        PasswordEditRow()
        Spacer(modifier = Modifier.height(10.dp))
        InfoTextRow()
        Spacer(modifier = Modifier.height(10.dp))
        ButtonList()
    }
}


@Composable
fun NameEditRow() {
    //Icon(Icons.Rounded.AccountBox, contentDescription = "Localized description")
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .padding(start = padding1.dp, end = padding1.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(10))
            //.background(Color.White)
            .padding(0.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_account),
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier
                .padding(12.dp)
        )
        Divider(color = Color.Black, modifier = Modifier
            .fillMaxHeight()
            .width(1.dp))
        Text(text = "用戶名或郵箱",
            modifier = Modifier
                .padding(12.dp),
            style = TextStyle(fontSize = 18.sp, color = Color.Gray)
        )
    }
}

@Composable
fun PasswordEditRow() {
    //Icon(Icons.Rounded.AccountBox, contentDescription = "Localized description")
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .padding(start = padding1.dp, end = padding1.dp)
            .padding(0.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(10))
            //.background(Color.White)
            .padding(0.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_password),
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier
                .padding(12.dp)
        )
        Divider(color = Color.Black, modifier = Modifier
            .fillMaxHeight()
            .width(1.dp))
        Text(text = "密碼",
            modifier = Modifier
                .padding(12.dp),
            style = TextStyle(fontSize = 18.sp, color = Color.Gray)
        )
    }
}

@Composable
fun InfoTextRow() {
    //Icon(Icons.Rounded.AccountBox, contentDescription = "Localized description")
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .padding(start = padding1.dp, end = padding1.dp),

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
fun ButtonList() {
    Column(
        modifier = Modifier
            //.height(IntrinsicSize.Max)
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = padding1.dp, end = padding1.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(
            onClick = {  },
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
        MyButton(
            color = Color.Gray,
            child = { Text( text = "Google 帳號登入", style = TextStyle(fontSize = 18.sp) ) }
        )
        Spacer(modifier = Modifier.height(5.dp))
        MyButton(
            color = Color.Gray,
            child = { Text( text = "Facebook 帳號登入", style = TextStyle(fontSize = 18.sp) ) }
        )
        Spacer(modifier = Modifier.height(5.dp))
        MyButton(
            color = Color.Gray,
            child = { Text( text = "Line 帳號登入", style = TextStyle(fontSize = 18.sp) ) }
        )
    }
}

@Composable
fun MyButton(color: Color, child: @Composable () -> Unit) {
    OutlinedButton(
        onClick = {  },
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        border = BorderStroke(1.dp, color),
        shape = RoundedCornerShape(50), // = 50% percent
        //or shape = CircleShape
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
    ){
        child()
    }
}








@HiltViewModel
class MyMvvmViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: MyRepository,
): ViewModel() {
    private val _isRegisPage = MutableLiveData(false)
    val isRegisPage: LiveData<Boolean> = _isRegisPage

    fun goRegisterPage(value: Boolean) {
        _isRegisPage.value = value
    }
    
    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    // onNameChange is an event we're defining that the UI can invoke
    // (events flow up from UI)
    fun onNameChange(newName: String) {
        _name.value = newName
    }


  fun test(text: String) {
      viewModelScope.launch {
          withContext(Dispatchers.IO) {
              repository.save(text)
          }
      }
  }
}