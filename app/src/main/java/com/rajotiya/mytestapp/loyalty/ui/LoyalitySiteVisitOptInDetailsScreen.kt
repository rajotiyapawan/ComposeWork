package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFont


@Composable
fun LoyalitySiteVisitOptInDetailsScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.Black),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(bottom = 76.dp)
                .verticalScroll(
                    state = rememberScrollState()
                ), horizontalAlignment = Alignment.Start
        )
        {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .size(24.dp)
            )
            TopView(modifier = Modifier.fillMaxWidth())
            DetailedView(Modifier)
        }
        BottomView(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun BottomView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 23.dp, topEnd = 23.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        BottomRedButton(enable = true, ctaText = "Book Site Visit", onClick = {})
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
            .clickable { if (enable) onClick() }
            .background(color = Color(0xffd8232a), shape = RoundedCornerShape(50))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = ctaText,
                color = if (enable) Color.White else Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null,
                tint = Color.White,
            )
        }
    }
}


@Composable
fun DetailedView(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 7.dp, vertical = 20.dp)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 24.dp, topEnd = 24.dp, bottomEnd = 24.dp, bottomStart = 24.dp
                ),

                )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xfffff5f6), Color(0xfff5f5f5)),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
    ) {
        Spacer(modifier = Modifier.height(14.dp))
        EasyStepsView()
        Spacer(modifier = Modifier.height(12.dp))
        FAndQView()
        Spacer(modifier = Modifier.height(12.dp))
        LoyaltyTermsConditionsView(modifier = Modifier, tnc = "Horem ipsum dolor sit amet, consectetur adipiscing elit. Etiam eu turpis molestie, dictum est a, mattis tellus. Sed dignissim, metus nec fringilla accumsan, risus sem sollicitudin lacus, ut interdum tellus elit sed risus. Maecenas eget condimentum velit, sit amet feugiat lectus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Praesent auctor purus luctus enim egestas, ac scelerisque ante pulvinar. Donec ut rhoncus ex. Suspendisse ac rhoncus nisl, eu tempor urna. Curabitur vel bibendum lorem. Morbi convallis convallis diam sit amet lacinia. Aliquam in elementum tellus.")
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
private fun FAndQView() {
    var expandedState by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 9.dp)
            .clip(shape = RoundedCornerShape(12))
            .background(color = Color.White)
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Frequently asked questions",
                fontSize = 16.sp,
                color = Color(0xff303030),
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
            )
            IconButton(
                onClick = {
                    expandedState = !expandedState
                },
            ) {
                Icon(
                    Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.Gray,
                )
            }
        }
        if (expandedState) {
            Spacer(modifier = Modifier.height(12.dp))
            QAView(
                "How  Can I earn more points?",
                "Do the tasks and you will gain points on complition of every task"
            )
            Spacer(modifier = Modifier.height(12.dp))
            QAView(
                "How can I redeem rewards?",
                "No, late parties is not allowed in this socierty sometime neighbours come to poke you."
            )
        }
    }
}

@Composable
fun QAView(que: String, ans: String) {
    Column {
        Row {
            Text(
                text = "Q : ",
                fontSize = 14.sp,
                color = Color(0xff333333),
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))        
            )
            Spacer(modifier = Modifier.width(7.dp))
            Text(
                text = que, fontSize = 14.sp,
                color = Color(0xff333333),
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))   
            )
        }
        Spacer(modifier = Modifier.height(7.dp))
        Row {
            Text(
                text = "A : ",
                fontSize = 14.sp,
                color = Color(0xff009681),
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_BOLD))
            )
            Spacer(modifier = Modifier.width(7.dp))
            Text(
                text = ans, fontSize = 14.sp, color = Color(0xff333333),
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR))
            )
        }
    }
}

@Composable
fun EasyStepsView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 9.dp)
            .clip(shape = RoundedCornerShape(12))
            .background(color = Color.White)
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        Text(
            "Get your free Uber cab for Site Visits in 3 easy steps:",
            fontSize = 16.sp,
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
            color = Color(0xff303030)
        )
        Spacer(modifier = Modifier.height(13.dp))
        IconTextView("Select your availability for a Site Visit.")
        IconTextView("Shortlist properties that you’re interested in.")
        IconTextView("Our team will call to book your Site Visit.")
        Text("Complete the Site Visit to earn 5000 points", fontSize = 14.sp)
    }

}

@Composable
fun IconTextView(text: String) {
    Row(
        modifier = Modifier.padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null)
        Spacer(modifier = Modifier.width(12.dp))
        Text(text, fontSize = 14.sp, color = Color(0xff000000),
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR))
        )
    }
}


@Composable
fun TopView(modifier: Modifier) {
    Column(modifier = modifier.padding(horizontal = 30.dp)) {
        BenefitsView(
            modifier = Modifier.fillMaxWidth(),
            Icons.AutoMirrored.Outlined.ArrowBack,
            "Free Uber Cab for Site Visit"
        )
        BenefitsView(
            modifier = Modifier.fillMaxWidth(),
            Icons.AutoMirrored.Outlined.ArrowBack,
            "Free Uber Cab for Site Visit"
        )
        BenefitsView(
            modifier = Modifier.fillMaxWidth(),
            Icons.AutoMirrored.Outlined.ArrowBack,
            "Free Uber Cab for Site Visit"
        )

    }
}

@Composable
fun BenefitsView(modifier: Modifier, icon: ImageVector, text: String) {
    Row(modifier = modifier.padding(top = 9.dp), horizontalArrangement = Arrangement.Start) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .border(width = 1.dp, color = Color(0x85ffffff), shape = RoundedCornerShape(8.dp))
                .width(90.dp)
                .height(60.dp)
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(
                        topStart = 8.dp,
                        bottomStart = 8.dp,
                        topEnd = 8.dp,
                        bottomEnd = 8.dp
                    )
                )

        )
        Text(
            text,
            fontSize = 14.sp,
            color = Color.White,
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
            modifier = Modifier.padding(start = 11.dp, top = 4.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LoyalityScreenPreview() {
    LoyalitySiteVisitOptInDetailsScreen()
}