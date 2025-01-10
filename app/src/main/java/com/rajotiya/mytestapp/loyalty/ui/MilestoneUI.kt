package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.loyalty.models.MileStone
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFontFamily

@Composable
fun MilestoneView(
    modifier: Modifier = Modifier,
    milestones: List<MileStone>?,
    currentPoints: Int,
    strokeWidth: Int = 4,
) {
    milestones?.let {
        val scrollState = rememberScrollState()
        val milestoneSpacing = 100.dp // Fixed spacing between milestones
        val milestoneSize = 50.dp // Size of each milestone horizontal
        val verticalLineSize = 20.dp

        // Find progress fraction based on milestones
        val totalMilestones = milestones.size
        val milestoneValues = milestones.map { it.pntsD?.replace("K", "000", ignoreCase = true)?.toInt() ?: 0 }
        val currentMileStoneIndex = milestoneValues.indexOfLast { it <= currentPoints }

        val progressFraction by remember(currentPoints, milestones) {
            derivedStateOf {
                currentMileStoneIndex.let { index ->
                    val startValue = milestoneValues.getOrNull(index) ?: 0
                    val endValue = milestoneValues.getOrNull(index + 1) ?: startValue
                    (currentPoints - startValue).toFloat() / (endValue - startValue).toFloat()
                }
            }
        }

        val totalWidth = (totalMilestones - 1) * milestoneSpacing
        Box(
            modifier = modifier
                .horizontalScroll(scrollState)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(milestoneSize)
            ) {
                milestones.forEachIndexed { index, milestone ->
                    Column(
                        modifier = Modifier
                            .offset(x = (-milestoneSize / 2)),
                        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        if (index > 0) {
                            Box(
                                modifier = Modifier
                                    .size(milestoneSize)
                                    .border(width = 1.dp, color = Color(0xffd9d9d9), shape = RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(painter = painterResource(R.drawable.ic_prime_bitmap_chat), contentDescription = null)
                            }
                            VerticalDivider(modifier = Modifier.height(32.dp), thickness = 2.dp, color = Color(0xffd9d9d9))
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(milestoneSize)
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .padding(top = milestoneSize + verticalLineSize)
                    .fillMaxWidth()
            ) {
                // Background Line
                Canvas(
                    modifier = Modifier
                        .width(totalWidth)
                        .padding(start = 16.dp, end = milestoneSize / 2)
                        .height(strokeWidth.dp)
                ) {
                    val milestoneOffsetPx = milestoneSpacing.toPx()
                    drawLine(
                        color = Color(0xff794343),
                        start = Offset(size.height / 2, size.height / 2),
                        end = Offset(totalWidth.toPx(), size.height / 2),
                        strokeWidth = size.height,
                        cap = StrokeCap.Round
                    )

                    val progressEndX = currentMileStoneIndex * milestoneOffsetPx
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
                        start = Offset(size.height / 2, size.height / 2),
                        end = Offset(
                            progressEndX + progressFraction * milestoneOffsetPx - size.height,
                            size.height / 2
                        ),
                        strokeWidth = size.height,
                        cap = StrokeCap.Round
                    )
                }
            }

//                if (progressFraction != 0f) {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp, end = milestoneSize / 2)
                    .run {
                        padding(top = milestoneSize + verticalLineSize - strokeWidth.dp - 22.dp)
                            .offset(x = currentMileStoneIndex * milestoneSpacing + (progressFraction * milestoneSpacing) - strokeWidth.dp -12.dp)
                    }, contentAlignment = Alignment.Center
            ) {
                val currFloat = currentPoints/1000f
                val currPoint = if (currFloat % 1 == 0f) {
                    currFloat.toInt().toString() // Convert to Int if there's no decimal
                } else {
                    currFloat.toString() // Keep the Float format if there are decimals
                }
                Text(
                    "${currPoint}K",
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                    fontSize = 10.sp,
                    lineHeight = 18.sp,
                    color = Color.White
                )
                Icon(
                    Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(top = 18.dp)
                )
//                    }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = milestoneSize + verticalLineSize + strokeWidth.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(milestoneSize)
            ) {
                milestones.forEachIndexed { index, milestone ->
                    Column(
                        modifier = Modifier
                            .offset(x = (-milestoneSize / 2))
                            .width(milestoneSize),
                        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = milestone?.pntsD ?: "",
                            fontSize = 10.sp,
                            color = Color.White,
                            textAlign = if (index == 0) TextAlign.Center else TextAlign.Left,
                            modifier = Modifier
                                .width(milestoneSize)
                                .padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MilestoneViewPreview() {
    Column {
        MilestoneView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .background(Color.Black)
                .padding(vertical = 20.dp),
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
            currentPoints = 30000,
            strokeWidth = 12
        )
    }
}