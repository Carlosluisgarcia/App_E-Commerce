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


/*
@Composable
fun TestScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "Los Luis",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Text(
            text = "E-Commerce",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Botón Principal",
                style = MaterialTheme.typography.labelLarge
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Tarjeta de Producto",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Esta es una descripción de prueba",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}*/

@Preview(showBackground = true)
@Composable
fun TestScreenPreview() {
    LosLuisTheme {
       // TestScreen()
    }
}


/*

---

## ✅ **VERIFICACIÓN**

Después de crear los 3 archivos, tu estructura debería verse así:
```
com.example.app_e_commercev10
└── ui.theme
├── Color.kt
├── Type.kt
└── Theme.kt
└── MainActivity.kt

 */