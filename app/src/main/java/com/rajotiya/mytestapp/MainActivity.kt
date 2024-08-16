package com.rajotiya.mytestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.sharp.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.ui.theme.MyTestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    RatingCardUI()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Composable
private fun ChatOurExpert() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .background(color = Color.Black, shape = RoundedCornerShape(4.dp))
            .padding(16.dp)
    ) {
        Row {
            Image(painter = painterResource(id = R.drawable.ic_prime_bitmap_chat), contentDescription = null)
            Column {
                Text(
                    text = "For queries or assistance", modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
//            fontFamily = FontFamily(getFont(Constants.MONTSERRAT)),
                    fontSize = 16.sp, color = Color.White, textAlign = TextAlign.Start, fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Call our Expert on", modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
//            fontFamily = FontFamily(getFont(Constants.MONTSERRAT)),
                    fontSize = 12.sp, color = Color(0xff909090), textAlign = TextAlign.Start, fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mb_prime_call_white), contentDescription = null, colorFilter = ColorFilter.tint(color = Color(0xff009681))
                    )
                    Text(
                        text = "7303439363",
//            fontFamily = FontFamily(getFont(Constants.MONTSERRAT)),
                        fontSize = 12.sp, color = Color(0xff009681), textAlign = TextAlign.Start, fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(4.dp))
                    .padding(vertical = 5.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Chat with Us",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
//                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                )
            }
            Button(
                modifier = Modifier
                    .height(25.dp)
                    .weight(1f),
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff909090)),
                shape = RoundedCornerShape(4.dp),
                contentPadding = PaddingValues(vertical = 5.dp, horizontal = 0.dp)
            ) {
                Icon(Icons.Rounded.ShoppingCart, contentDescription = null)
                Text(
                    text = "WhatsApp",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
//                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                )
            }
        }
    }
}

@Composable
private fun BottomCardsLayout() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xff192129))
            .padding(8.dp)
    ) {
        val (selected, onSelection) = remember{ androidx.compose.runtime.mutableStateOf(-1) }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(3) {
                BottomCardItem(modifier = Modifier.weight(1f), selected, onSelection, it)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ),
            shape = RoundedCornerShape(4.dp),
            contentPadding = PaddingValues(vertical = 5.dp, horizontal = 0.dp)
        ) {
            Text(
                text = "Continue with Pro",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
//                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
            )
            Icon(
                Icons.Sharp.ArrowForward, contentDescription = null, modifier = Modifier
                    .padding(start = 8.dp)
                    .height(18.dp)
            )
        }
    }
}

private val pkgArray = arrayListOf(Color(0xffd8d8d8),Color(0xff00c1d6),Color(0xffeac945))

@Composable
private fun BottomCardItem(modifier: Modifier, selected: Int, onSelection: (Int) -> Unit, index: Int) {
    val borderRadius = if (selected==index) 2 else 1
    val itemColor = pkgArray[index]
    val borderColor = if (selected==index) itemColor else Color(0xff565b60)
    Column(modifier = modifier
        .selectable(
            onClick = { onSelection(index) },
            selected = selected == index
        )
        .border(width = borderRadius.dp, color = borderColor, shape = RoundedCornerShape(8.dp))
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = itemColor,
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 4.dp, bottomEnd = 4.dp, bottomStart = 0.dp)
                    )
                    .padding(vertical = 4.dp, horizontal = 6.dp)
            ) {
                Text(
                    text = "BASIC",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    //                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_BOLD)),
                )
            }
            Box(modifier=Modifier.padding(end=8.dp, top=8.dp)) {
                if (selected == index) {
                    Icon(Icons.Rounded.CheckCircle, modifier = Modifier.size(18.dp),contentDescription = null, tint = itemColor)
                } else {
                    Box(modifier = Modifier
                        .size(16.dp)
                        .border(width = 1.dp, color = Color(0xff565b60), shape = RoundedCornerShape(50)))
                }
            }
        }
        Row(modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "\u02b94999",
                color = Color(0xffd2d2d2),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                lineHeight = 18.sp,
                //                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_BOLD)),
            )
            Spacer(modifier = Modifier.width(4.dp))
            Column {
                Text(
                    text = "\u02b99998",
                    color = Color(0xff909090),
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp,
                    textDecoration = TextDecoration.LineThrough,
                    //                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_BOLD)),
                )
                Text(
                    text = "50% Off",
                    color = Color(0xffeac945),
                    textAlign = TextAlign.Center,
                    fontSize = 8.sp,
                    //                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_BOLD)),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomCardsLayout()
}