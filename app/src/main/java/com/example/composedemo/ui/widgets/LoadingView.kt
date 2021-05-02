import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import java.util.concurrent.TimeUnit
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Loading(
    modifier: Modifier = Modifier
) {
    val nbMarker = 18
    val deltaXAnim = rememberInfiniteTransition()
    val dx by deltaXAnim.animateFloat(
        initialValue = 0f,
        targetValue = (nbMarker - 1).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(777, easing = LinearEasing)
        )
    )
    val bb = Color(57, 184, 235)
    val mark = Color(22, 199, 158)
    Box(
        modifier
            .fillMaxHeight()
            .aspectRatio(1f)
    ) {
        for (i in 0 until nbMarker) {
            val _color = Color(57, 184, 235, 255 * (nbMarker - (dx.toInt() - i)) / nbMarker)
            //val _makrR = 8f *  (nbMarker - (dx.toInt() - i)) / nbMarker
            MarkerCircle(
                angle = i * (360 / nbMarker),
                color = _color,
                radius = when(dx.toInt() - i) {
                    in -(nbMarker-5)..-(nbMarker-7) -> 5f
                    in -(nbMarker-3)..-(nbMarker-5) -> 7f
                    in -(nbMarker-1)..-(nbMarker-3) -> 9f
                    0 -> 10f
                    in 1..3 -> 9f
                    in 3..5 -> 7f
                    in 5..7 -> 5f
                    else -> 4f
                }
            )
        }
    }
}

@Composable
internal fun MarkerLine(
    angle: Int,
    color: Color,
    modifier: Modifier = Modifier
) {
    val startFloatingPoint: Float = .32f
    val endFloatingPoint: Float = .50f
    val strokeWidth: Float = 8f

    Box(
        modifier
            .fillMaxSize()
            .drawBehind {
                val theta = (angle - 90) * PI.toFloat() / 180f
                val startRadius = size.width / 2 * startFloatingPoint
                val endRadius = size.width / 2 * endFloatingPoint
                val startPos = Offset(cos(theta) * startRadius, sin(theta) * startRadius)
                val endPos = Offset(cos(theta) * endRadius, sin(theta) * endRadius)
                drawLine(
                    color = color,
                    start = center + startPos,
                    end = center + endPos,
                    strokeWidth = strokeWidth,
                    cap = StrokeCap.Round
                )
            }
    )
}


@Composable
internal fun MarkerCircle(
    angle: Int,
    color: Color,
    radius: Float,
    modifier: Modifier = Modifier
) {
    val startFloatingPoint: Float = .45f
    Box(
        modifier
            .fillMaxSize()
            .drawBehind {
                val theta = (angle - 90) * PI.toFloat() / 180f
                val startRadius = size.width / 2 * startFloatingPoint
                val startPos = Offset(cos(theta) * startRadius, sin(theta) * startRadius)
                drawCircle(
                    color = color,
                    center = center + startPos,
                    radius = radius
                )
            }
    )
}