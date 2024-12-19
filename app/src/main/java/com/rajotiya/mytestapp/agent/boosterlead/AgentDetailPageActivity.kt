package com.rajotiya.mytestapp.agent.boosterlead

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.aob_revamp.utils.noRippleClick
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFont

class AgentDetailPageActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgentProfileCard()
        }
    }

}

@Preview(showBackground = true)
@Composable
fun AgentProfileCard() {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(state = rememberScrollState())
    ) {

        DatePicker()

        // Header Section
        HeaderSection(modifier = Modifier.fillMaxWidth())

        LegalDataSection(
            modifier = Modifier
                .fillMaxWidth()
                .noRippleClick { showDialog = true }
                .padding(top = 24.dp)
        )

        SalesDetails(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        ContactSection(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFE8E8E8))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )

        AboutDreamHouse(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 20.dp, bottom = 20.dp, end = 12.dp)
        )

        MeetTheTeam(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 20.dp, bottom = 10.dp)
        )

        PropertiesView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, bottom = 12.dp)
        )
        PropertiesView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, bottom = 24.dp)
        )
    }

    if (showDialog) {
        ScheduleMeeting(modifier = Modifier.fillMaxWidth()) {
            showDialog = false
        }
    }
}

@Composable
fun HeaderSection(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
                brush = Brush.verticalGradient(colors = listOf(Color(0xff001520), Color(0xff2d0000)))
            )
            .padding(start = 24.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 9.dp)
                    .padding(end = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "magicbricks",
                    color = Color.White,
                    fontSize = 21.sp,
                )
                Image(painter = painterResource(id = R.drawable.ic_preferred_agent), contentDescription = null)
            }
            Spacer(modifier = Modifier.height(18.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 15.dp), verticalAlignment = Alignment.Top
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_aob_shared_flat_unselected), // Replace with actual image resource
                    contentDescription = "Profile Picture", modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Adil Sheraz",
                        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        color = Color.White
                    )
                    Text(
                        text = "The Dream House",
                        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        color = Color.White
                    )
                    HorizontalDivider(modifier = Modifier.padding(end = 20.dp, top = 3.dp), thickness = 1.dp, color = textColorLight)
                    Text(
                        text = "Operating since: 1994",
                        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun LegalDataSection(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BadgeWithText(modifier = Modifier.weight(1f),"RERA ID\nB39e4812hs", R.drawable.rera_registered_webp)
        BadgeWithText(modifier = Modifier.weight(1f),"GST No\nB39e4812hs", R.drawable.gst_webp)
        BadgeWithText(modifier = Modifier.weight(1f),"Among Top 2% of\nCRISIL Verified\nAgents", R.drawable.ic_cirisil_verified)
    }
}

@Composable
fun SalesDetails(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(color = Color(0xFFD7D7D7))
            .padding(horizontal = 20.dp)
            .padding(bottom = 24.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp, bottom = 12.dp)
        ) {
            PropertyInfoItem("10", "Properties\nFor Sale")
            PropertyInfoItem("70", "Properties\nFor Rent")
            PropertyInfoItem("2", "Team\nMembers")
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFF5F5F5))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Deals in",
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
            fontSize = 12.sp,
            color = textColorDark,
            lineHeight = 18.sp
        )
        Text(
            text = "Rent/Lease, Pre-launch, Original Booking, Resale, Others",
            color = textColorDark,
            lineHeight = 18.sp,
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Operates Section
        Text(
            text = "Operates in",
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
            fontSize = 12.sp,
            color = textColorDark,
            lineHeight = 18.sp
        )
        Text(
            text = "Panchsheel Park, Connaught Place, Shanti Niketan, Vasant Vihar, Defence Colony, Safdarjung Enclave, More",
            color = textColorDark,
            lineHeight = 18.sp,
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
            fontSize = 12.sp
        )
    }
}

@Composable
fun ContactSection(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Box(modifier = Modifier
            .weight(1f)
            .noRippleClick { }
            .border(color = mbRed, width = 1.dp, shape = RoundedCornerShape(50))
            .background(color = Color.White, shape = RoundedCornerShape(50)), contentAlignment = Alignment.Center) {
            Text(
                text = "Save in Phonebook",
                color = mbRed,
                fontSize = 14.sp,
                lineHeight = 30.sp,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Box(modifier = Modifier
            .weight(1f)
            .noRippleClick { }
            .background(color = mbRed, shape = RoundedCornerShape(50)),
            contentAlignment = Alignment.Center) {
            Text(
                text = "Contact Agent",
                color = Color.White,
                fontSize = 14.sp,
                lineHeight = 30.sp,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
            )
        }
    }
}

@Composable
fun AboutDreamHouse(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "About The Dream House",
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
            fontSize = 20.sp,
            color = textColorDark
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "We are property consultants in New Delhi since 1994. We are expertise in Best Negotiator for Buyers & renting of residential in New Delhi Our experience enables us to respond to client needs in most efficient and effective manner.  ",
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
            fontSize = 10.sp,
            lineHeight = 16.sp,
            color = textColorDark
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Text(
                modifier = Modifier.weight(.25F),
                text = "Team Size",
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
                fontSize = 10.sp,
                lineHeight = 16.sp,
                color = textColorDark
            )
            Text(
                modifier = Modifier.weight(.75F),
                text = "2 people",
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
                fontSize = 10.sp,
                lineHeight = 16.sp,
                color = textColorDark
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Text(
                modifier = Modifier.weight(.25F),
                text = "Address",
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
                fontSize = 10.sp,
                lineHeight = 16.sp,
                color = textColorDark
            )
            Text(
                modifier = Modifier.weight(.75F),
                text = "G-25, Vasant Enclave, Vasant Vihar, New Delhi -\n" + "110057",
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
                fontSize = 10.sp,
                lineHeight = 16.sp,
                color = textColorDark
            )
        }
    }
}

@Composable
fun MeetTheTeam(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Meet the Team",
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
            fontSize = 20.sp,
            color = textColorDark
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(5) {
                Box(
                    modifier = Modifier
                        .size(127.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = Color.Gray)
                )
            }
        }
    }
}

@Composable
fun PropertiesView(modifier: Modifier = Modifier) {
    Column(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Properties for sale",
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                fontSize = 20.sp,
                color = textColorDark
            )
            Text(
                text = "See All", fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)), fontSize = 14.sp, color = mbRed
            )
        }
        LazyRow(modifier = Modifier.padding(top = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(5) {
                PropertyCardView(
                    modifier = Modifier
                        .height(320.dp)
                        .width(215.dp)
                        .border(width = 1.dp, color = Color(0xFFD7D7D7), shape = RoundedCornerShape(8.dp))
                )
            }
        }
    }
}

@Composable
fun PropertyCardView(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(132.dp)
                .clip(shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "property image",
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .padding(start = 15.dp, top = 11.dp)
        ) {
            Text(
                text = "Ireo The Corridors",
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                fontSize = 16.sp,
                color = textColorDark
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Noida Extension",
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
                fontSize = 14.sp,
                color = textColorLight
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "3, 4 BHK Flats",
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
                fontSize = 14.sp,
                color = textColorLight
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                            fontSize = 14.sp,
                            color = textColorDark
                        )
                    ) {
                        append("₹64.5 Lac")
                    }
                    append(" onwards")
                }, fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)), fontSize = 12.sp, color = textColorLight
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Contact Agent",
                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                    fontSize = 14.sp,
                    color = mbRed
                )
                Spacer(modifier = Modifier.height(6.dp))
                Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowForward, contentDescription = null, tint = mbRed)
            }
        }
    }
}

@Composable
fun BadgeWithText(modifier: Modifier,text: String, imageId: Int) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = imageId), // Replace with actual badge icons
            contentDescription = null, modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            color = textColorDark,
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR))
        )
    }
}

@Composable
fun PropertyInfoItem(count: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = count, fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)), fontSize = 24.sp, color = textColorDark
        )
        Text(
            text = label,
            color = textColorDark,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR))
        )
    }
}

