package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MilestoneView(
    modifier: Modifier = Modifier,
    milestones: List<String>,
    currentPoints: Int,
    progressLineHeight: Int = 4,
) {
    var boxWidth by remember { mutableStateOf(0f) } // Holds the width of the Box
    var progressFraction by remember { mutableStateOf(0f) }

    // Find progress fraction based on milestones
    val totalMilestones = milestones.size
    val milestoneValues = milestones.map { it.replace("K", "000", ignoreCase = true).toInt() }

    LaunchedEffect(Unit) {
        progressFraction = milestoneValues.indexOfLast { it <= currentPoints }
            .let { index ->
                val startValue = milestoneValues.getOrNull(index) ?: 0
                val endValue = milestoneValues.getOrNull(index + 1) ?: startValue
                val progressWithinSegment = (currentPoints - startValue).toFloat() / (endValue - startValue).toFloat()
                (index + progressWithinSegment) / (totalMilestones - 1)
            }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
            .height(112.dp)
            .onGloballyPositioned { coordinates ->
                boxWidth = coordinates.size.width.toFloat()
            }
    ) {
        // Background Line
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp)
                .height(progressLineHeight.dp)
                .align(Alignment.BottomStart)
        ) {
            val strokeWidth = size.height
            drawLine(
                color = Color(0xff794343),
                start = Offset(strokeWidth / 2, size.height / 2),
                end = Offset(size.width - strokeWidth / 2, size.height / 2),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
        }

        // Progress Line
        Canvas(
            modifier = Modifier
                .fillMaxWidth(fraction = progressFraction)
                .padding(bottom = 18.dp)
                .height(progressLineHeight.dp)
                .align(Alignment.BottomStart)
        ) {
            val strokeWidth = size.height
            drawLine(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xffd40000),
                        Color(0xffde3719),
                        Color(0xffe86d31),
                        Color(0xfff09945),
                        Color(0xfff6bc54),
                        Color(0xfffbd45f),
                        Color(0xfffee366),
                        Color(0xffffe969)
                    )
                ),
                start = Offset(strokeWidth / 2, size.height / 2),
                end = Offset(size.width - strokeWidth / 2, size.height / 2),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
        }

        // Milestones
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.BottomStart),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            milestones.forEachIndexed { index, milestone ->
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
                    if (index > 0) {
                        Box(
                            modifier = Modifier.offset(x = (-27).dp)
                                .size(width = 54.dp, height = 51.dp)
                                .border(width = 1.dp, color = Color(0xffd9d9d9), shape = RoundedCornerShape(8.dp))
                        ) {

                        }
                        VerticalDivider(modifier = Modifier.height(32.dp), thickness = 2.dp, color = Color(0xffd9d9d9))
                    }
                    Text(
                        text = milestone,
                        fontSize = 10.sp,
                        color = Color.White,
                        modifier = Modifier.padding(top = (progressLineHeight + 8).dp)
                    )
                }
            }
        }

        // Current Position
        var positionTextWidth by remember { mutableStateOf(0f) }
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .onGloballyPositioned { layoutCoordinates -> positionTextWidth = layoutCoordinates.size.width.toFloat() }
                .offset(
                    x = with(LocalDensity.current) {
                        (progressFraction * boxWidth - positionTextWidth / 2).toDp()
                    },
                    y = -(progressLineHeight + 12).dp
                )
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "$currentPoints",
                    fontSize = 10.sp,
                    color = Color.White
                )
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null, tint = Color(0xffd9d9d9))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MilestoneViewPreview() {
    MilestoneView(
        modifier = Modifier.background(Color.Black),
        milestones = listOf("0", "1K", "2K", "10K", "30K"),
        currentPoints = 1500,
        progressLineHeight = 12
    )
}
