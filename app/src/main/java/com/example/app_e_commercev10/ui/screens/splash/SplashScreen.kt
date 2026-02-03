package com.example.app_e_commercev10.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import com.example.app_e_commercev10.ui.navegation.Screen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import com.example.app_e_commercev10.R
import kotlinx.coroutines.delay
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material3.Text

import androidx.compose.runtime.LaunchedEffect

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreenPlaceholder(
    onNavigateToLogin: ()-> Unit
){
    LaunchedEffect(key1 = Unit) {
        delay(3000)  // Esperar 4 segundos
        onNavigateToLogin()  // Llamar a la función de navegación
    }

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0E0E0E),
            Color(0xFF2D2D2D),
            Color(0xFF090909),

        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundGradient),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center ,
            modifier = Modifier.padding(24.dp)
        ) {

            Image(

                painter = painterResource(id = R.drawable.logo_sin_fondo),
                contentDescription = "Logo de la aplicación",
                modifier = Modifier.size(300.dp)
            )

            Spacer(modifier = Modifier.height(70.dp))

            Text(
                text = "INICIANDO TU MEJOR EXPERIENCIA",
                style = MaterialTheme.typography.labelSmall.copy(
                    letterSpacing = 2.sp
                ),
                color = Color(0xFF666666),  // Gris sutil
                textAlign = TextAlign.Center
            )



        }
    }

}