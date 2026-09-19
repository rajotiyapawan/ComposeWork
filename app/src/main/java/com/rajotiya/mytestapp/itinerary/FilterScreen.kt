package com.rajotiya.mytestapp.itinerary

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbGreen
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.utility.getFontFamily
import com.rajotiya.mytestapp.utility.noRippleClick

@Preview
@Composable
private fun FilterScreenTest() {
    val filters = listOf(
        Filters(
            "Price Range", listOf(
                ItineraryFilter("Any price", "Any price"),
                ItineraryFilter("Under ₹1.8 Cr", "Under ₹1.8 Cr"),
                ItineraryFilter("₹1.8 – 2 Cr", "₹1.8 – 2 Cr"),
                ItineraryFilter("₹2 Cr+", "₹2 Cr+"),
            )
        ),
        Filters(
            "Max drive time", listOf(
                ItineraryFilter("Any", "Any"),
                ItineraryFilter("10 min", "10 min"),
                ItineraryFilter("15 min", "15 min"),
                ItineraryFilter("20 min", "20 min"),
                ItineraryFilter("30 min", "30 min"),
            )
        ),
    )
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
            Box(
                Modifier
                    .noRippleClick {}
                    .padding(8.dp), contentAlignment = Alignment.Center) {
                Icon(Icons.Outlined.Cancel, contentDescription = null, modifier = Modifier.size(17.dp))
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 18.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Filters", color = textColorDark, fontFamily = getFontFamily(weight = FontWeight.SemiBold), fontSize = 16.sp, lineHeight = 20.sp)
            Text("Reset", color = mbRed, fontFamily = getFontFamily(), fontSize = 12.sp, lineHeight = 16.sp)
        }
        Spacer(Modifier.height(10.dp))
        Column(Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)) {
            filters.forEach {
                val (selected, onSelected) = remember { mutableStateOf("") }
                Text(it.type.uppercase(), fontSize = 10.sp, lineHeight = 15.sp, fontFamily = getFontFamily(), color = textColorDark)
                FlowRow(Modifier.fillMaxWidth().padding(top = 11.dp, bottom = 18.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    it.list?.forEach { item ->
                        val borderColor = if (selected==item.name) mbGreen else Color(0xffd7d7d7)
                        val bgColor = if (selected==item.name) Color(0xffe0f2f0) else Color(0xfff5f5f5)
                        Box(Modifier
                            .noRippleClick{onSelected(item.name)}
                            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(50))
                            .background(color = bgColor, shape = RoundedCornerShape(50))
                            .padding(vertical = 9.dp, horizontal = 11.dp)
                        ) {
                            Text(
                                item.name,
                                color = textColorDark,
                                fontFamily = getFontFamily(),
                                fontSize = 12.sp, lineHeight = 16.sp,
                                modifier = Modifier.noRippleClick {})
                        }
                    }
                }
            }
        }
        Spacer(Modifier.weight(1f))
        Box(
            Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .background(color = mbRed, shape = RoundedCornerShape(50))
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Show 4 projects", color = Color.White, fontFamily = getFontFamily(weight = FontWeight.SemiBold), fontSize = 14.sp, lineHeight = 18.sp)
        }
    }
}

data class Filters(
    val type: String,
    val list: List<ItineraryFilter>?
)