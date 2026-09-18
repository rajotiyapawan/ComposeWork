package com.rajotiya.mytestapp.itinerary

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbGold
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbGreen
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorExtraLight
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.aob_revamp.utils.noRippleClick
import com.rajotiya.mytestapp.utility.getFontFamily
import kotlinx.coroutines.launch

/**
 * Created by Pawan Rajotiya on 18-09-2026.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun AnchorCardsTest() {
    InlineBottomSheetLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        initialSheetState = InlineSheetState.EXPANDED,
        fixedBottomContent = { DiscoveryBottomView() },
        expandedContent = {
            Column {
                NearByProjectsSheetContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, bottom = 10.dp),
                    onFilterClick = { })
            }
        },
        collapsedContent = {
            Column {
                NearByProjectsSheetCollapsedHeader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, bottom = 10.dp),
                    onFilterClick = { })
            }
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(color = Color.Green), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(painterResource(R.drawable.frame_magic), contentDescription = null)
        }
    }
}

enum class InlineSheetState {
    /** Sheet is at its minimum collapsed height. */
    COLLAPSED,

    /** Sheet is at its maximum expanded height. */
    EXPANDED
}

private val SheetShape = RoundedCornerShape(
    topStart = 20.dp,
    topEnd = 20.dp
)

/**
 * A custom gesture-driven, inline bottom sheet layout that supports smooth transitions
 * between collapsed and expanded states, offscreen height measurement, sticky top/bottom sections,
 * and physics-based fling/velocity snap handling.
 *
 * @param modifier Modifier to be applied to the root container.
 * @param initialSheetState The starting state of the sheet (`COLLAPSED` or `EXPANDED`).
 * @param fixedTopContent Optional sticky content rendered above the collapsible body.
 * @param fixedBottomContent Optional sticky content rendered below the collapsible body.
 * @param collapsedContent Composable lambda rendered when the sheet is collapsed.
 * @param expandedContent Composable lambda rendered when the sheet is expanded.
 * @param mainContent Background layer content (e.g., maps, lists) over which the sheet floats.
 */
@Composable
fun InlineBottomSheetLayout(
    modifier: Modifier = Modifier,
    initialSheetState: InlineSheetState = InlineSheetState.COLLAPSED,
    fixedTopContent: (@Composable () -> Unit)? = null,
    fixedBottomContent: (@Composable () -> Unit)? = null,
    collapsedContent: @Composable ColumnScope.() -> Unit,
    expandedContent: @Composable ColumnScope.() -> Unit,
    mainContent: @Composable BoxScope.() -> Unit
) {
    val density = LocalDensity.current
    val scope = rememberCoroutineScope()

    // ----------------------------------------------------------
    // MEASURED HEIGHTS
    // ----------------------------------------------------------

    var collapsedContentHeight by remember {
        mutableIntStateOf(0)
    }

    var expandedContentHeight by remember {
        mutableIntStateOf(0)
    }

    var bottomViewHeight by remember {
        mutableIntStateOf(0)
    }

    var topViewHeight by remember {
        mutableIntStateOf(0)
    }

    val handleHeightPx = with(density) {
        24.dp.toPx()
    }

    /*
     * These are the actual two anchors.
     *
     * Handle + content
     */
    val collapsedHeightPx =
        handleHeightPx + collapsedContentHeight + bottomViewHeight + topViewHeight

    val expandedHeightPx =
        handleHeightPx + expandedContentHeight + bottomViewHeight + topViewHeight

    // ----------------------------------------------------------
    // STATE
    // ----------------------------------------------------------

    var sheetState by remember {
        mutableStateOf(initialSheetState)
    }

    /*
     * Current height while dragging.
     */
    val sheetHeight = remember {
        Animatable(0f)
    }

    /*
     * Once both layouts have been measured,
     * initialize the sheet to collapsed.
     */
    LaunchedEffect(
        collapsedHeightPx,
        expandedHeightPx
    ) {

        if (
            collapsedHeightPx > 0f &&
            expandedHeightPx >= collapsedHeightPx
        ) {

            sheetHeight.snapTo(
                when (sheetState) {
                    InlineSheetState.COLLAPSED -> collapsedHeightPx
                    InlineSheetState.EXPANDED -> expandedHeightPx
                }
            )
        }
    }

    val canDrag =
        collapsedHeightPx > 0f &&
                expandedHeightPx > collapsedHeightPx

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        // ======================================================
        // MAIN CONTENT
        // ======================================================

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            mainContent()
        }

        // ======================================================
        // BOTTOM SHEET
        // ======================================================

        if (sheetHeight.value > 0f) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        with(density) {
                            sheetHeight.value.toDp()
                        }
                    )
                    .align(Alignment.BottomCenter)
                    .shadow(
                        elevation = 8.dp,
                        shape = SheetShape
                    )
                    .clip(
                        SheetShape
                    )
                    .background(Color.White)
                    .draggable(
                        orientation = Orientation.Vertical,

                        state = rememberDraggableState { delta ->

                            if (!canDrag) {
                                return@rememberDraggableState
                            }

                            scope.launch {
                                sheetHeight.snapTo(
                                    (sheetHeight.value - delta)
                                        .coerceIn(
                                            collapsedHeightPx,
                                            expandedHeightPx
                                        )
                                )
                            }
                        },

                        onDragStopped = { velocity ->

                            if (!canDrag) {
                                return@draggable
                            }

                            val currentHeight = sheetHeight.value

                            val totalRange =
                                expandedHeightPx - collapsedHeightPx

                            val tenPercent =
                                totalRange * 0.10f

                            val isFlickUp =
                                velocity < -1000f

                            val isFlickDown =
                                velocity > 1000f

                            val targetState = when (sheetState) {

                                InlineSheetState.COLLAPSED -> {

                                    // Flick up OR dragged at least 10%
                                    if (
                                        isFlickUp ||
                                        currentHeight >=
                                        collapsedHeightPx + tenPercent
                                    ) {
                                        InlineSheetState.EXPANDED
                                    } else {
                                        InlineSheetState.COLLAPSED
                                    }
                                }

                                InlineSheetState.EXPANDED -> {

                                    // Flick down OR dragged at least 10%
                                    if (
                                        isFlickDown ||
                                        currentHeight <=
                                        expandedHeightPx - tenPercent
                                    ) {
                                        InlineSheetState.COLLAPSED
                                    } else {
                                        InlineSheetState.EXPANDED
                                    }
                                }
                            }

                            val targetHeight =
                                when (targetState) {
                                    InlineSheetState.COLLAPSED ->
                                        collapsedHeightPx

                                    InlineSheetState.EXPANDED ->
                                        expandedHeightPx
                                }

                            sheetState = targetState

                            scope.launch {
                                sheetHeight.animateTo(
                                    targetValue = targetHeight,
                                    animationSpec = tween(
                                        durationMillis = 250,
                                        easing = FastOutSlowInEasing
                                    )
                                )
                            }
                        }
                    )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .padding(
                            bottom = with(density) {
                                bottomViewHeight.toDp()
                            }
                        )
                ) {
                    // ==================================================
                    // HANDLE
                    // ==================================================

                    CustomDragHandle()

                    if (fixedTopContent != null) {
                        fixedTopContent()
                    }

                    // ==================================================
                    // CONTENT
                    // ==================================================

                    // Animated collapsed/expanded content
                    AnimatedContent(
                        targetState = sheetState,
                        transitionSpec = {
                            (fadeIn(
                                animationSpec = tween(220)
                            ) + slideInVertically(
                                animationSpec = tween(220),
                                initialOffsetY = { it / 4 }
                            )) togetherWith
                                    (fadeOut(
                                        animationSpec = tween(120)
                                    ) + slideOutVertically(
                                        animationSpec = tween(120),
                                        targetOffsetY = { -it / 4 }
                                    ))
                        },
                        label = "SheetContentAnimation"
                    ) { state ->

                        when (state) {

                            InlineSheetState.COLLAPSED -> {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    content = collapsedContent
                                )
                            }

                            InlineSheetState.EXPANDED -> {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    content = expandedContent
                                )
                            }
                        }
                    }
                }

                // ALWAYS visible
                if (fixedBottomContent != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                    ) {
                        fixedBottomContent()
                    }
                }
            }
        }

        // ======================================================
        // MEASURE COLLAPSED CONTENT
        // ======================================================

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .alpha(0f)
                .onGloballyPositioned { coordinates ->

                    val height = coordinates.size.height

                    if (height != collapsedContentHeight) {
                        collapsedContentHeight = height
                    }
                }
        ) {
            collapsedContent()
        }

        // ======================================================
        // MEASURE EXPANDED CONTENT
        // ======================================================

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .alpha(0f)
                .onGloballyPositioned { coordinates ->

                    val height = coordinates.size.height

                    if (height != expandedContentHeight) {
                        expandedContentHeight = height
                    }
                }
        ) {
            expandedContent()
        }

        if (fixedTopContent != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .alpha(0f)
                    .onGloballyPositioned { coordinates ->
                        val height = coordinates.size.height

                        if (height != topViewHeight) {
                            topViewHeight = height
                        }
                    }
            ) {
                fixedTopContent.invoke()
            }
        }
        if (fixedBottomContent != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .alpha(0f)
                    .onGloballyPositioned { coordinates ->
                        val height = coordinates.size.height

                        if (height != bottomViewHeight) {
                            bottomViewHeight = height
                        }
                    }
            ) {
                fixedBottomContent()
            }
        }
    }
}

/**
 * Renders the standard drag handle indicator at the top of the bottom sheet.
 */
@Composable
private fun CustomDragHandle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .width(40.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.Gray)
        )
    }
}

@Composable
fun NearByProjectsSheetContent(modifier: Modifier = Modifier, onFilterClick: () -> Unit) {
    Column(modifier) {
        NearByProjectsSheetHeader(modifier = Modifier.padding(end = 16.dp), onFilterClick = onFilterClick)
        Spacer(Modifier.height(4.dp))
        LazyRow(
            Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(5) {
                SuggestedProjectCard(
                    Modifier
                        .width(290.dp)
                        .height(324.dp)
                )
            }
        }
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
fun DiscoveryBottomView(modifier: Modifier = Modifier) {
    Box(
        modifier
            .dropShadow(shape = RectangleShape, shadow = Shadow(radius = 4.dp, color = Color(0x0c000000)))
            .border(width = 1.dp, color = Color(0xffe8eaed), shape = RectangleShape)
            .background(color = Color.White)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    "Your visit plan ",
                    fontFamily = getFontFamily(weight = FontWeight.Medium),
                    fontSize = 10.sp,
                    lineHeight = 16.sp,
                    color = textColorLight
                )
                Text(
                    "3 Project · Est. ~ 2.0 - 2.5 hrs",
                    fontFamily = getFontFamily(),
                    fontSize = 10.sp,
                    lineHeight = 16.sp,
                    color = textColorExtraLight
                )
            }
            Box(
                Modifier
                    .weight(1f)
                    .background(color = mbRed, shape = RoundedCornerShape(50))
                    .padding(vertical = 8.dp), contentAlignment = Alignment.Center
            ) {
                Text(
                    "Continue",
                    fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun NearByProjectsSheetCollapsedHeader(modifier: Modifier = Modifier, onFilterClick: () -> Unit) {
    val filters = listOf(
        ItineraryFilter("Budget", "₹1.8 – ₹2 Cr"),
        ItineraryFilter("Max drive time:", "30 mins"),
        ItineraryFilter("Budget", "₹1.8 – ₹2 Cr"),
        ItineraryFilter("Max drive time:", "30 mins"),
        ItineraryFilter("Budget", "₹1.8 – ₹2 Cr"),
        ItineraryFilter("Max drive time:", "30 mins"),
    )
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.ic_filter_icon),
            contentDescription = null,
            modifier = Modifier
                .size(34.dp)
                .noRippleClick { onFilterClick() })
        Spacer(Modifier.width(5.dp))
        LazyRow(
            Modifier.weight(1f), contentPadding = PaddingValues(end = 16.dp), horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(filters) {
                ItineraryFilterItem(filter = it)
            }
        }
    }
}

data class ItineraryFilter(val name: String, val value: String)

@Composable
fun ItineraryFilterItem(modifier: Modifier = Modifier, filter: ItineraryFilter) {
    Row(
        modifier
            .border(width = 1.dp, color = Color(0xffd7d7d7), shape = RoundedCornerShape(50))
            .background(color = Color(0xfff5f5f5), shape = RoundedCornerShape(50))
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Text("${filter.name}:", fontFamily = getFontFamily(), fontSize = 10.sp, lineHeight = 18.sp, color = textColorDark)
        Spacer(Modifier.width(4.dp))
        Text(
            filter.value,
            fontFamily = getFontFamily(weight = FontWeight.SemiBold),
            fontSize = 10.sp,
            lineHeight = 16.6.sp,
            color = textColorDark
        )
    }
}

@Composable
fun NearByProjectsSheetHeader(modifier: Modifier = Modifier, onFilterClick: () -> Unit) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        NearByProjectsSheetTitle(Modifier.weight(1f))
        Spacer(Modifier.width(16.dp))
        Image(
            painter = painterResource(R.drawable.ic_filter_icon),
            contentDescription = null,
            modifier = Modifier
                .size(34.dp)
                .noRippleClick { onFilterClick() })
    }
}

@Composable
fun NearByProjectsSheetTitle(modifier: Modifier = Modifier, projectName: String = "Pavani Mirabilia Phase 2") {
    Text(buildAnnotatedString {
        append("While visiting $projectName ")
        withStyle(style = SpanStyle(fontFamily = getFontFamily(weight = FontWeight.Bold))) {
            append("Explore nearby projects to add to your visit")
        }
    }, fontFamily = getFontFamily(), fontSize = 16.sp, lineHeight = 20.sp, color = textColorDark, modifier = modifier)
}

@Composable
fun SvCardLayout(
    modifier: Modifier = Modifier,
    containerColor: Color = Color(0xffedfaf9),
    titleRow: @Composable () -> Unit,
    contentColor: Color = Color.White,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(Modifier.padding(4.dp)) {
            titleRow()
            Spacer(Modifier.height(4.dp))
            Box(
                Modifier
                    .background(contentColor, shape = RoundedCornerShape(16.dp))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }
    }
}

@Composable
fun DefaultAnchorCard(modifier: Modifier = Modifier) {
    SvCardLayout(modifier = modifier, containerColor = Color(0xfff5f5f5), titleRow = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.green_right_tik_),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                "SITE VISIT CONFIRMED",
                fontFamily = getFontFamily(weight = FontWeight.Bold),
                fontSize = 10.sp,
                lineHeight = 16.sp,
                color = Color(0xff009681)
            )
        }
    }, content = {
        Row(
            Modifier
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .size(40.dp)
                    .background(color = Color.Gray, shape = RoundedCornerShape(6.dp))
            ) {
                Image(
                    painter = painterResource(R.drawable.gst_webp),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
            Spacer(Modifier.width(5.dp))
            Column {
                Text(
                    "Pavani Mirabilia Phase 2",
                    fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = textColorDark
                )
                Text(
                    "Whitefield - Hoskote Rd   Sat, 12 Jul • 3:00 PM",
                    fontFamily = getFontFamily(),
                    fontSize = 10.sp,
                    lineHeight = 16.sp,
                    color = textColorDark
                )
            }
        }
    })
}

@Composable
fun DefaultCollapsedCard(modifier: Modifier = Modifier) {
    SvCardLayout(
        modifier = modifier, titleRow = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.green_right_tik_),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "SITE VISIT CONFIRMED",
                    fontFamily = getFontFamily(weight = FontWeight.Bold),
                    fontSize = 8.sp,
                    lineHeight = 13.sp,
                    color = Color(0xff009681)
                )
            }
        }) {
        Row(
            Modifier
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .size(20.dp)
                    .background(color = Color.Gray, shape = RoundedCornerShape(6.dp))
            ) {
                Image(
                    painter = painterResource(R.drawable.gst_webp),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
            Spacer(Modifier.width(5.dp))
            Column {
                Text(
                    "Pavani Mirabilia Phase 2",
                    fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                    fontSize = 10.sp,
                    color = textColorDark
                )
            }
        }
    }
}

@Composable
fun AddedProjectCard(modifier: Modifier = Modifier) {
    SvCardLayout(
        modifier = modifier, titleRow = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.ic_green_tick_009681_icon),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "ADDED",
                    fontFamily = getFontFamily(weight = FontWeight.Bold),
                    fontSize = 8.sp,
                    lineHeight = 13.sp,
                    color = Color(0xff009681)
                )
            }
        }) {
        Column {
            Text(
                "Brigade Utopia",
                fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                fontSize = 10.sp,
                color = textColorDark
            )
            Row {
                Icon(painter = painterResource(R.drawable.icon_car_grey), contentDescription = null, tint = textColorExtraLight)
                Text(
                    "~10 min drive",
                    fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                    fontSize = 8.sp,
                    lineHeight = 14.sp,
                    color = textColorExtraLight
                )
            }
        }
    }
}

@Composable
fun SuggestedProjectCard(modifier: Modifier = Modifier) {
    var added by remember { mutableStateOf(false) }
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(width = 1.dp, color = Color(0xffe5e7eb)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = Color.Gray, shape = RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_project),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
                Row(
                    Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp)
                        .background(color = textColorDark, shape = RoundedCornerShape(50))
                        .padding(vertical = 6.dp, horizontal = 10.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_car_grey), contentDescription = null, tint = Color.White
                    )
                    Text(
                        "~10 min drive",
                        fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                        fontSize = 8.sp,
                        lineHeight = 14.sp,
                        color = Color.White
                    )
                }
                Row(
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp)
                        .background(color = Color(0x4c000000), shape = RoundedCornerShape(50))
                        .padding(vertical = 6.dp, horizontal = 10.dp)
                ) {
                    Text(
                        "5 visits booked in last 2 days",
                        fontFamily = getFontFamily(weight = FontWeight.SemiBold, fontStyle = FontStyle.Italic),
                        fontSize = 8.sp,
                        lineHeight = 14.sp,
                        color = mbGold
                    )
                }
            }
            Spacer(Modifier.height(17.dp))
            Column(Modifier.weight(1f)) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Brigade Utopia",
                        fontFamily = getFontFamily(weight = FontWeight.Bold),
                        fontSize = 18.sp,
                        lineHeight = 22.sp,
                        textDecoration = TextDecoration.Underline,
                        color = textColorDark
                    )
                    Box(
                        Modifier
                            .background(color = Color(0xfff2f2f2), shape = CircleShape)
                            .size(24.dp), contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = null, modifier = Modifier.size(15.dp))
                    }
                }
                Spacer(Modifier.height(5.dp))
                Text(
                    "Whitefield · Varthur Rd",
                    fontFamily = getFontFamily(),
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = textColorLight
                )
                Spacer(Modifier.height(10.dp))
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                    Text(
                        "₹1.92Cr",
                        fontFamily = getFontFamily(weight = FontWeight.Bold),
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        color = textColorDark
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "· 3 BHK · Under Construction",
                        fontFamily = getFontFamily(),
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        color = textColorDark
                    )
                }
                Spacer(Modifier.weight(1f))
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (!added) {
                        Box(
                            Modifier
                                .weight(1f)
                                .border(width = 1.dp, color = mbRed, shape = RoundedCornerShape(50))
                                .noRippleClick { added = true }
                                .padding(vertical = 10.dp), contentAlignment = Alignment.Center) {
                            Text(
                                "+ ADD TO VISIT",
                                fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                                fontSize = 12.sp,
                                lineHeight = 12.5.sp,
                                color = mbRed
                            )
                        }
                    } else {
                        Box(
                            Modifier
                                .weight(1f)
                                .border(width = 1.dp, color = mbGreen, shape = RoundedCornerShape(50))
                                .padding(vertical = 10.dp), contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(R.drawable.ic_green_tick_009681_icon),
                                    contentDescription = null,
                                    modifier = Modifier.size(10.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    "ADDED",
                                    fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                                    fontSize = 12.sp,
                                    lineHeight = 12.5.sp,
                                    color = mbGreen
                                )
                            }
                        }
                        Box(
                            Modifier
                                .weight(1f)
                                .noRippleClick { added = false }
                                .padding(vertical = 10.dp),
                            contentAlignment = Alignment.Center) {
                            Text(
                                "Remove",
                                fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                                fontSize = 12.sp,
                                lineHeight = 12.5.sp,
                                color = mbRed
                            )
                        }
                    }
                }
            }
        }
    }
}
