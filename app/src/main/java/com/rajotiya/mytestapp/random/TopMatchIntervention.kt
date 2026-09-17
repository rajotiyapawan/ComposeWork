package com.topmatches.interventions

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.utils.noRippleClick
import com.rajotiya.mytestapp.utility.getFontFamily

/**
 * Modern Jetpack Compose implementation of the "Top Match" intervention banner for SRP.
 *
 * Why: This banner provides a visually distinct call-to-action within the SRP list,
 * highlighting handpicked properties based on the user's recent search activity.
 */
@Composable
fun TopMatchIntervention(
    count: Int,
    onBannerClick: () -> Unit
) {
    val translateAnim by rememberInfiniteTransition().animateFloat(
        initialValue = -100f,
        targetValue = 500f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing)
        ),
        label = "translate"
    )

    // Root Container centered for SRP list
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp, start = 8.dp, end = 8.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xffffebf0),
                        Color.White,
                        Color(0xffffebf0),
                    )
                )
            )
            .noRippleClick(onClick = onBannerClick), verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 24.dp, top = 21.dp, bottom = 21.dp)
        ) {
            Text(
                text = "Because you contacted Edenia Glory",
                color = textColorDark,
                fontSize = 12.sp,
                fontFamily = getFontFamily()
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = mbRed)) {
                        append("$count ")
                    }
                    append("Top-rated Properties Handpicked just for you")
                },
                color = textColorDark,
                fontSize = 18.sp,
                fontFamily = getFontFamily(weight = FontWeight.SemiBold)
            )
            Spacer(Modifier.height(14.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(textColorDark) // CTA base color
            ) {
                Row(
                    Modifier
                        .background(textColorDark, RoundedCornerShape(50))
                        .padding(vertical = 11.dp, horizontal = 12.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "View Matches",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = getFontFamily(weight = FontWeight.SemiBold)
                    )
                    Spacer(Modifier.width(5.dp))
//                    Image(painterResource(R.drawable.), contentDescription = null)
                }

                // 🔹 Shimmer Overlay
                Canvas(modifier = Modifier.matchParentSize()) {

                    val width = size.width
                    val height = size.height

                    val brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.White.copy(alpha = 0.4f),
                            Color.Transparent
                        ),
                        start = Offset(translateAnim - 50f, 0f),
                        end = Offset(translateAnim, 20f)
                    )

                    drawRect(
                        brush = brush,
                        size = size
                    )
                }
            }
        }

        // Right Image: 46x73
        Image(
            painter = painterResource(id = R.drawable.ic_ai_text),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(0.28f),
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopMatchInterventionPreview() {
    TopMatchIntervention(
        count = 21,
        onBannerClick = {}
    )
}
