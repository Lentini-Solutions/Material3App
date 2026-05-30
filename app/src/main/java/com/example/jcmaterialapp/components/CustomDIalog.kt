package com.example.jcmaterialapp.components


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.jcmaterialapp.R

@Composable
fun CustomDialog(
    info: String,
    title: Int,
    confirmRes: Int,
    onDismissRequest: (Boolean) -> Unit,
){

    AlertDialog(
        onDismissRequest= { onDismissRequest(false) },
        title = { stringResource(title) },
        text = { Text(text = info) },
        confirmButton = {
            Button(onClick = { onDismissRequest(true) }) {
                Text(text = stringResource(confirmRes))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest(false) }) {
                Text(text = stringResource(R.string.btn_cancel_dialog_custom))
            }
        }
    )
}