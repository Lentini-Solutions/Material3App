package com.example.jcmaterialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jcmaterialapp.components.CustomDialog
import com.example.jcmaterialapp.model.User
import com.example.jcmaterialapp.screens.MainPreview
import com.example.jcmaterialapp.screens.MainView
import com.example.jcmaterialapp.ui.theme.JCMaterialAppTheme
import com.example.jcmaterialapp.utils.userFormatter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCMaterialAppTheme {
                var openDialog by remember { mutableStateOf(false) }
                var cleanForm by remember { mutableStateOf(false) }
                var userFilled = User(name = "", surname = "", height = 0)
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainView(
                        modifier = Modifier.padding(innerPadding),
                        onSave = { user ->
                            userFilled = User(
                                name = user.name,
                                surname = user.surname,
                                height = user.height,
                                birthdate = user.birthdate,
                                occupation = user.occupation,
                                notes = user.notes
                            )
                            openDialog = true
                        },
                        isClean = cleanForm,
                        onCleaned = { cleanForm = false }
                    )

                    if(openDialog){
                        CustomDialog(
                            info = userFormatter(userFilled),
                            title = R.string.dialog_title,
                            confirmRes = R.string.clean){ clean ->
                            cleanForm = clean
                            openDialog = false
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JCMaterialAppTheme {
        MainPreview()

    }
}