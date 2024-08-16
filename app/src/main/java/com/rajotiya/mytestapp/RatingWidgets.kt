package com.rajotiya.mytestapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by Pawan Rajotiya on 13-06-2024.
 */

private val unselectedResIDs = intArrayOf(
    R.drawable.ic_unselected_one,
    R.drawable.ic_unselected_two,
    R.drawable.ic_unselected_three,
    R.drawable.ic_unselected_four,
    R.drawable.ic_unselected_five
)
private val selectedResIDs = intArrayOf(
    R.drawable.ic_selected_one,
    R.drawable.ic_selected_two,
    R.drawable.ic_selected_three,
    R.drawable.ic_selected_four,
    R.drawable.ic_selected_five
)

@Composable
fun RatingCardUI() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(
                    color = Color(0xfffff7e1),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(top = 36.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Loving the experience?")
            Box(modifier = Modifier.height(11.dp))
            Text(text = "Rate us 5 Star")
        }
        Row(modifier = Modifier
            .padding(horizontal = 12.dp)
            .offset(y = (-32).dp)
            .fillMaxWidth()
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(8.dp))
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .padding(2.dp)
            .drawBehind {
                drawRoundRect(
                    color = Color(0xffffebb3), style = Stroke(
                        width = 1f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 8f), 0f),
                        join = StrokeJoin.Round
                    )
                )
            }
            .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            val (selected, onSelection) = remember {
                mutableStateOf(-1)
            }
            repeat(5) { index ->
                val resId = if (selected == index) selectedResIDs[index] else unselectedResIDs[index]
                Box(
                    modifier = Modifier.selectable(
                        selected = selected == index,
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = { onSelection(index) }
                    )
                ) {
                    Image(
                        painter = painterResource(id = resId),
                        contentDescription = null
                    )
                }
            }
        }
        Text(
            text = "139,395 Rated us on play store as well",
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun FeedbackDialogUI() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
            .padding(16.dp), verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(ScrollState(0))
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.mb_prime_call_white), contentDescription = null, modifier = Modifier
                        .align(
                            Alignment.CenterEnd
                        )
                        .padding(start = 8.dp, bottom = 8.dp)
                )
            }
            Text(text = "Help us improve your experience", fontSize = 18.sp, modifier = Modifier.padding(top = 12.dp))
            Text(text = "Let us Know what went wrong", fontSize = 14.sp, modifier = Modifier.padding(top = 8.dp))

            val (optionSelected, onOptionSelected) = remember { mutableStateOf("Search Issue") }
            val (subOptionSelected, onSubOptionSelected) = remember { mutableStateOf("") }

            val options = arrayListOf(
                "Poor Listing Quality",
                "App Crash/Slow Speed",
                "Search Issue",
                "Notification Issue",
                "Post Property/Login Issue",
                "Other"
            )
            val subOptions = arrayListOf(
                "Poor Listing Quality",
                "App Crash/Slow Speed",
                "Search Issue",
                "Notification Issue",
                "Post Property/Login Issue",
                "Other"
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp)
            ) {
                options.forEach { text ->
                    Row(
                        Modifier
                            .selectable(
                                selected = (text == optionSelected),
                                onClick = {
                                    onOptionSelected(text)
                                }
                            )
                            .padding(end = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == optionSelected),
                            onClick = { onOptionSelected(text) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(
                                    0xff009681
                                ),
                                unselectedColor = Color(0xffd7d7d7)
                            )
                        )
                        Text(
                            text = text,
                            modifier = Modifier.padding(start = 8.dp),
                            fontSize = 14.sp
                        )
                    }

                    if (text == optionSelected) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color(0xfff5f5f5), shape = RoundedCornerShape(8.dp))
                                .padding(horizontal = 12.dp, vertical = 16.dp)
                        ) {
                            subOptions.forEach { subText ->
                                Row(
                                    Modifier
                                        .selectable(
                                            selected = (subText == subOptionSelected),
                                            onClick = {
                                                onSubOptionSelected(subText)
                                            }
                                        )
                                        .padding(end = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = (subText == subOptionSelected),
                                        onClick = { onSubOptionSelected(subText) },
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = Color(
                                                0xff009681
                                            ),
                                            unselectedColor = Color(0xffd7d7d7)
                                        )
                                    )
                                    Text(
                                        text = subText,
                                        modifier = Modifier.padding(start = 8.dp),
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }

                }
            }

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(color = Color(0xffd8232a), shape = RoundedCornerShape(50))
                .padding(vertical = 10.dp)
        ) {
            Text(text = "Submit", color = Color.White, modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun RatingBottomDialogUI(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 36.dp, bottom = 20.dp)
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Loving the experience?",
//                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                fontSize = 18.sp, lineHeight = 20.sp
            )
            Box(modifier = Modifier.height(11.dp))
            Text(
                text = "Rate us 5 Star",
                fontWeight = FontWeight(600),
//                fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                fontSize = 16.sp, lineHeight = 20.sp
            )
        }

        val (selected, onSelection) = remember { mutableStateOf(-1) }

        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            repeat(5) { index ->
                val resId = if (selected == index) selectedResIDs[index] else unselectedResIDs[index]
                Box(
                    modifier = Modifier.selectable(
                        selected = selected == index,
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = { onSelection(index) }
                    )
                ) {
                    Image(
                        painter = painterResource(id = resId),
                        contentDescription = null
                    )
                }
            }
        }
        val countRated = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight(600)
//                    fontFamily = FontFamily(Font(R.font.montserrat_semibold))
                )
            ) {
                append("139,395")
            }
            append(" Rated us on play store as well")
        }
        Text(
            text = countRated,
//            fontFamily = FontFamily(Font(R.font.montserrat_regular)),
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun FeedbackBottomDialogUI(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 30.dp)
    ) {
        Text(
            text = "Help us improve your experience",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            lineHeight = 24.sp
        )
        Text(
            text = "Let us Know what went wrong",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            lineHeight = 24.sp
        )
        Box(modifier = Modifier.height(30.dp))

        val options = arrayListOf(
            "Poor Listing Quality",
            "App Crash/Slow Speed",
            "Search Issue",
            "Notification Issue",
            "Post Property/Login Issue",
            "Other"
        )

        val (optionSelected, onOptionSelected) = remember { mutableStateOf("") }

        var i = 0
        while (i < options.size) {
            val firstText = options[i]
            val secondText = if ((i + 1) < options.size) options[i + 1] else ""
            RadioButtonRow(firstText = firstText, secondText = secondText, optionSelected, onOptionSelected)
            i += 2
        }
        Box(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xffd8232a), shape = RoundedCornerShape(50))
                .padding(vertical = 10.dp)
        ) {
            Text(text = "Submit", color = Color.White, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun RadioButtonRow(firstText: String, secondText: String, optionSelected: String, onOptionSelected: (String) -> Unit) {
    val firstFont = if (firstText == optionSelected) FontWeight(600) else FontWeight(400)
    val secondFont = if (secondText == optionSelected) FontWeight(600) else FontWeight(400)
    Row(modifier = Modifier.padding(bottom = 20.dp), verticalAlignment = Alignment.CenterVertically) {
        Row(
            Modifier
                .weight(1f)
                .selectable(
                    selected = (firstText == optionSelected),
                    onClick = {
                        onOptionSelected(firstText)
                    }
                )
                .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                modifier = Modifier.size(20.dp),
                selected = (firstText == optionSelected),
                onClick = null,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(
                        0xff009681
                    ),
                    unselectedColor = Color(0xffd7d7d7)
                )
            )
            Text(
                text = firstText,
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 12.sp,
                fontWeight = firstFont
            )
        }
        if (secondText.isNotEmpty()) {
            Row(
                Modifier
                    .weight(1f)
                    .selectable(
                        selected = (secondText == optionSelected),
                        onClick = {
                            onOptionSelected(secondText)
                        }
                    )
                    .padding(end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    modifier = Modifier.size(20.dp),
                    selected = (secondText == optionSelected),
                    onClick = null,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(
                            0xff009681
                        ),
                        unselectedColor = Color(0xffd7d7d7)
                    )
                )
                Text(
                    text = secondText,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 12.sp,
                    fontWeight = secondFont
                )
            }
        }
    }
}

@Composable
fun BottomDialogTemplateUI(content: @Composable (modifier: Modifier) -> Unit) {
    Column {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.round_cross), contentDescription = null, modifier = Modifier.align(
                    Alignment.CenterEnd
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            content(
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            )
        }
    }
}

@Preview
@Composable
private fun RatingPreview() {
    Surface {
        Column(
            modifier = Modifier
                .verticalScroll(ScrollState(0))
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.Black)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
//            RatingCardUI()
//            FeedbackDialogUI()
            BottomDialogTemplateUI {
                RatingBottomDialogUI(modifier = it)
            }
        }
    }
}