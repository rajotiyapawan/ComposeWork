package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFont


@Composable
 fun SVTMContactStatusBanner(modifier: Modifier = Modifier,){
    Column(
        modifier = modifier
            .background(color = Color(0xfff5f5f5), shape = RoundedCornerShape(20.dp))
    ) {
        Box(modifier = Modifier.fillMaxWidth()){
            MiddleViews()
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 13.dp, start = 34.dp, end = 20.dp),horizontalArrangement = Arrangement.SpaceBetween) {
                Image( painter = painterResource(id = R.drawable.ic_unselected_two)
                    , contentDescription = null,
                    modifier = Modifier.padding(top = 5.dp)
                )
                Image( painter = painterResource(id = R.drawable.ic_unselected_two)
                    , contentDescription = null
                )
            }

        }
        BottomViews()
    }
}

@Composable
private  fun BottomViews() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 30.dp, vertical = 12.dp)){
        Row{
            Image( painter = painterResource(id = R.drawable.loyalty_coin)
                , contentDescription = null,
                modifier = Modifier.padding(end = 6.dp)
            )
            Text(
                "15,000 reserved",
                fontSize = 16.sp,
                color = Color(0xff303030),
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
            )
        }
        Text(
            "Points will be credited after the visits.",
            fontSize = 14.sp,
            color = Color(0xff303030),
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR))
        )
        Spacer(Modifier.width(7.dp))
    }
}

@Composable
private fun MiddleViews() {
    Column(
        modifier = Modifier
            .padding(start = 9.dp, end = 9.dp, top = 46.dp)
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(11.dp))
    ) {
        Column(modifier = Modifier.padding(start = 24.dp, end = 11.dp, top = 37.dp)){
            Text(
                "Great!",
                fontSize = 14.sp,
                color = Color(0xff967139),
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_BOLD))
            )
            Text(
                "3 Properties selected",
                fontSize = 17.sp,
                color = Color(0xff303030),
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_BOLD))
            )
            Text(
                "for site visits",
                fontSize = 14.sp,
                color = Color(0xff303030),
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR))
            )
        }

        Row(modifier = Modifier
            .padding(horizontal = 11.dp, vertical = 7.dp)
            .border(width = 1.dp, color = Color(0xFFF5F5F5), shape = RoundedCornerShape(8.dp))){
            Image( painter = painterResource(id = R.drawable.ic_prime_bitmap_chat)
                , contentDescription = null,
                modifier = Modifier.padding(start = 4.dp, top = 6.dp, bottom = 10.dp)
            )
            Text(
                "Our representative will call to schedule your visits.",
                fontSize = 14.sp,
                color = Color(0xff303030),
                modifier = Modifier.padding(horizontal =16.dp, vertical = 4.dp),
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR))

            )
        }
    }
}

@Preview
@Composable
private fun PreviewSVTMContactStatusBanner(){
    SVTMContactStatusBanner(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp))
}