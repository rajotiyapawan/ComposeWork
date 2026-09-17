package com.magicbricks.prime.grid.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.magicbricks.prime.grid.presentation.contract.PrimeGridEffect
import com.magicbricks.prime.grid.presentation.contract.PrimeGridIntent
import com.magicbricks.prime.grid.presentation.contract.PrimeGridState
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.LoaderUI
import com.rajotiya.mytestapp.utility.getFont
import com.rajotiya.mytestapp.utility.getFontFamily
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun PrimeGridScreen(
    state: PrimeGridState,
    onIntent: (PrimeGridIntent) -> Unit,
    effectFlow: SharedFlow<PrimeGridEffect>,
    onEffect: (PrimeGridEffect) -> Unit
) {
    LaunchedEffect(Unit) {
        onIntent(PrimeGridIntent.FetchInitialData)
    }

    LaunchedEffect(Unit) {
        effectFlow.collect { effect ->
            onEffect(effect)
        }
    }

    if (state.isLoading) {
        LoaderUI()
        return
    }

    val primaryBg = Color(0xFF121212)
    val accentOrange = Color(0xFFFA8000)
    val cardBg = Color(0xFF1E1E1E)
    val selectedCardBg = Color(0xFF1E293B)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryBg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 90.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Top App Bar / Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onIntent(PrimeGridIntent.BackClicked) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Welcome to MB Prime",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Subtitle
            Text(
                text = /*state.primePackageResponse?.landingBannerSubTitle ?:*/ "Buy home DIRECTLY from an Owner & SAVE",
                color = Color(0xFFA2A5A8),
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Transparency Promise Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Transparency Promise",
                        color = Color(0xFF303030),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Know More",
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            onIntent(PrimeGridIntent.ShowTransparencyPromise)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Non-NRI / NRI Package List Section (Matching cl_button_package_0, 1, 2)
            val packageList = state.primePackageResponse?.packageDetails?.packageList
            if (!packageList.isNullOrEmpty()) {
                Text(
                    text = "Select Package",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    packageList.forEach { packageItem ->
                        val isSelected = state.selectedPackageItem?.packageID == packageItem.packageID
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSelected) selectedCardBg else cardBg)
                                .border(
                                    width = if (isSelected) 1.5.dp else 1.dp,
                                    color = if (isSelected) accentOrange else Color(0xFF333333),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable { onIntent(PrimeGridIntent.SelectPackage(packageItem)) }
                                .padding(12.dp)
                        ) {
                            Column {
                                if (/*packageItem.isBestSeller*/true) {
                                    Text(
                                        text = "BEST SELLER",
                                        color = Color.White,
                                        fontSize = 8.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .background(Color.Red, RoundedCornerShape(4.dp))
                                            .padding(horizontal = 4.dp, vertical = 2.dp)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                }

                                Text(
                                    text = packageItem.packageName ?: "Package",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "₹ ${packageItem.price}",
                                    color = accentOrange,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                if (/*packageItem.offrePrice > 0 && packageItem.offrePrice != packageItem.price*/true) {
                                    Text(
                                        text = "₹ ",
                                        color = Color.Gray,
                                        fontSize = 10.sp,
                                        textDecoration = TextDecoration.LineThrough
                                    )
                                }

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = /*packageItem.contactUnits ?: "${packageItem.totalContactCount ?: 0} Contacts"*/"",
                                    color = Color.LightGray,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Additional details or benefits from response
            state.primePackageResponse?.subHeading?.let { subHeading ->
                if (subHeading.isNotEmpty()) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = cardBg)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Why MB Prime?",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = subHeading,
                                color = Color.LightGray,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }

        // Bottom Sticky Action Bar (matching property_view / llPackageBuyNowBottom)
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(primaryBg)
                .padding(16.dp)
        ) {
            state.selectedPackageItem?.let { selected ->
                Button(
                    onClick = { onIntent(PrimeGridIntent.ProceedToPayment(selected)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = accentOrange)
                ) {
                    Text(
                        text = "Upgrade to ${selected.packageName ?: "Prime"} - ₹ ${selected.price}",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun MBPrimeLandingScreen(
    modifier: Modifier = Modifier,

    // State
    showRetargetStrip: Boolean = false,
    showRetargetTitle: Boolean = false,
    showExclusiveForAgent: Boolean = false,
    showGrowBusinessBanner: Boolean = false,
    showPrimeTopViews: Boolean = false,
    showTransparencyPromise: Boolean = false,

    showHowToUse: Boolean = false,
    showPrimeVideoBanner: Boolean = false,
    showVideoGrid: Boolean = false,
    showRequestVisitVideo: Boolean = false,
    showPartnerOffer: Boolean = false,
    showOffers: Boolean = false,
    showFaq: Boolean = false,
    showHelpLine: Boolean = false,
    showRequestCallback: Boolean = true,
    showHearFromMembers: Boolean = false,

    showPropertyView: Boolean = true,
    showNriPackages: Boolean = false,

    showWhatsApp: Boolean = true,
    showFreePlan: Boolean = true,

    isLoading: Boolean = false,

    heading: String = "Welcome to MB Prime",
    subtitle: String = "Buy home DIRECTLY from an Owner & SAVE",

    retargetText: String = "Wohoo! Save more with Extra 5% OFF",

    onBackClick: () -> Unit = {},
    onCloseClick: () -> Unit = {},
    onKnowMoreClick: () -> Unit = {},
    onRequestCallbackClick: () -> Unit = {},
    onWhatsAppClick: () -> Unit = {},
    onContinueClick: () -> Unit = {},
    onFreePlanClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF000000))
    ) {

        /*
         * Main scrollable content
         */
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF000000)),
            contentPadding = PaddingValues(
                bottom = if (showPropertyView) 180.dp else 24.dp
            )
        ) {

            /*
             * Header
             */
            item {
                PrimeHeader(
                    heading = heading,
                    subtitle = subtitle,

                    showRetargetStrip = showRetargetStrip,
                    showRetargetTitle = showRetargetTitle,
                    showExclusiveForAgent = showExclusiveForAgent,
                    showGrowBusinessBanner = showGrowBusinessBanner,
                    showPrimeTopViews = showPrimeTopViews,
                    showTransparencyPromise = showTransparencyPromise,

                    retargetText = retargetText,

                    onBackClick = onBackClick,
                    onCloseClick = onCloseClick,
                    onKnowMoreClick = onKnowMoreClick
                )
            }

            /*
             * Top category / benefits
             */
            item {
                if (showPrimeTopViews) {
//                    PrimeTopBenefits()
                }
            }

            /*
             * How to use
             */
            item {
                if (showHowToUse) {
//                    WidgetPrimeUseInstrCompose(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 64.dp)
//                    )
                }
            }

            /*
             * Prime video banner
             */
            item {
                if (showPrimeVideoBanner) {
                    Image(
                        painter = painterResource(
                            R.drawable.ic_banner_prime_video
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 10.dp
                            )
                            .wrapContentHeight(),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }

            /*
             * Video RecyclerView replacement
             *
             * RecyclerView -> LazyRow / LazyColumn
             */
            item {
                if (showVideoGrid) {
//                    PrimeVideoGrid(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(
//                                start = 16.dp,
//                                top = 10.dp
//                            )
//                    )
                }
            }

            /*
             * Request visit videos
             *
             * HorizontalScrollView -> LazyRow
             */
            item {
                if (showRequestVisitVideo) {
                    PrimeRequestVisitVideos()
                }
            }

            /*
             * WhatsApp
             */
            item {
//                LayoutPrimeWhatsAppCompose(
//                    modifier = Modifier.fillMaxWidth()
//                )
            }

            /*
             * Partner offer
             */
            item {
                if (showPartnerOffer) {
//                    WidgetPrimePartnerOfferCompose(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 40.dp)
//                    )
                }
            }

            /*
             * Offers
             */
            item {
                if (showOffers) {
//                    WidgetPrimeOfferCompose(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 8.dp)
//                    )
                }
            }

            /*
             * FAQ
             */
            item {
                if (showFaq) {
//                    WidgetPrimeFaqCompose(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 64.dp)
//                    )
                }
            }

            /*
             * Help line
             */
            item {
                if (showHelpLine) {
                    PrimeHelpLine(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 48.dp
                            )
                    )
                }
            }

            /*
             * Request callback
             */
            item {
                if (showRequestCallback) {
                    PrimeRequestCallback(
                        showWhatsApp = showWhatsApp,
                        onRequestCallbackClick = onRequestCallbackClick,
                        onWhatsAppClick = onWhatsAppClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 48.dp
                            )
                    )
                }
            }

            /*
             * Hear from members
             */
            item {
                if (showHearFromMembers) {
//                    WidgetPrimeHearFromMembersCompose(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 64.dp)
//                    )
                }
            }

            /*
             * Existing Compose content
             *
             * This was already a ComposeView in XML.
             */
            item {
                TcRadioButtonCompose(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 22.dp,
                            bottom = 22.dp
                        )
                )
            }
        }

        /*
         * Fixed bottom purchase section
         */
        if (showPropertyView) {
            PrimeBottomPurchaseBar(
                showNriPackages = showNriPackages,
                showFreePlan = showFreePlan,

                onContinueClick = onContinueClick,
                onFreePlanClick = onFreePlanClick,

                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            )
        }

        /*
         * Loading overlay
         */
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .zIndex(10f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

private var tcRecommend = ""

@Composable
fun TcRadioButtonCompose(modifier: Modifier) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf("") }
    TCRadioButton(selectedOption, onOptionSelected)
    tcRecommend = if (selectedOption == "Yes") {
        "AssistedTC_"
    } else ""
}

@Composable
fun TCRadioButton(selectedOption: String, onOptionSelected: ((String) -> Unit)) {
    val radioOptions = listOf("No", "Yes")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xfff5f5f5), RoundedCornerShape(8.dp))
            .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 12.dp)
    ) {
        Text(
            text = "Were you assisted by a Tele-executive?",
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
            color = textColorDark,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = {
                                onOptionSelected(text)
                            }
                        )
                        .padding(end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(
                                0xff009681
                            )
                        )
                    )
                    Text(
                        text = text,
                        modifier = Modifier.padding(start = 8.dp),
                        fontFamily = getFontFamily(),
                        color = textColorDark,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun PrimeHeader(
    heading: String,
    subtitle: String,

    showRetargetStrip: Boolean,
    showRetargetTitle: Boolean,
    showExclusiveForAgent: Boolean,
    showGrowBusinessBanner: Boolean,
    showPrimeTopViews: Boolean,
    showTransparencyPromise: Boolean,

    retargetText: String,

    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    onKnowMoreClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF000000))
    ) {

        /*
         * Top row
         */
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {

            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 6.dp, top = 10.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.back_arrow),
                    contentDescription = "Back",
                    tint = Color(0xFF303030)
                )
            }

            if (showRetargetStrip) {
                Row(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 12.dp)
                        .background(
                            color = Color(0x99000000),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(
                            horizontal = 10.dp,
                            vertical = 8.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(
                            R.drawable.ic_prime_red_gift
                        ),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp)
                    )

                    Text(
                        text = retargetText,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontFamily = getFontFamily(weight = FontWeight.SemiBold)
                    )
                }
            }

            if (showRetargetStrip.not()) {
                // Equivalent to the XML gone state.
            }

            /*
             * Close button
             */
            IconButton(
                onClick = onCloseClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 6.dp, top = 10.dp)
            ) {
                Icon(
                    painter = painterResource(
                        R.drawable.ic_cross_white_24dp
                    ),
                    contentDescription = "Close"
                )
            }
        }

        /*
         * Crown + title
         */
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(
                    R.drawable.ic_mb_prime
                ),
                contentDescription = null
            )

            if (showRetargetTitle) {
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = stringResource(
                        R.string.retarget_mb_prime
                    ),
                    color = Color(0xFFFFFFFF),
                    fontSize = 18.sp,
                    fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                    lineHeight = 20.sp
                )
            }
        }

        /*
         * Exclusive for agent
         */
        if (showExclusiveForAgent) {
            Image(
                painter = painterResource(
                    R.drawable.exclusively_for_agen_2
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )
        }

        /*
         * Heading
         */
        Text(
            text = heading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = getFontFamily(font = "Roboto", weight = FontWeight.Bold)
        )

        /*
         * Subtitle
         */
        Text(
            text = subtitle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 4.dp,
                    bottom = 36.dp
                ),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 11.sp,
            fontFamily = getFontFamily()
        )

        /*
         * Grow your business banner
         */
        if (showGrowBusinessBanner) {
            GrowYourBusinessBanner()
        }

        /*
         * Transparency promise
         */
        if (showTransparencyPromise) {
            TransparencyPromise(
                onKnowMoreClick = onKnowMoreClick
            )
        }
    }
}

@Composable
private fun GrowYourBusinessBanner() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 18.dp,
                bottom = 18.dp
            )
            .background(
                Color.White,
                RoundedCornerShape(28.dp)
            )
            .padding(
                top = 8.dp,
                bottom = 8.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Unlock Upto 150 Owner Contacts",
            fontSize = 14.sp,
            color = Color(0xFF303030),
            fontFamily = getFontFamily()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Grow Your Business",
                fontSize = 14.sp,
                color = Color(0xFF009681),
                fontFamily = getFontFamily()
            )

            Spacer(modifier = Modifier.width(4.dp))

            Icon(
                painter = painterResource(
                    R.drawable.grow_ur_business_icon
                ),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
private fun TransparencyPromise(
    onKnowMoreClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 40.dp,
                end = 40.dp,
                bottom = 50.dp
            )
            .height(30.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFFFEBEE)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(
                R.drawable.prime_red_hand_shake
            ),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .offset(x = (-15).dp)
        )

        Text(
            text = stringResource(
                R.string.prime_grid_transparency_promise
            ),
            modifier = Modifier.weight(1f),
            color = Color(0xFF303030),
            fontSize = 11.sp,
            fontFamily = getFontFamily(weight = FontWeight.SemiBold)
        )

        Text(
            text = stringResource(
                R.string.prime_grid_know_more
            ),
            modifier = Modifier
                .clickable(onClick = onKnowMoreClick)
                .padding(6.dp),
            color = Color.Red,
            fontSize = 12.sp,
            fontFamily = getFontFamily(weight = FontWeight.SemiBold)
        )
    }
}

@Composable
private fun PrimeRequestVisitVideos() {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        contentPadding = PaddingValues(start = 16.dp)
    ) {

        item {
            Image(
                painter = painterResource(
                    R.drawable.sitevisit_video_image
                ),
                contentDescription = null,
                modifier = Modifier.padding(end = 16.dp)
            )
        }

        item {
            Image(
                painter = painterResource(
                    R.drawable.helpus_video_image
                ),
                contentDescription = null,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}

@Composable
private fun PrimeHelpLine(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(
                Color(0xFF171E26),
                RoundedCornerShape(4.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(
                R.drawable.ic_prime_bitmap_chat
            ),
            contentDescription = null,
            modifier = Modifier.size(45.dp)
        )

        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {

            Text(
                text = "Still got questions?",
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = getFontFamily(font = "Roboto", weight = FontWeight.Medium)
            )

            Text(
                text = "Contact us at +91-120330292",
                modifier = Modifier.padding(top = 1.dp),
                color = Color.White,
                fontSize = 12.sp,
                fontFamily = getFontFamily(font = "Roboto")
            )
        }
    }
}

@Composable
private fun PrimeRequestCallback(
    showWhatsApp: Boolean,
    onRequestCallbackClick: () -> Unit,
    onWhatsAppClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color(0xFF303030),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.Top
        ) {

            Image(
                painter = painterResource(
                    R.drawable.ic_prime_bitmap_chat
                ),
                contentDescription = null,
                modifier = Modifier.size(45.dp)
            )

            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {

                Text(
                    text = "For queries or assistance",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = getFontFamily(),
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Call our expert on",
                        color = Color.White.copy(alpha = .4f),
                        fontSize = 12.sp,
                        fontFamily = getFontFamily(weight = FontWeight.Medium)
                    )

                    Spacer(
                        modifier = Modifier.width(4.dp)
                    )

                    Text(
                        text = "7303439363",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = getFontFamily(weight = FontWeight.Bold)
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            Button(
                onClick = onRequestCallbackClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                ),
                shape = RoundedCornerShape(2.dp)
            ) {
                Text(
                    text = "Request a Callback",
                    fontSize = 12.sp
                )
            }

            if (showWhatsApp) {

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                OutlinedButton(
                    onClick = onWhatsAppClick,
                    shape = RoundedCornerShape(4.dp)
                ) {

                    Image(
                        painter = painterResource(
                            R.drawable.whats_app_logo
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(
                        modifier = Modifier.width(5.dp)
                    )

                    Text(
                        text = "Chat instantly",
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun PrimeBottomPurchaseBar(
    showNriPackages: Boolean,
    showFreePlan: Boolean,
    onContinueClick: () -> Unit,
    onFreePlanClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = Color.Black,
        shadowElevation = 20.dp
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            if (showNriPackages) {
                NriPackages()
            } else {
                NonNriPackages()
            }

            /*
             * Continue button
             */
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 14.dp,
                        bottom = 10.dp
                    )
                    .height(40.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Red)
                    .clickable {
                        onContinueClick()
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Upgrade To Silver",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = getFontFamily(weight = FontWeight.Bold)
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Icon(
                    painter = painterResource(
                        R.drawable.ic_arrow_black_right
                    ),
                    contentDescription = null,
                    tint = Color.White
                )
            }

            /*
             * Free plan
             */
            if (showFreePlan) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                            bottom = 18.dp
                        )
                        .height(38.dp)
                        .border(
                            1.dp,
                            Color(0xFF909090),
                            RoundedCornerShape(4.dp)
                        )
                        .clickable {
                            onFreePlanClick()
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "Continue with Free Plan",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = getFontFamily(weight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}

@Composable
private fun NonNriPackages() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 5.dp,
                end = 8.dp
            )
    ) {

        PrimePackageCard(
            modifier = Modifier.weight(1f),
            name = "Basic",
            price = "₹2399",
            actualPrice = "Price",
            discount = "50% Off",
            selected = false
        )

        Spacer(modifier = Modifier.width(8.dp))

        PrimePackageCard(
            modifier = Modifier.weight(1f),
            name = "Silver",
            price = "₹2399",
            actualPrice = "Price",
            discount = "50% Off",
            selected = true
        )

        Spacer(modifier = Modifier.width(8.dp))

        PrimePackageCard(
            modifier = Modifier.weight(1f),
            name = "Gold",
            price = "₹14999",
            actualPrice = "₹14999",
            discount = "50% Off",
            selected = false
        )
    }
}

@Composable
private fun PrimePackageCard(
    modifier: Modifier = Modifier,
    name: String,
    price: String,
    actualPrice: String,
    discount: String,
    selected: Boolean
) {
    Column(
        modifier = modifier
            .padding(top = 12.dp)
            .border(
                width = 1.dp,
                color = if (selected) {
                    Color(0xFF2196F3)
                } else {
                    Color(0xFF909090)
                },
                shape = RoundedCornerShape(4.dp)
            )
            .padding(
                start = 8.dp,
                end = 8.dp,
                bottom = 14.dp
            )
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {

            Box(
                modifier = Modifier
                    .background(
                        if (selected) {
                            Color(0xFF90CAF9)
                        } else {
                            Color(0xFFE0E0E0)
                        },
                        RoundedCornerShape(4.dp)
                    )
                    .padding(
                        horizontal = 7.dp,
                        vertical = 3.dp
                    )
            ) {

                Text(
                    text = name.uppercase(),
                    fontSize = 10.sp,
                    fontFamily = getFontFamily(weight = FontWeight.Bold),
                    color = Color.Black
                )
            }

            /*
             * Tick
             */
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .clip(CircleShape)
                    .background(
                        if (selected) {
                            Color(0xFF2196F3)
                        } else {
                            Color(0xFFF5F5F5)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {

                if (selected) {
                    Icon(
                        painter = painterResource(
                            R.drawable.tick_white_coachmark
                        ),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.padding(3.dp)
                    )
                }
            }
        }

        Text(
            text = price,
            modifier = Modifier.padding(
                top = 8.dp,
                start = 2.dp
            ),
            fontSize = 14.sp,
            fontFamily = getFontFamily(weight = FontWeight.Bold),
            color = if (selected) {
                Color(0xFF2196F3)
            } else {
                Color.White
            }
        )

        Row(
            modifier = Modifier.padding(start = 2.dp)
        ) {

            Text(
                text = actualPrice,
                fontSize = 9.sp,
                color = Color(0xFF909090)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = discount,
                fontSize = 10.sp,
                color = Color.Red
            )
        }
    }
}

@Composable
private fun NriPackages() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 5.dp,
                end = 10.dp
            )
    ) {

//        NriPackageCard(
//            modifier = Modifier.weight(1f),
//            name = "Basic",
//            price = "₹2399",
//            days = "/45 Days",
//            selected = false
//        )

        Spacer(modifier = Modifier.width(5.dp))

//        NriPackageCard(
//            modifier = Modifier.weight(1f),
//            name = "Silver",
//            price = "₹2399",
//            days = "/45 Days",
//            selected = true
//        )
    }
}


