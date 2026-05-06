package com.rajotiya.mytestapp.aob_revamp.utils

import android.text.TextUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.rajotiya.mytestapp.aob_revamp.models.CountryListModel
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textFieldColor
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFont

/**
 * Created by Pawan Rajotiya on 08-09-2023.
 */

@Composable
fun CustomMobileInputField(
    modifier: Modifier,
    isd: String,
    isdChange: (String) -> Unit,
    mobile: String,
    mobileChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    errorMessage: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    selectedCountry: (CountryListModel.Country) -> Unit
) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column {
                IsdCodePicker(isd, isdChange, selectedCountry)
            }
            CustomTextField(
                modifier = Modifier.weight(weight = 1f),
                singleLine = true,
                value = mobile,
                onValueChange = {
                    if (TextUtils.isDigitsOnly(it))
                        mobileChange(it)
                },
                label = label,
                isError = isError,
                keyboardOptions = keyboardOptions
            )
        }
        if (isError) {
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

@Composable
private fun IsdCodePicker(
    isd: String,
    isdChange: (String) -> Unit,
    selectedCountry: (CountryListModel.Country) -> Unit
) {
    val showDialog = remember { mutableStateOf(false) }
    val selCountry = remember { mutableStateOf(CountryListModel.Country("50", "IND", "+91")) }
    val mutableInteractionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .width(120.dp)
            .clickable(interactionSource = mutableInteractionSource, indication = null) {
                showDialog.value = true
            }
    ) {
        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            interactionSource = mutableInteractionSource,
            value = isd,
            onValueChange = isdChange,
            label = "",
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    contentDescription = null,
                    Modifier.size(12.dp)
                )
            })
    }
    if (mutableInteractionSource.collectIsPressedAsState().value) {
        showDialog.value = true
    }
    if (showDialog.value) {
        OpenIsdPickerDialog(isdChange = isdChange, onDismiss = { showDialog.value = false }, {
            selCountry.value = it
            selectedCountry(it)
        })
    }
}

@Composable
private fun OpenIsdPickerDialog(
    isdChange: (String) -> Unit,
    onDismiss: () -> Unit,
    selectedCountry: (CountryListModel.Country) -> Unit
) {
    val isdList = loadJSONFromAsset("ISDCodes.json", CountryListModel::class.java) as CountryListModel?
    isdList?.let { list ->
        Popup(
            alignment = Alignment.TopStart,
            offset = IntOffset(x = 0, y = 140),
            onDismissRequest = { onDismiss() },
        ) {
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(300.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                    .border(
                        width = 1.dp,
                        color = textFieldColor,
                        shape = RoundedCornerShape(8.dp)
                    ),
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(list.countryCodes) {
                        IsdCodeItem(code = it.isdCodes, country = it.displayName) { code ->
                            isdChange(code ?: "")
                            selectedCountry(it)
                            onDismiss()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun IsdCodeItem(code: String?, country: String?, onSelection: (code: String?) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelection(code) }, contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = "$code - $country",
            color = textColorDark,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_MEDIUM))
        )
    }
}