package com.rajotiya.mytestapp.utility

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import kotlinx.coroutines.delay

/**
 * Created by Pawan Rajotiya on 01-10-2024.
 */

@Composable
fun LoaderUI(modifier: Modifier = Modifier,isTransparentBg:Boolean=false) {
    val messageData = ""//MagicPrefHandler.getInstance().sharedPref.getString(KeyIds.LoaderMessageData, "")
    val messageList = if (messageData.isNullOrEmpty()) {
        listOf("Compare & choose from 300+ Home Interior brands")
    } else {
        messageData.split("##")
    }
    val message = if (messageList.size > 1) messageList.random() else messageList[0]
    Column(
        modifier = modifier
            .background(color = if (isTransparentBg)Color.Transparent else Color.White)
            .padding(horizontal = 40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val list = listOf(
            R.drawable.ic_loader_image_1,
            R.drawable.ic_loader_image_2,
            R.drawable.ic_loader_image_3,
            R.drawable.ic_loader_image_4,
            R.drawable.ic_loader_image_5,
            R.drawable.ic_loader_image_6
        )
        AnimatedImageSlider(images = list)
//        Image(painter = painterResource(id = R.drawable.aob_search_property), contentDescription = null)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = message,
            textAlign = TextAlign.Center,
//            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
            fontSize = 16.sp, color = textColorLight, fontStyle = FontStyle.Italic
        )
    }
}

@Composable
private fun AnimatedImageSlider(images: List<Int>, durationMillis: Long = 700L) {
    var currentIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(key1 = currentIndex) {
        delay(durationMillis)
        currentIndex = (currentIndex + 1) % images.size
    }

    Box(
        modifier = Modifier.size(48.dp),
        contentAlignment = Alignment.Center
    ) {
        images.forEachIndexed { index, imageResId ->
            AnimatedVisibility(
                visible = currentIndex == index,
                enter = slideInHorizontally(initialOffsetX = { it }) + fadeIn(),
                exit = slideOutHorizontally(targetOffsetX = { -it }) + fadeOut()
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Image $index",
                    modifier = Modifier.size(300.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TestLoader() {
    LoaderUI(modifier = Modifier.fillMaxSize())
}