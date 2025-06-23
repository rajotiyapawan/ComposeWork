package com.rajotiya.mytestapp.random

/**
 * Created by Pawan Rajotiya on 09-06-2025.
 */


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R

@Composable
fun NPSRatingView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 15.dp, vertical = 15.dp)
    ) {
        Text(
            text = "How likely are you to recommend \nMB Prime to your friends?",
            fontSize = 16.sp,
            color = Color(0xFF303030),
            fontFamily = FontFamily.Default, // Use RobotoMedium if added
            lineHeight = 20.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 17.dp)
                .background(Color(0xFFF5F5F5))
                .padding(horizontal = 8.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                (0..10).forEach { rating ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        when (rating) {
                            in 0..6 -> UnselectedRatingItem(color = colorResource(R.color.red), rating = rating.toString())
                            7, 8 -> UnselectedRatingItem(color = colorResource(R.color.checklist_yellow), rating = rating.toString())
                            else -> UnselectedRatingItem(color = colorResource(R.color.green_demand), rating = rating.toString())
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(11.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                DividerBar(color = Color.Red, modifier = Modifier.weight(0.75f))
                Spacer(modifier = Modifier.width(8.dp))
                DividerBar(color = Color.Yellow, modifier = Modifier.weight(0.2f))
                Spacer(modifier = Modifier.width(8.dp))
                DividerBar(color = Color.Green, modifier = Modifier.weight(0.2f))
            }

            Spacer(modifier = Modifier.height(4.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                LabelText("Not Likely", modifier = Modifier.weight(0.75f))
                LabelText("May Be", modifier = Modifier.weight(0.2f))
                LabelText("Very Likely", modifier = Modifier.weight(0.2f))
            }
        }
    }
}

@Composable
private fun UnselectedRatingItem(color: Color, rating: String="1") {
    Box(
        Modifier
            .size(dimensionResource(R.dimen.nps_circle_radius))
            .clip(CircleShape)
            .border(width = 1.dp, shape = CircleShape, color = color)
            .background(color = Color.White), contentAlignment = Alignment.Center
    ){
        Text(rating, fontSize = 14.sp)
    }
}

@Composable
private fun DividerBar(color: Color, modifier: Modifier) {
    Box(
        modifier = modifier
            .height(1.dp)
            .background(color)
    )
}

@Composable
private fun LabelText(text: String, modifier: Modifier) {
    Text(
        text = text,
        fontSize = 10.sp,
        color = Color(0xFF303030),
        maxLines = 1,
        modifier = modifier
            .wrapContentHeight()
            .padding(horizontal = 2.dp),
        fontFamily = FontFamily.Default,
        textAlign = TextAlign.Center
    )
}