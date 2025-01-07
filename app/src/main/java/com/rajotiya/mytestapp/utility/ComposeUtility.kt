package com.rajotiya.mytestapp.utility

import android.graphics.Color
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
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
fun TextWithPlaceholders(textModel: TextModel) {
    val annotatedString = buildAnnotatedString {
        val mainText = textModel.text
        var currentIndex = 0

        // Iterate over placeholders and format them in the main text
        textModel.placeholder?.forEach { placeholder ->
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
        fontFamily = getComposeFont(font = textModel.font, weight = textModel.weight)
    )
}

fun getComposeColor(color:String) : ComposeColor {
    return ComposeColor(Color.parseColor(color))
}

fun getComposeFont(font:String, weight:String): FontFamily{
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

