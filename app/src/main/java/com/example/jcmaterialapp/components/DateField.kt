package com.example.jcmaterialapp.components

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.jcmaterialapp.R
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismissRequest: () -> Unit
) {

    val datePickerState = rememberDatePickerState(System.currentTimeMillis())

    DatePickerDialog(
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            Button(
                onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                    onDismissRequest()
                }
            ) {
                Text(text = stringResource(R.string.btn_confirm_dialog_custom))
            }
        }, dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(text = stringResource(R.string.btn_cancel_dialog_custom))
            }
        })
    {
        DatePicker(state = datePickerState)
    }
}