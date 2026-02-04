package com.example.app_e_commercev10.ui.screens.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_e_commercev10.R
import com.example.app_e_commercev10.viewmodel.LoginViewModel
import com.losluis.ecommerce.ui.theme.GoldPrimary
import com.losluis.ecommerce.ui.theme.TextSecondary


@Composable
fun LoginScreenPlaceholder(
    viewModel: LoginViewModel,
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit,
    onGuestLogin: () -> Unit
) {

    val email = viewModel.email
    val password = viewModel.password
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage


    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0E0E0E),
            Color(0xFF232222),
            Color(0xFF090909),

            )
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundGradient)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Image(
            painter = painterResource(id = R.drawable.isotipo_sin_fondo),
            contentDescription = "Logo de la aplicación",
            modifier = Modifier
                .size(85.dp)
                .padding(top = 20.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.solo_nombre_entero_sin_fondo),
            contentDescription = "Nombre de el logo ",
            modifier = Modifier.width(220.dp)

        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier,
            text = "Welcome!",
            style = MaterialTheme.typography.displayLarge.copy(fontSize = 28.sp),
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Experimenta la mejor de las colecciones.",
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.updateEmail(it)},
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,



        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.updatePassword(it) },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),

            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(8.dp))


        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick =  {viewModel.login(onSuccess = onNavigateToHome)} ,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            enabled = !isLoading
        ) {
            if (isLoading) {
                // loading spinner
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    strokeWidth = 2.dp
                )
            } else {

                Text("Iniciar Sesión")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = {

                viewModel.loginAsGuest(onSuccess = onGuestLogin)
            },
            modifier = Modifier.fillMaxWidth()

        ) {
            Text("Continuar como Invitado")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onNavigateToRegister) {
            Text("¿No tienes cuenta? Regístrate")
        }



    }




}


