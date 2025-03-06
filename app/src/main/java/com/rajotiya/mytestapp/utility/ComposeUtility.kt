package com.rajotiya.mytestapp.utility

import android.graphics.Color
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.loyalty.models.TextModel
import androidx.compose.ui.graphics.Color as ComposeColor

@Composable
fun TextWithIcon(
    text: String,
    iconText: String,
    icon: @Composable () -> Unit,
    textStyle: TextStyle,
    modifier: Modifier = Modifier
) {
    val inlineContentId = "inlineIcon"

    // Prepare the AnnotatedString
    val annotatedString = buildAnnotatedString {
        val splitText = text.split(iconText)
        append(splitText.first())
        appendInlineContent(inlineContentId, "[icon]")
        if (splitText.size > 1) {
            append(splitText.last())
        }
    }

    // Define InlineContent
    val inlineContent = mapOf(
        inlineContentId to InlineTextContent(
            placeholder = Placeholder(
                width = textStyle.fontSize,
                height = textStyle.fontSize,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            ),
            children = { icon() }
        )
    )

    // Render the Text composable
    Text(
        text = annotatedString,
        style = textStyle,
        inlineContent = inlineContent,
        modifier = modifier
    )
}

@Composable
fun TextWithPlaceholders(textModel: TextModel, modifier: Modifier = Modifier) {
    if (textModel.placeholder?.isNotEmpty() == true) {
        val annotatedString = buildAnnotatedString {
            val mainText = textModel.text
            var currentIndex = 0

            // Iterate over placeholders and format them in the main text
            textModel.placeholder.forEach { placeholder ->
                val placeholderIndex = mainText.indexOf(placeholder.text, startIndex = currentIndex)
                if (placeholderIndex != -1) {
                    // Add non-placeholder text
                    append(mainText.substring(currentIndex, placeholderIndex))

                    // Add placeholder text with styling
                    withStyle(
                        style = SpanStyle(
                            color = ComposeColor(Color.parseColor(placeholder.color)),
                            fontSize = placeholder.size.toFloat().sp,
                            fontFamily = getComposeFont(font = placeholder.font, weight = placeholder.weight)
                        )
                    ) {
                        append(placeholder.text)
                    }

                    currentIndex = placeholderIndex + placeholder.text.length
                }
            }

            // Add remaining text after the last placeholder
            if (currentIndex < mainText.length) {
                append(mainText.substring(currentIndex))
            }
        }

        // Display the annotated text
        Text(
            text = annotatedString,
            color = ComposeColor(Color.parseColor(textModel.color)),
            fontSize = textModel.size.toFloat().sp,
            fontFamily = getComposeFont(font = textModel.font, weight = textModel.weight),
            lineHeight = textModel.lineHeight?.toFloat()?.sp ?: textModel.size.toFloat().sp,
            modifier=modifier
        )
    } else {
        Text(
            text = textModel.text,
            color = ComposeColor(Color.parseColor(textModel.color)),
            fontSize = textModel.size.toFloat().sp,
            fontFamily = getComposeFont(font = textModel.font, weight = textModel.weight),
            lineHeight = textModel.lineHeight?.toFloat()?.sp ?: textModel.size.toFloat().sp,
            modifier = modifier
        )
    }
}

fun getComposeColor(color: String): ComposeColor {
    return ComposeColor(Color.parseColor(color))
}

fun getComposeFont(font: String, weight: String): FontFamily {
    return when (font) {
        "Montserrat" -> getComposeMontserratFont(weight)
        else -> getComposeMontserratFont(weight)
    }
}

fun getComposeMontserratFont(weight: String): FontFamily {
    return when (weight) {
        "SemiBold" -> getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
        "Bold" -> getFontFamily(Constants.MONTSERRAT_BOLD)
        "Medium" -> getFontFamily(Constants.MONTSERRAT_MEDIUM)
        "Regular" -> getFontFamily(Constants.MONTSERRAT_REGULAR)
        else -> getFontFamily(Constants.MONTSERRAT_REGULAR)
    }
}

@Composable
fun Modifier.noRippleClick(delayMillis: Long = 500L,onClick: () -> Unit):Modifier {
    var lastClickTime by remember { mutableLongStateOf(0L) }
    return this.then(
        Modifier.clickable(interactionSource = remember { MutableInteractionSource() }, indication = null
        ) {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime >= delayMillis) {
                lastClickTime = currentTime
                onClick()
            }
        }
    )
}

@Composable
fun BottomPopupDialog(
    modifier: Modifier=Modifier,
    showDialog: Boolean,
    alignment: Alignment = Alignment.BottomCenter,
    onDismiss: () -> Unit,
    showCross: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = false)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = ComposeColor.Transparent) // Blurred effect
                    .noRippleClick { onDismiss() }, // Dismiss on background click
                contentAlignment = alignment
            ) {
                // Animated Bottom Content
                AnimatedVisibility(
                    visible = showDialog,
                    enter = slideInVertically(initialOffsetY = { it }, animationSpec = tween(400)),
                    exit = slideOutVertically(targetOffsetY = { it }, animationSpec = tween(400))
                ) {
                    Column {
                        if (showCross) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                        onClick = { onDismiss() })
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.need_assistance_small_cross_icon),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(end = 16.dp)
                                        .size(32.dp)
                                        .align(
                                            Alignment.CenterEnd
                                        )
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp)) // Space between cross and content
                        }
                        Column(
                            modifier = modifier
                                .weight(1f, false)
                                .noRippleClick {  }
                        ) {
                            content()
                        }
                    }
                }
            }
        }
    }
}


fun defaultEnterTransition(): EnterTransition {
    return fadeIn(animationSpec = tween(durationMillis = 0)) +
            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300))
}

fun defaultExitTransition(): ExitTransition {
    return fadeOut(animationSpec = tween(durationMillis = 0)) +
            slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(300))
}

fun defaultPopEnterTransition(): EnterTransition {
    return fadeIn(animationSpec = tween(durationMillis = 0)) +
            slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(300))
}

fun defaultPopExitTransition(): ExitTransition {
    return fadeOut(animationSpec = tween(durationMillis = 0)) +
            slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300))
}