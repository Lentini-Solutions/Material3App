package com.example.jcmaterialapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcmaterialapp.R
import com.example.jcmaterialapp.components.CustomDateTextField
import com.example.jcmaterialapp.components.CustomTextField
import com.example.jcmaterialapp.components.DatePickerModal
import com.example.jcmaterialapp.components.ProcessingView
import com.example.jcmaterialapp.components.RadioButtonProfiles
import com.example.jcmaterialapp.fake_data.simulateDelayLong
import com.example.jcmaterialapp.model.User
import com.example.jcmaterialapp.ui.theme.JCMaterialAppTheme
import com.example.jcmaterialapp.ui.theme.Typography
import kotlinx.coroutines.launch

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPreview(){
    JCMaterialAppTheme() {
        MainView(Modifier.padding(top = 24.dp), onSave = {}, onCleaned = {})
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainView(
    modifier: Modifier,
    isClean: Boolean = false,
    onSave: (user: User) -> Unit,
    onCleaned: () -> Unit
){

    val scope = rememberCoroutineScope()

    var isProcessing by remember { mutableStateOf(false) }
    var nameValue by remember { mutableStateOf("") }
    var surnameValue by remember { mutableStateOf("") }
    var heightValue by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    var dateValue by remember { mutableStateOf<Long?>(null) }
    var notesValues by remember { mutableStateOf("") }
    var acceptValue by remember { mutableStateOf(false) }

    val profiles = listOf("Estudiante","Programador")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(profiles[0]) }

    val canRegister = nameValue.isNotBlank() &&
                        surnameValue.isNotBlank() &&
                        heightValue.isNotBlank() &&
                        acceptValue

    val isKeyboardVisible = WindowInsets.isImeVisible

    fun onSaveData(){
        scope.launch {
            isProcessing = true
            simulateDelayLong()
            onSave(User(
                nameValue, surnameValue,
                heightValue.toInt(),
                dateValue ?: 0L,
                selectedOption,
                notes = notesValues
            ))
            isProcessing = false
        }
    }

    if(isClean){
        dateValue = null
        onOptionSelected(profiles[0])
        notesValues = ""
        acceptValue = false
        onCleaned()
    }

    Box(modifier
        .fillMaxWidth()
    ) {

        Card(modifier.padding(dimensionResource(R.dimen.common_padding_min))) {
            Column(modifier = modifier) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    //allowed show button top, if keyboard is visible
                    val saveAlpha = if(isKeyboardVisible) 1f else 0f

                    //title Form
                    Text(text = stringResource(R.string.register_text_form),
                        style = Typography.labelLarge,
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.common_padding_default)),
                    )
                    Button(onClick = {
                        onSaveData()
                    },
                        modifier = Modifier
                            .padding(vertical = dimensionResource(R.dimen.common_padding_default))
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.common_padding_default))
                            .alpha(saveAlpha),
                        enabled = canRegister,
                        shape = CutCornerShape(dimensionResource(R.dimen.common_padding_default))
                    ) {
                        Icon(
                            painterResource(R.drawable.ic_save_check),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = dimensionResource(R.dimen.common_padding_min)),
                        )
                    }
                }

                Column(Modifier
                    .imePadding()
                    .verticalScroll(rememberScrollState())
                ) {
                    //Name
                    CustomTextField(
                        labelRes = R.string.hint_name,
                        maxLengthRes = R.integer.max_length_username,
                        iconRes = R.drawable.ic_outline_person,
                        onValueChange = { nameValue = it },
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words
                        ),
                        isClean = isClean
                    )

                    //Surname
                    CustomTextField(
                        labelRes = R.string.hint_surname,
                        iconRes = R.drawable.ic_outline_person,
                        onValueChange = { surnameValue = it},
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words
                        ),
                        isClean = isClean,
                        singleLines = true
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement
                            .spacedBy(dimensionResource(R.dimen.common_padding_default))
                    ) {
                        //Height
                        CustomTextField(
                            modifier = Modifier.weight(50f),
                            labelRes = R.string.hint_height,
                            maxLengthRes = R.integer.max_length_height,
                            iconRes = R.drawable.ic_height,
                            onValueChange = { heightValue = it },
                            minValue = integerResource(R.integer.height_min_value),
                            errorRes = R.string.error_min_height_valid,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            isClean = isClean
                        )

                        //BirthDay
                        CustomDateTextField(
                            modifier = Modifier.weight(50f),
                            labelRes = R.string.hint_birth_date,
                            selectedDate = dateValue,
                            onShowModal = { showDatePicker = true }
                        )

                        if(showDatePicker){
                            DatePickerModal(
                                onDateSelected = { dateValue = it },
                                onDismissRequest = { showDatePicker = false }
                            )
                        }

                    }

                    //Options Occupation
                    RadioButtonProfiles(
                        profiles = profiles,
                        selectedOption = selectedOption,
                        onOptionSelected = { onOptionSelected(it) })

                    //Notes
                    CustomTextField(
                        labelRes = R.string.hint_notes,
                        iconRes = R.drawable.ic_notes,
                        onValueChange = { notesValues = it},
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        maxLengthRes = R.integer.max_length_notes,
                        singleLines = false,
                        isRequired = false,
                        isClean = isClean
                    )

                    //checkbox auth terms and conditions
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Switch(
                            checked = acceptValue,
                            onCheckedChange = {
                                acceptValue = it
                            },
                            modifier = Modifier.padding(dimensionResource(R.dimen.common_padding_default)),
                            thumbContent = if (acceptValue) {
                                {
                                    Icon(
                                        imageVector = Icons.Filled.Check,
                                        contentDescription = null,
                                        modifier = Modifier.size(SwitchDefaults.IconSize),
                                    )
                                }
                            } else {
                                {
                                    Icon(
                                        imageVector = Icons.Filled.Block,
                                        contentDescription = null,
                                        modifier = Modifier.size(SwitchDefaults.IconSize),
                                    )
                                }
                            }
                        )
                        Text(
                            text = stringResource(R.string.text_accept_conditions),
                            style = Typography.titleLarge,
                            modifier = Modifier.clickable{ acceptValue = !acceptValue }
                        )
                    }

                    //save Button
                    val saveAlpha = if(!isKeyboardVisible) 1f else 0f
                    Button(onClick = {
                        onSaveData()
                    },
                        modifier = Modifier
                            .padding(vertical = dimensionResource(R.dimen.common_padding_default))
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.common_padding_default))
                            .alpha(saveAlpha),
                        enabled = canRegister,
                        shape = CutCornerShape(dimensionResource(R.dimen.common_padding_default))
                    ) {
                        Text(stringResource(R.string.text_btn_register))
                        Icon(
                            painterResource(R.drawable.ic_save),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = dimensionResource(R.dimen.common_padding_min))
                        )
                    }
                }
            }
        }


        //Processing Screen
        ProcessingView(isProcessing)
    }
}