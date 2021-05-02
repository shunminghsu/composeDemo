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
import com.example.composedemo.ui.theme.BlogBlue
import com.example.composedemo.R

@Composable
fun LoginPage() {
    println("LoginPage")
    var email: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }
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
        //NameEditRow()
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                //.background(Color.White)
                .padding(0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_account),
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
                    //.wrapContentSize(Alignment.Center)
                    .padding(15.dp)
            )
            Divider(color = Color.Gray, modifier = Modifier
                .fillMaxHeight()
                .width(1.dp))
            TextField(
                value = email,//viewModel.myUsername,
                onValueChange = {
                    email = it
                    //viewModel.setName(it)
                },
                placeholder = {
                    Text("用戶名或郵箱")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    autoCorrect = true,
                    imeAction = ImeAction.Next
                ),
                textStyle = TextStyle.Default.copy(
                    fontSize = 20.sp,
                    //textAlign = TextAlign.Center
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = BlogBlue
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onNext = {
                        //focusRequester.requestFocus()
                    }
                )
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}