package com.rajotiya.mytestapp.primedashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorExtraLight
import com.rajotiya.mytestapp.utility.ComposeUrlImage
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFont
import com.rajotiya.mytestapp.utility.getFontFamily
import com.rajotiya.mytestapp.utility.noRippleClick

/**
 * Created by Pawan Rajotiya on 30-01-2026.
 */

@Preview
@Composable
fun PrimeDashboardTest(modifier: Modifier = Modifier) {
    Column(
        Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        PrimeDashboardPartnerOffers()
        Spacer(Modifier.height(16.dp))
        PrimePlusBenefitCard(Modifier.fillMaxWidth()) {}
        Spacer(Modifier.height(16.dp))
        PrimeDashboardVerticalPropertyCard(onItemCtaClick = {}) { }
        PrimeDashboardFAQs(
            Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        )
    }
}

@Composable
fun PrimeDashboardPartnerOffers(modifier: Modifier = Modifier) {
    Column(modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "Offers Unlocked",
                fontSize = 24.sp,
                color = textColorDark,
                fontFamily = FontFamily(getFont(Constants.ROBOTO_BOLD))
            )
            Spacer(Modifier.width(10.dp))
            Image(
                painter = painterResource(R.drawable.ic_blue_gift_box),
                contentDescription = null,
                modifier = Modifier.width(32.dp)
            )
        }
        Box(
            Modifier
                .width(42.dp)
                .height(4.dp)
                .background(mbRed)
        )
        ServicesList(
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 4.dp)
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 25.dp, end = 16.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Great discounts only for you",
                color = textColorDark,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(getFont(Constants.ROBOTO_MEDIUM))
            )
            SeeAllCta { }
        }
        PartnerOfferList(
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 4.dp)
        )
    }
}

@Composable
fun ServicesList(modifier: Modifier = Modifier) {
    LazyRow(modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(5) {
            Card(
                Modifier.width(290.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                ComposeUrlImage(url = "https://picsum.photos/200/300", contentScale = ContentScale.FillWidth, modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun PartnerOfferList(modifier: Modifier = Modifier) {
    LazyRow(modifier, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        items(4) {
            Card(
                Modifier
                    .width(290.dp)
                    .height(180.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xffdff6f9)),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(Modifier.fillMaxWidth()
                    .padding(start = 16.dp, top = 12.dp, end = 12.dp, bottom = 10.dp)) {
                    Column(Modifier
                        .weight(1f)
                        .padding(end = 11.dp)) {
                        ComposeUrlImage(
                            url = "https://picsum.photos/200/300",
                            modifier = Modifier
                                .width(110.dp)
                                .height(35.dp),
                            contentScale = ContentScale.Fit
                        )
                        Spacer(Modifier.height(12.dp))
                        Text(
                            "Subscribe Now | Signup",
                            color = textColorDark,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(getFont(Constants.ROBOTO_BOLD))
                        )
                        Spacer(Modifier.height(11.dp))
                        Text(
                            "₹100 Off on Livpure Smart Subscription",
                            color = textColorDark,
                            fontSize = 12.sp,
                            fontFamily = FontFamily(getFont(Constants.ROBOTO))
                        )
                        Box(Modifier
                            .weight(1f)
                            .padding(top = 10.dp), contentAlignment = Alignment.CenterStart) {
                            Box(
                                Modifier
                                    .background(mbRed, shape = RoundedCornerShape(4.dp))
                                    .padding(horizontal = 15.dp, vertical = 6.dp), contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "Get Coupon Code", color = Color.White, fontSize = 11.sp, fontFamily = FontFamily(
                                        getFont(
                                            Constants.ROBOTO_BOLD
                                        )
                                    )
                                )
                            }
                        }
                    }
                    ComposeUrlImage(url = "https://picsum.photos/200/300", modifier = Modifier.width(80.dp), contentScale = ContentScale.Fit)
                }
            }
        }
    }
}

@Composable
fun PrimePlusBenefitCard(
    modifier: Modifier = Modifier,
    onViewAllBenefitsClick: () -> Unit,
) {
    Box(
        modifier
            .height(IntrinsicSize.Max)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.prime_dashboard_prime_plus_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_prime_crown),
                contentDescription = null,
                modifier = Modifier.size(22.dp)
            )
            Text(
                buildAnnotatedString {
                    append("Get discountOff OFF on ")
                    withStyle(style = SpanStyle(fontFamily = getFontFamily(weight = FontWeight.Bold))) {
                        append("Prime")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontFamily = getFontFamily(font = "Damion", weight = FontWeight.Normal),
                            color = Color(0xfffa8000),
                            fontSize = 21.sp
                        )
                    ) {
                        append("plus")
                    }
                    append(" Membership!")
                },
                modifier = Modifier.padding(top = 12.dp),
                fontFamily = getFontFamily(),
                fontSize = 14.sp,
                color = Color.White
            )
            Row(
                Modifier.padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "\u20B9 Original",
                    fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = textColorExtraLight,
                    textDecoration = TextDecoration.LineThrough
                )
                Text(
                    "\u20B9 Payable",
                    fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                    fontSize = 18.sp,
                    lineHeight = 20.sp,
                    color = Color(0xffd2d2d2)
                )
                Text(
                    "discount",
                    fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                    fontSize = 10.sp,
                    modifier = Modifier
                        .border(width = 1.dp, color = Color(0xffffcc33), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 5.dp, vertical = 2.dp),
                    color = Color(0xffffcc33)
                )
            }
            Text(
                "Benefit Heading",
                textAlign = TextAlign.Start,
                fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                fontSize = 10.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 24.dp),
                color = Color.White
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 12.dp)
            ) {
                repeat(5) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                    ) {
                        Image(painter = painterResource(R.drawable.ic_tick_green), contentDescription = null)
                        Spacer(Modifier.width(9.dp))
                        Text(
                            "Contact upto 35 Owners",
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            color = Color.White,
                            fontFamily = getFontFamily()
                        )
                    }
                }
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp)
                    .background(mbRed, shape = RoundedCornerShape(20.dp))
                    .padding(vertical = 11.dp), contentAlignment = Alignment.Center
            ) {
                Text(
                    "View All Benefits",
                    fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun PrimeDashboardVerticalPropertyCard(
    modifier: Modifier = Modifier, itemCtaText: String = "Contact Owner",
    onItemCtaClick: () -> Unit,
    onItemClick: () -> Unit
) {
    val price = "₹ 3 Cr"
    val area = "1000 sqft"

    Card(
        modifier = modifier
            .padding(top = 4.dp, bottom = 4.dp)
            .width(216.dp)
            .height(285.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .noRippleClick {
                    onItemClick()
                }
        ) {

            /** IMAGE SECTION **/
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.9f)
            ) {
                ComposeUrlImage(
                    url = "",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFF6F6F6))
                )
            }

            /** DETAILS SECTION **/
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.1f)
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 19.dp
                    )
            ) {

                Text(
                    text = "App Title",
                    fontSize = 14.sp, fontFamily = FontFamily(getFont(Constants.ROBOTO)),
                    color = Color(0xFF303030)
                )

                val priceArea = price + if (area.isNotEmpty()) " | $area" else ""
                Text(
                    text = priceArea,
                    modifier = Modifier.padding(top = 9.dp),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(getFont(Constants.ROBOTO_MEDIUM)),
                    color = Color(0xFF303030),
                    maxLines = 1
                )

                val localityText = "Sector 55, Noida"
                if (localityText.isNotEmpty()) {
                    Text(
                        text = localityText,
                        modifier = Modifier.padding(top = 6.dp),
                        fontSize = 14.sp,
                        color = Color(0xFF303030),
                        maxLines = 1, fontFamily = FontFamily(getFont(Constants.ROBOTO))
                    )
                }

                Spacer(modifier = Modifier.height(13.dp))

                Box(
                    modifier = Modifier
                        .heightIn(min = 30.dp)
                        .noRippleClick {
                            onItemCtaClick()
                        }
                        .background(
                            color = mbRed, shape = RoundedCornerShape(20.dp)
                        )
                        .padding(
                            horizontal = 18.dp,
                            vertical = 5.dp
                        )
                ) {
                    Text(
                        text = itemCtaText,
                        fontSize = 14.sp,
                        color = Color.White,
                        fontFamily = FontFamily(getFont(Constants.ROBOTO_MEDIUM))
                    )
                }
            }
        }
    }
}

@Composable
fun PrimeDashboardFAQs(modifier: Modifier = Modifier) {
    var showAll by remember { mutableStateOf(false) }
    Column(modifier) {
        Text(
            "Frequently Asked Questions", fontSize = 16.sp, lineHeight = 20.sp, fontFamily = FontFamily(
                getFont(
                    Constants.ROBOTO_BOLD
                )
            ), color = textColorDark
        )
        PrimeFAQs(
            Modifier
                .fillMaxWidth()
                .padding(top = 32.dp), showAll
        )
        Row(Modifier.padding(top = 18.dp, start = 16.dp, bottom = 16.dp)) {
            SeeAllCta(ctaText = if (showAll) "See less" else "See all") { showAll = !showAll }
        }
    }
}

@Composable
fun PrimeFAQs(modifier: Modifier = Modifier, showAll: Boolean) {
    LazyColumn(
        modifier.heightIn(max = 1200.dp),
        contentPadding = PaddingValues(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        val count = if (showAll) 12 else 4
        items(count) { index ->
            PrimeFaqItem()
            if (index < count - 1) {
                HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = Color(0xffe6e6e6))
            }
        }
    }
}

@Composable
fun PrimeFaqItem(modifier: Modifier = Modifier) {
    var toggleOpen by remember { mutableStateOf(false) }
    Column(modifier) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                "What is MB Prime?",
                fontSize = 14.sp,
                maxLines = 2,
                color = textColorDark,
                fontFamily = FontFamily(getFont(Constants.ROBOTO))
            )
            Icon(
                if (toggleOpen) Icons.Outlined.KeyboardArrowUp else Icons.Outlined.KeyboardArrowDown,
                contentDescription = null, modifier = Modifier
                    .noRippleClick { toggleOpen = !toggleOpen }
                    .padding(4.dp)
                    .size(20.dp))
        }
        if (toggleOpen) {
            FaqItemStep(Modifier.padding(top = 8.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 2.dp)
            ) {
                FaqItemStep(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
                FaqItemStep(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
                FaqItemStep(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
                FaqItemStep(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
                FaqItemStep(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun FaqItemStep(modifier: Modifier = Modifier) {
    Text(
        "MB Prime is a membership programme that gives you exclusive access to properties posted",
        modifier = modifier,
        fontSize = 12.sp,
        color = textColorDark,
        fontFamily = FontFamily(getFont(Constants.ROBOTO_LIGHT))
    )
}

@Composable
fun SeeAllCta(
    ctaText: String = "See all",
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .noRippleClick { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = ctaText,
            fontFamily = FontFamily(getFont(Constants.ROBOTO_MEDIUM)),
            fontSize = 14.sp, lineHeight = 20.sp,
            color = mbRed
        )

        Spacer(modifier = Modifier.width(7.dp))

        Icon(
            Icons.AutoMirrored.Default.ArrowForward, tint = mbRed,
            contentDescription = null,
            modifier = Modifier.size(width = 12.dp, height = 11.dp)
        )
    }
}
