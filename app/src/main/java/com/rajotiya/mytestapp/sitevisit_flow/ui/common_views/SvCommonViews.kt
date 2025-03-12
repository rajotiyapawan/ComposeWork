package com.rajotiya.mytestapp.sitevisit_flow.ui.common_views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
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