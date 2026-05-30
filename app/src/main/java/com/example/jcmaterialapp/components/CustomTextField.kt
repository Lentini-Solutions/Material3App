package com.example.jcmaterialapp.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.example.jcmaterialapp.R

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    labelRes: Int,
    maxLengthRes: Int? = null,
    iconRes: Int,
    onValueChange: (String) -> Unit,
    minValue: Int = 0,
    errorRes: Int = R.string.required,
    keyboardOptions: KeyboardOptions? = null,
    singleLines: Boolean = false,
    isRequired: Boolean = true,
    isClean: Boolean = false
){

    var textValue by remember { mutableStateOf("") }
    val maxLength = if(maxLengthRes == null) null
    else integerResource(maxLengthRes)

    var isError by remember { mutableStateOf(false) }

    if(isClean){
        textValue = ""
    }

    OutlinedTextField(
        value = textValue,
        onValueChange = {
            if (maxLength == null){
                textValue = it
            }else{
                if(it.length <= maxLength){
                    textValue = it
                }
            }
            isError = it.trim().isEmpty()
            if(minValue > 0){
                isError = (textValue.toIntOrNull() ?: 0) <= minValue
            }
            onValueChange(textValue)
        },
        isError = isError,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                dimensionResource(R.dimen.common_padding_default)
            ),
        label = {
            Text(text = stringResource(labelRes))
        },
        leadingIcon = {
            Icon(
                painterResource(iconRes),
                contentDescription = null
            )
        },
        keyboardOptions = KeyboardOptions(
            capitalization = keyboardOptions?.capitalization ?: KeyboardCapitalization.Sentences,
            keyboardType = keyboardOptions?.keyboardType ?: KeyboardType.Text,
            imeAction = if(keyboardOptions == null ||
                            keyboardOptions.imeAction == ImeAction.Default
                        ) ImeAction.Next else keyboardOptions.imeAction
        ),
        supportingText = {
            Row {
                if(isRequired){
                    Text(text = if(isError) stringResource(errorRes) else stringResource(R.string.required))
                }
                Spacer(Modifier.weight(1f))
                if(maxLengthRes != null && minValue == 0){
                    Text(text = "${textValue.length}/$maxLength")
                }
            }
        },
        singleLine = singleLines
    )

}