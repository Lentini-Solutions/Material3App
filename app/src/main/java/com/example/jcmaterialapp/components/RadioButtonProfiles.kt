package com.example.jcmaterialapp.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.jcmaterialapp.R
import com.example.jcmaterialapp.ui.theme.Typography

@Composable
fun RadioButtonProfiles(profiles: List<String>, selectedOption: String, onOptionSelected: (String) -> Unit){

    Log.i("RadioButton", "selected Option: $selectedOption")

    Column(Modifier
        .padding(dimensionResource(R.dimen.common_padding_min))
        .selectableGroup()
    ) {
        Text(
            text = stringResource(R.string.section_occupation),
            style = Typography.titleLarge
        )
        profiles.forEach { profile ->
            Row(Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .selectable(
                    selected = (profile == selectedOption),
                    onClick = {
                        onOptionSelected(profile)
                    },
                    role = Role.RadioButton
                )
            ) {
                RadioButton(
                    selected = (profile == selectedOption),
                    onClick = null,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.tertiary
                    )
                )
                Text(
                    text = profile,
                    modifier = Modifier
                        .padding(start =dimensionResource(R.dimen.common_padding_min))
                )
            }
        }
    }
}