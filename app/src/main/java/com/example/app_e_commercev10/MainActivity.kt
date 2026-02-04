package com.example.app_e_commercev10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.app_e_commercev10.ui.navegation.NavGraph
import com.example.app_e_commercev10.ui.screens.auth.LoginScreenPlaceholder
import com.example.app_e_commercev10.ui.screens.splash.SplashScreenPlaceholder
import com.example.app_e_commercev10.ui.theme.LosLuisTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LosLuisTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Crear el NavController
                    val navController = rememberNavController()

                    // Iniciar el sistema de navegación
                    NavGraph(navController = navController)
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun TestScreenPreview() {
    LosLuisTheme {
       // TestScreen()
    }
}
