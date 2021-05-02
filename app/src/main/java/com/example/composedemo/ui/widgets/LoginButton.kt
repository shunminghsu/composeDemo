import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoginButton(color: Color, child: @Composable () -> Unit) {
    OutlinedButton(
        onClick = {
            println("onClick")
        },
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