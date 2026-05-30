package com.example.jcmaterialapp.components

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.jcmaterialapp.R
import com.example.jcmaterialapp.utils.convertMillisDate

@Composable
fun CustomDateTextField(
    modifier: Modifier,
    labelRes: Int,
    selectedDate: Long? = null,
    onShowModal: ()-> Unit
){

    OutlinedTextField(
        value = selectedDate?.let { convertMillisDate(it) } ?: "",
        onValueChange = {},
        label = {
            Text(text = stringResource(labelRes), maxLines = 1, overflow = TextOverflow.Ellipsis)
        },
        trailingIcon = {
            Icon(painterResource(R.drawable.ic_date), contentDescription = null)
        },
        /*
        * this modifier, use coroutines, to "win in time" to click input to keyboard to can
        * show modal DatePicker.
        * */
        modifier = modifier
            .padding(dimensionResource(R.dimen.common_padding_default))
            .pointerInput(selectedDate){
            awaitEachGesture {
                awaitFirstDown(pass = PointerEventPass.Initial)
                val upEvent = waitForUpOrCancellation(pass= PointerEventPass.Initial)
                if(upEvent != null) onShowModal()
            }
        }
    )



}