package com.rajotiya.mytestapp.homedrawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFont

/**
 * Created by Pawan Rajotiya on 08-11-2024.
 */

@Composable
fun DrawerMainScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFd7d7d7))
            .padding(horizontal = 10.dp)
    ) {
        UserStatusView(modifier = Modifier.fillMaxWidth())
        TabsView()
    }
}

@Composable
private fun UserStatusView(modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(vertical = 20.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "For a personalized experience",
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_MEDIUM)),
            fontSize = 16.sp,
            color = textColorDark, modifier = Modifier.weight(1f)
        )
//        Spacer(modifier = Modifier.width(12.dp))
        Box(
            modifier = Modifier
//                .wrapContentWidth()
                .clickable { }
                .background(color = mbRed, shape = RoundedCornerShape(50))
        ) {
            Text(
                text = "Login Now",
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 14.sp,
                maxLines = 1,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
            )
        }
    }
}

@Composable
private fun TabsView(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp)
    ) {
        items(10) { index ->
            TabItem()
            if (index < 10) {
                HorizontalDivider(color = Color(0xFFf1f1f1), thickness = 1.dp)
            }
        }
    }
}

@Composable
private fun TabItem(modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(vertical = 13.dp, horizontal = 4.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = Icons.Default.Home, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Home")
        Spacer(modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight, contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
fun TestDrawer(modifier: Modifier = Modifier) {
    DrawerMainScreen()
}