package com.candroid.loginpagesample.ui.theme

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import com.candroid.loginpagesample.SharedPreferencesManager

@Composable
fun HomePage(context: Context){
    val preferencesManager = remember { SharedPreferencesManager(context) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "HomePage")
    }
    val name = preferencesManager.getData("name")
    val surname = preferencesManager.getData("surname")
    Toast.makeText(context, "Welcome $name $surname", Toast.LENGTH_SHORT).show()
}