package com.candroid.loginpagesample

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.candroid.loginpagesample.ui.theme.HomePage
import com.candroid.loginpagesample.ui.theme.LoginPageSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginPageSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavController(context = this@MainActivity)
                }
            }
        }
    }
}

@Composable
fun MainNavController(context: Context){
    val navController = rememberNavController()
    NavHost(navController = navController, "LoginPage"){
        composable("LoginPage"){
            LoginPage(context, navController)
        }

        composable("HomePage"){
            HomePage(context)
        }
    }
}