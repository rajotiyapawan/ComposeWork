package com.rajotiya.mytestapp.sitevisit_flow.ui.common_views

import android.text.TextUtils
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.sitevisit_flow.domain.models.SiteVisitFlowData
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFontFamily
import com.rajotiya.mytestapp.utility.noRippleClick

@Composable
fun SvExpandablePropCardWidget(
    modifier: Modifier = Modifier,
    propList: List<SiteVisitFlowData.SvProjectItem>,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = if (!expanded) 16.dp else 0.dp)
        ) {
            this@Column.AnimatedVisibility(visible = !expanded && propList.size > 1) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    border = BorderStroke(width = 1.dp, color = Color(0xffe8e8e8)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                        .fillMaxWidth()
                        .height(62.dp)
                        .align(Alignment.TopCenter)
                ) {}
            }
            SvDetailCardUI(
                modifier = Modifier.align(Alignment.TopCenter),
                propItem = propList.first()
            )
            if (propList.size > 1) {
                this@Column.AnimatedVisibility(
                    modifier = Modifier.align(Alignment.BottomCenter).offset(y = 16.dp),
                    visible = !expanded
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(width = 1.dp, color = Color(0xFFe8e8e8)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        shape = CircleShape,
                        modifier = Modifier
                            .size(32.dp)
                            .noRippleClick {
                                if ((propList.size ?: 0) > 1) {
                                    expanded = !expanded
                                }
                            }
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "+${(propList.size ?: 1) - 1}",
                                color = Color.Red,
                                fontSize = 14.sp,
                                fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM)
                            )
                        }
                    }
                }
            }

        }


        AnimatedVisibility(visible = expanded) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 16.dp)
            ) {
                Column {
                    propList.drop(1).forEach { property ->
                        Spacer(modifier = Modifier.height(4.dp))
                        SvDetailCardUI(modifier = Modifier, property)
                    }
                }
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(width = 1.dp, color = Color(0xFFe8e8e8)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    shape = CircleShape,
                    modifier = Modifier
                        .offset(y=10.dp)
                        .align(Alignment.BottomCenter)
                        .size(32.dp)
                        .noRippleClick {
                            if ((propList.size ?: 0) > 1) {
                                expanded = !expanded
                            }
                        }
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowUp,
                            contentDescription = "Up Arrow",
                            tint = Color(0xFFd8232a),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SvDetailCardUI(
    modifier: Modifier,
    propItem: SiteVisitFlowData.SvProjectItem,
) {

    Row(
        modifier = modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .border(
                BorderStroke(1.dp, Color(0xffd7d7d7)),
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(8.dp)
    ) {

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .height(46.dp)
                .width(50.dp)
                .background(color = Color.Green)
        )
        Spacer(modifier = Modifier.width(9.dp))
        Column {
            Text(
                propItem.prjName ?: "",
                modifier = Modifier,
                color = textColorDark,
                fontSize = 14.sp,
                lineHeight = 22.sp,
                maxLines = 1,
                fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
            )

            Text(
                address(propItem),
                modifier = Modifier,
                color = textColorLight,
                fontSize = 14.sp,
                lineHeight = 22.sp,
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
            )
        }

    }
}

private fun address(propItem: SiteVisitFlowData.SvProjectItem): String {
    var detail = ""
    if (!TextUtils.isEmpty(propItem.ltName) && !"null".equals(propItem.ltName, true)) {
        detail = propItem.ltName ?: ""
    }
    if (!TextUtils.isEmpty(propItem.prjCity)) {
        detail += ", " + propItem.prjCity
    }
    return detail
}

@Preview
@Composable
fun TestDrop(modifier: Modifier = Modifier) {
    SvExpandablePropCardWidget(
        Modifier
            .fillMaxWidth()
            .padding(8.dp), propList = listOf(
            getDummyProjectItem(), getDummyProjectItem()
        )
    )
}

private fun getDummyProjectItem(): SiteVisitFlowData.SvProjectItem {
    return SiteVisitFlowData.SvProjectItem(
        prjName = "Mb Project 1",
        prjCity = "Noida",
        ltName = "Noida Extension",
        price = "2.01Cr", propType = "2BHK", area = "1890 sqft",
        possStatusD = "Dec'25"
    )
}