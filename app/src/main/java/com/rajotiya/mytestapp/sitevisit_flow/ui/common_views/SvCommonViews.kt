package com.rajotiya.mytestapp.sitevisit_flow.ui.common_views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorExtraLight
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.sitevisit_flow.domain.models.SiteVisitFlowData
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFontFamily
import com.rajotiya.mytestapp.utility.noRippleClick

/**
 * Created by Pawan Rajotiya on 12-03-2025.
 */

@Composable
fun SvCommonTopBar(modifier: Modifier = Modifier, onBack:()->Unit) {
    Box(
        modifier
            .padding(16.dp), contentAlignment = Alignment.CenterEnd
    ) {
        Box(Modifier.noRippleClick { onBack() }) {
            Text(
                "Skip",
                textDecoration = TextDecoration.Underline,
                color = textColorLight,
                fontSize = 14.sp, lineHeight = 24.sp, fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM)
            )
        }
    }
}

@Composable
fun SvCommonProjectItemView(modifier: Modifier = Modifier, projectItem: SiteVisitFlowData.SvProjectItem, backgroundColor: Color = Color.White) {
    Column(modifier=modifier
        .border(width = 1.dp, color = Color(0xffe8e8e8), shape = RoundedCornerShape(6.dp))
        .background(color = backgroundColor, shape = RoundedCornerShape(6.dp))
        .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            "${projectItem.prjName}, ${projectItem.prjCity}",
            fontSize = 12.sp,
            lineHeight = 18.sp,
            color = textColorDark,
            fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM)
        )
        Text(
            "₹${projectItem.price}   |   ${projectItem.propType}   |   ${projectItem.area}",
            fontSize = 12.sp,
            lineHeight = 18.sp,
            color = textColorExtraLight,
            fontFamily =
            getFontFamily
                (
                Constants
                    .MONTSERRAT_MEDIUM
            )
        )
        Text(
            "Possession by ${projectItem.possStatusD}",
            fontSize = 12.sp,
            lineHeight = 18.sp,
            color = textColorExtraLight,
            fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM)
        )
    }
}