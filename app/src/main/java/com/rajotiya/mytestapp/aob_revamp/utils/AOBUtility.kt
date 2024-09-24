package com.rajotiya.mytestapp.aob_revamp.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.GsonBuilder
import com.rajotiya.mytestapp.AppContext
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.models.PopularLtPrjData
import com.rajotiya.mytestapp.aob_revamp.ui.theme.disableButtonColor
import com.rajotiya.mytestapp.aob_revamp.ui.theme.flowItemBGColorSelected
import com.rajotiya.mytestapp.aob_revamp.ui.theme.flowItemBorderColorSelected
import com.rajotiya.mytestapp.aob_revamp.ui.theme.flowItemBorderColorUnselected
import com.rajotiya.mytestapp.aob_revamp.ui.theme.focusSearchBarBorderColor
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.searchBarBorderColor
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textFieldColor
import com.rajotiya.mytestapp.aob_revamp.ui.theme.unfocusSearchBarBorderColor
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFont
import kotlinx.coroutines.delay
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

/**
 * Created by Pawan Rajotiya on 05-08-2024.
 */


@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    errorMessage: String = "",
    singleLine: Boolean = false,
    readOnly: Boolean = false,
    isError: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    trailingIcon: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions()
) {
    Column {
        OutlinedTextField(
            modifier = modifier,
            singleLine = singleLine,
            value = value,
            readOnly = readOnly,
            trailingIcon = trailingIcon,
            placeholder = placeholder,
            isError = isError,
            interactionSource = interactionSource,
            onValueChange = {
                if (singleLine) {
                    if (!it.contains("\n")) {
                        onValueChange(it)
                    }
                } else {
                    onValueChange(it)
                }
            },
            label = {
                Text(
                    text = label,
                    color = textColorLight,
                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            },
            textStyle = TextStyle(
                color = textColorDark,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_MEDIUM))
            ),
            keyboardOptions = keyboardOptions.copy(
                imeAction = ImeAction.None
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = textFieldColor,
                unfocusedBorderColor = textFieldColor,
                disabledBorderColor = textFieldColor,
                cursorColor = textColorDark,
                errorBorderColor = mbRed,
                errorTextColor = mbRed,
            ),
            shape = RoundedCornerShape(12.dp)
        )
        if (isError && errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = mbRed,
                fontSize = 12.sp,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

fun loadJSONFromAsset(filname: String, className: Class<*>?): Any? {
    val json: String?
    return try {
        val `is`: InputStream? = AppContext.getApplicationContext().assets?.open(filname)
            ?: filname?.let { AppContext.getApplicationContext().assets?.open(it) }
        val size = `is`?.available()
        val buffer = size?.let { ByteArray(it) }
        `is`?.read(buffer)
        `is`?.close()
        json =
            buffer?.let { String(it, StandardCharsets.UTF_8) }
        val gson =
            GsonBuilder().excludeFieldsWithModifiers(java.lang.reflect.Modifier.STATIC, java.lang.reflect.Modifier.PROTECTED)
                .create()
        gson.fromJson(json, className)
    } catch (ex: IOException) {
        ex.printStackTrace()
        null
    }
}

@Composable
fun FlowItem(modifier: Modifier = Modifier, item: String, selected: Boolean) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = if (selected) flowItemBorderColorSelected else flowItemBorderColorUnselected,
                shape = RoundedCornerShape(50)
            )
            .background(color = if (selected) flowItemBGColorSelected else Color.Transparent, shape = RoundedCornerShape(50))
            .padding(horizontal = 14.dp, vertical = 7.dp)
    ) {
        if (selected) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = item,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    color = textColorDark,
                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_aob_cross),
                    contentDescription = null
                )
            }
        } else {
            Text(
                text = item,
                fontSize = 13.sp,
                lineHeight = 18.sp,
                color = textColorDark,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_MEDIUM))
            )
        }
    }
}

@Composable
fun BottomRedButton(modifier: Modifier = Modifier, enable: Boolean, ctaText: String, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .clickable { if (enable) onClick() }
            .background(color = if (enable) mbRed else disableButtonColor, shape = RoundedCornerShape(50))
    ) {
        Text(
            text = ctaText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            textAlign = TextAlign.Center,
            color = if (enable) Color.White else Color.White,
            fontSize = 14.sp,
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
        )
    }
}

@Composable
fun FilterFormRequiredFieldHeading(modifier: Modifier = Modifier, heading: String) {
    val str = buildAnnotatedString {
        append(heading)
        withStyle(style = SpanStyle(color = mbRed)) {
            append("*")
        }
    }
    Text(
        text = str,
        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = textColorLight,
        modifier = modifier
    )
}

@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    placeholder: @Composable (() -> Unit)? = null,
    alternatePlaceholder: @Composable (() -> Unit)? = null,
    selectedItems: List<PopularLtPrjData.LtPrjDetail>? = null,
    onSelectedItemClick: (item: PopularLtPrjData.LtPrjDetail) -> Unit,
    state: LazyListState = rememberLazyListState(),
    singleLine: Boolean = true
) {
    BoxWithConstraints(modifier = modifier) {
        val maxWidthIn = maxWidth * 0.6f
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            placeholder = {
                if (selectedItems.isNullOrEmpty()) {
                    if (placeholder != null) {
                        placeholder()
                    }
                } else if (alternatePlaceholder != null) {
                    alternatePlaceholder()
                }
            },
            leadingIcon = {
                if (selectedItems.isNullOrEmpty()) {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = null, tint = textColorLight)
                } else {
                    LazyRow(
                        modifier = Modifier
                            .wrapContentWidth()
                            .widthIn(max = maxWidthIn)
                            .padding(start = 12.dp, end = 8.dp), state = state, horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(selectedItems) { item: PopularLtPrjData.LtPrjDetail ->
                            Row(
                                modifier = Modifier
                                    .clickable(interactionSource, indication = null) { onSelectedItemClick(item) }
                                    .border(width = 1.dp, color = flowItemBorderColorSelected, shape = RoundedCornerShape(50))
                                    .background(color = flowItemBGColorSelected, shape = RoundedCornerShape(50))
                                    .padding(horizontal = 12.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = item.name ?: "",
                                    fontSize = 12.sp,
                                    lineHeight = 20.sp,
                                    color = textColorDark,
                                    maxLines = 1,
                                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                                    overflow = TextOverflow.Ellipsis, modifier = Modifier
                                        .widthIn(min = 0.dp, max = 120.dp)
                                )
                                Icon(
                                    imageVector = Icons.Outlined.Close,
                                    contentDescription = null,
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }
                    }
                }
            },
            interactionSource = interactionSource,
            onValueChange = {
                if (it.length < 20) {
                    onValueChange(it)
                }
            },
            textStyle = TextStyle(
                color = textColorDark,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_medium))
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = focusSearchBarBorderColor,
                unfocusedBorderColor = unfocusSearchBarBorderColor,
                disabledBorderColor = searchBarBorderColor,
                cursorColor = textColorDark
            ),
            shape = RoundedCornerShape(4.dp),
            singleLine = singleLine
        )
    }
}

@Composable
fun Modifier.preventMultipleClicks(
    delayMillis: Long = 1000, // Default delay to prevent multiple clicks
    onClick: () -> Unit
): Modifier {
    var isClickable by remember { mutableStateOf(true) }

    LaunchedEffect(isClickable) {
        if (!isClickable) {
            delay(delayMillis)
            isClickable = true
        }
    }

    return this.then(
        Modifier.clickable(
            enabled = isClickable,
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {
            isClickable = false
            onClick()
        }
    )
}

@Composable
fun Modifier.noRippleClick(onClick: () -> Unit): Modifier {
    return this.then(
        Modifier.clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) { onClick() }
    )
}

// Function to create a shake effect
suspend fun shakeTextField(animatable: Animatable<Float, AnimationVector1D>) {
    animatable.snapTo(0f) // Start from the default position
    animatable.animateTo(
        targetValue = 15f,
        animationSpec = repeatable(
            iterations = 2, // Repeat 6 times for shake effect
            animation = tween(durationMillis = 200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    animatable.snapTo(0f) // Reset position after shake
}