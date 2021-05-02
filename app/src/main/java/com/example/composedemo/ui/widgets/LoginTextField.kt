import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composedemo.LoginViewModel
import com.example.composedemo.PADDING
import com.example.composedemo.Wrapper
import com.example.composedemo.ui.theme.BlogBlue

@Composable
fun LoginTextField(
    type: String,
    onValueChange: (String) -> Unit,
    @DrawableRes iconId: Int,
    defaultText: String,
    keyboardOptions: KeyboardOptions,
    textFieldModifier: Modifier,
    keyboardActions: KeyboardActions = KeyboardActions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    viewModel: LoginViewModel = viewModel()
) {
    Log.d("Me", "LoginTextField")
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .padding(start = PADDING, end = PADDING)
            .border(1.dp, Color.Gray, RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.padding(12.dp)
        )
        Divider(color = Color.Gray, modifier = Modifier
            .fillMaxHeight()
            .width(1.dp))
        //Use the Wrapper to prevent the inline function Row re-composing
        //only the TextField will be re-composing when onValueChanged
        Wrapper {
            TextField(
                value = if (type == "username") viewModel.username.value else viewModel.pwd.value,
                onValueChange = onValueChange,
                placeholder = {
                    Text(defaultText)
                },
                keyboardOptions = keyboardOptions,
                visualTransformation = visualTransformation,
                textStyle = TextStyle.Default.copy(
                    fontSize = 20.sp,
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = BlogBlue
                ),
                modifier = textFieldModifier,
                singleLine = true,
                keyboardActions = keyboardActions
            )
        }
    }
}