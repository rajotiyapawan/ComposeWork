package com.rajotiya.mytestapp.aob_revamp

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.GsonBuilder
import com.rajotiya.mytestapp.AppContext
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textFieldColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

/**
 * Created by Pawan Rajotiya on 05-08-2024.
 */


@Composable
fun CustomTextField(
    modifier: Modifier= Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    singleLine: Boolean = false,
    readOnly: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    trailingIcon: @Composable() (() -> Unit)? = null,
    placeholder: @Composable() (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions()
) {
    OutlinedTextField(
        modifier = modifier,
        singleLine = singleLine,
        value = value,
        readOnly = readOnly,
        trailingIcon = trailingIcon,
        placeholder = placeholder,
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
                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        },
        textStyle = TextStyle(
            color = textColorDark,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_medium))
        ),
        keyboardOptions = keyboardOptions.copy(
            imeAction = ImeAction.None
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = textFieldColor,
            unfocusedBorderColor = textFieldColor,
            disabledBorderColor = textFieldColor,
            cursorColor = textColorDark
        ),
        shape = RoundedCornerShape(12.dp)
    )
}

fun loadJSONFromAsset( filname: String, className: Class<*>?): Any? {
    var json: String? = null
    return try {
        var `is`: InputStream? = null
        `is` =
            AppContext.getApplicationContext()?.assets?.open(filname)
                ?: filname?.let { AppContext.getApplicationContext()?.assets?.open(it) }
        val size = `is`?.available()
        val buffer = size?.let { ByteArray(it) }
        `is`?.read(buffer)
        `is`?.close()
        json =
            buffer?.let { String(it, StandardCharsets.UTF_8) }
        val gson = GsonBuilder().excludeFieldsWithModifiers(java.lang.reflect.Modifier.STATIC, java.lang.reflect.Modifier.PROTECTED)
            .create()
        gson.fromJson(json, className)
    } catch (ex: IOException) {
        ex.printStackTrace()
        null
    }
}