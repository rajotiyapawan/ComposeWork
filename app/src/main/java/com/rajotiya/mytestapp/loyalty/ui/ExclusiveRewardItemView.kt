package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.utils.noRippleClick
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.TextWithIcon
import com.rajotiya.mytestapp.utility.getFont
import com.rajotiya.mytestapp.utility.getFontFamily

@Composable
fun ExclusiveRewardItemView(modifier: Modifier = Modifier, onClaim: (id:String)->Unit) {
    Column(
        modifier = modifier
            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
            .border(width = 1.dp, color = Color(0xffd7d7d7), shape = RoundedCornerShape(12.dp))
    ) {
        Box(
            modifier = Modifier
                .height(94.dp)
                .clip(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.mbimageloader_no_image_new),
                contentDescription = null,
                alignment = Alignment.TopEnd
            )
            WorthView(price = "500")
        }
        Text(
            "Contact 5 owners for free",
            fontSize = 14.sp,
            color = Color(0xff303030),
            fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
            modifier = Modifier.padding(start = 8.dp, top = 9.dp, end = 8.dp, bottom = 6.dp)
        )
        TextWithIcon(
            text = "Get for <icon> 1000 points",
            iconText = "<icon>",
            icon = { Image(painter = painterResource(R.drawable.loyalty_coin), contentDescription = null) },
            textStyle = TextStyle(
                fontSize = 10.sp, fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
            ),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        BottomRedButton(enable = true, ctaText = "Unlock", onClick = { onClaim("") })

    }
}

@Composable
private fun BottomRedButton(
    modifier: Modifier = Modifier,
    enable: Boolean,
    ctaText: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClick { if (enable) onClick() }
            .padding(8.dp)
            .background(
                shape = RoundedCornerShape(50),
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xffd40000), Color(0xff892334))
                )
            )
            .padding(vertical = 7.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = ctaText,
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
        )

    }
}


@Composable
private fun GetForView() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Get for ",
            color = Color(0xff303030),
            fontSize = 10.sp,
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))

        )
        Image(
            painter = painterResource(id = R.drawable.loyalty_coin), contentDescription = null,
            modifier = Modifier
                .width(10.dp)
                .padding(horizontal = 2.dp)
        )
        Text(
            text = "1000 points",
            color = Color(0xff303030),
            fontSize = 10.sp,
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
        )
    }
}

@Composable
private fun WorthView(price: String) {
    Row(
        modifier = Modifier
            .padding(end = 10.dp)
            .background(
                color = Color(0xffffc72c),
                shape = RoundedCornerShape(bottomStart = 4.dp, bottomEnd = 4.dp)
            )
            .padding(vertical = 2.dp, horizontal = 4.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                append("Worth ")
                withStyle(
                    style = SpanStyle(
                        fontFamily = getFontFamily(Constants.MONTSERRAT_BOLD)
                    )
                ) {
                    append("₹ $price")
                }
            },
            color = Color(0xff000000),
            fontSize = 10.sp,
            fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
        )
    }
}