package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.loyalty.models.MileStone

@Composable
fun MilestoneView(
    modifier: Modifier = Modifier,
    milestones: List<MileStone>?,
    currentPoints: Int,
    strokeWidth: Int = 4,
) {
    milestones?.let {
        val scrollState = rememberScrollState()
        val milestoneSpacing = 80.dp // Fixed spacing between milestones
        val milestoneSize = 40.dp // Size of each milestone horizontal
        var progressFraction by remember { mutableStateOf(0f) }
        var mileStoneOffset = remember { 0f }

        // Find progress fraction based on milestones
        val totalMilestones = milestones.size
        val milestoneValues = milestones.map { it.pntsD?.replace("K", "000", ignoreCase = true)?.toInt() ?: 0 }
        val currentMileStoneIndex = milestoneValues.indexOfLast { it <= currentPoints }

        LaunchedEffect(Unit) {
            progressFraction = currentMileStoneIndex
                .let { index ->
                    val startValue = milestoneValues.getOrNull(index) ?: 0
                    val endValue = milestoneValues.getOrNull(index + 1) ?: startValue
                    (currentPoints - startValue).toFloat() / (endValue - startValue).toFloat()
                }
        }

        Column(modifier
            .padding(start = 16.dp)) {
            // Milestones
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(scrollState),
                horizontalArrangement = Arrangement.spacedBy(milestoneSpacing),
                verticalAlignment = Alignment.Bottom
            ) {
                milestones.forEachIndexed { index, milestone ->
                    Column(
                        modifier = Modifier.width(milestoneSpacing),
                        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        if (index > 0) {
                            Box(
                                modifier = Modifier
//                                    .offset(x = (-27).dp)
                                    .size(milestoneSize)
                                    .border(width = 1.dp, color = Color(0xffd9d9d9), shape = RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(painter = painterResource(R.drawable.ic_prime_bitmap_chat), contentDescription = null)
                            }
                            VerticalDivider(modifier = Modifier.height(32.dp), thickness = 2.dp, color = Color(0xffd9d9d9))
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .horizontalScroll(scrollState)
            ) {
                // Background Line
                Canvas(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val milestoneOffsetPx = milestoneSpacing.toPx()
                    mileStoneOffset = milestoneOffsetPx
                    val startX = 0f
                    val endX = startX + (milestones.size - 1) * milestoneOffsetPx
                    drawLine(
                        color = Color(0xff794343),
                        start = Offset(startX, size.height / 2),
                        end = Offset(endX - strokeWidth / 2, size.height / 2),
                        strokeWidth = strokeWidth.toFloat(),
                        cap = StrokeCap.Round
                    )

                    val progressEndX = startX + currentMileStoneIndex*milestoneOffsetPx
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
                        start = Offset(startX+strokeWidth / 2, size.height / 2),
                        end = Offset(progressEndX + progressFraction - strokeWidth / 2, size.height / 2),
                        strokeWidth = strokeWidth.toFloat(),
                        cap = StrokeCap.Round
                    )
                }
            }

            // Milestones
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(scrollState),
                horizontalArrangement = Arrangement.spacedBy(milestoneSpacing),
                verticalAlignment = Alignment.Bottom
            ) {
                milestones.forEachIndexed { index, milestone ->
                    Column(
                        modifier = Modifier.width(milestoneSpacing),
                        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = milestone?.pntsD ?: "",
                            fontSize = 10.sp,
                            color = Color.White,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }

            // Current Position
            Box(
                modifier = Modifier.fillMaxWidth().offset ( x = with(LocalDensity.current){(currentMileStoneIndex*mileStoneOffset).toDp()})
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
}

@Preview(showBackground = true)
@Composable
fun MilestoneViewPreview() {
    MilestoneView(
        modifier = Modifier.fillMaxWidth().background(Color.Black),
        milestones = listOf(
            MileStone(imgUrl = null, pnts = "", pntsD = "0k", claimed = "y"),
            MileStone(imgUrl = null, pnts = "", pntsD = "1k", claimed = "y"),
            MileStone(imgUrl = null, pnts = "", pntsD = "2k"),
            MileStone(imgUrl = null, pnts = "", pntsD = "10k", locked = "y"),
            MileStone(imgUrl = null, pnts = "", pntsD = "12k", locked = "y"),
            MileStone(imgUrl = null, pnts = "", pntsD = "15k", locked = "y"),
            MileStone(imgUrl = null, pnts = "", pntsD = "16k", locked = "y"),
            MileStone(imgUrl = null, pnts = "", pntsD = "17k", locked = "y"),
            MileStone(imgUrl = null, pnts = "", pntsD = "18k", locked = "y"),
            MileStone(imgUrl = null, pnts = "", pntsD = "19k", locked = "y"),
            MileStone(imgUrl = null, pnts = "", pntsD = "20k", locked = "y"),
            MileStone(imgUrl = null, pnts = "", pntsD = "30k", locked = "y"),
        ),
        currentPoints = 1500,
        strokeWidth = 12
    )
}
