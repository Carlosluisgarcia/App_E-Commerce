package com.example.app_e_commercev10.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_e_commercev10.R
import com.example.app_e_commercev10.ui.navegation.Screen
import com.example.app_e_commercev10.viewmodel.RegisterViewModel
import com.losluis.ecommerce.ui.theme.GoldDark
import com.losluis.ecommerce.ui.theme.GoldPrimary
import com.losluis.ecommerce.ui.theme.TextPrimary
import com.losluis.ecommerce.ui.theme.TextSecondary


@Composable
fun RegisterScreenPlaceholder(
    viewModel: RegisterViewModel,
    onNavegateToLogin:()-> Unit,
    onNavigateToHome:()-> Unit

) {

    val name = viewModel.name
    val email = viewModel.email
    val password = viewModel.password
    val confirmPassword = viewModel.confirmPassword
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage
    val isPasswordVisible = viewModel.isPasswordVisible



    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0E0E0E),
            Color(0xFF2D2D2D),
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
                .size(150.dp)
                .padding(top = 20.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.72f)
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = Modifier.height(28.dp))


            Text(
                text = "Create Account",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 26.sp
                ),
                color = GoldPrimary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))


            OutlinedTextField(
                value = name,
                onValueChange = { viewModel.updateName(it) },
                label = { Text("Full Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    ,
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = GoldPrimary,      // Borde dorado al escribir
                    unfocusedBorderColor = Color(0xFFCCCCCC),  // Borde gris sin foco
                    focusedLabelColor = GoldPrimary,       // Label dorado al escribir
                    unfocusedLabelColor = TextSecondary    // Label gris sin foco
                )
            )

            Spacer(modifier = Modifier.height(14.dp))



            Spacer(modifier = Modifier.height(14.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { viewModel.updateEmail(it) },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                ,
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email  // Teclado con @
                ),
                enabled = !isLoading,
                isError = errorMessage != null && email.isBlank(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = GoldPrimary,
                    unfocusedBorderColor = Color(0xFFCCCCCC),
                    focusedLabelColor = GoldPrimary,
                    unfocusedLabelColor = TextSecondary
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { viewModel.updatePassword(it) },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    ,
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                visualTransformation = PasswordVisualTransformation(),  // Oculta texto
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                enabled = !isLoading,
                isError = errorMessage != null && password.isBlank(),
                colors = OutlinedTextFieldDefaults.colors(

                    focusedBorderColor = GoldPrimary,
                    unfocusedBorderColor = Color(0xFFCCCCCC),
                    focusedLabelColor = GoldPrimary,
                    unfocusedLabelColor = TextSecondary
                )
            )
            Spacer(modifier = Modifier.height(14.dp))


            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { viewModel.updateConfirmPassword(it) },  // 👈 Llamar ViewModel
                label = { Text("Confirm Password") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                visualTransformation =
                    PasswordVisualTransformation()
                ,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                enabled = !isLoading,
                isError = errorMessage != null && confirmPassword.isBlank(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = GoldPrimary,
                    unfocusedBorderColor = Color(0xFFCCCCCC),
                    focusedLabelColor = GoldPrimary,
                    unfocusedLabelColor = TextSecondary
                )
            )



            Spacer(modifier = Modifier.height(8.dp))


            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))


            Button(
                onClick = { viewModel.register(onSuccess = onNavegateToLogin) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GoldDark,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(25.dp),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        text = "Sign Up",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontSize = 16.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            TextButton(
                onClick = { viewModel.register(onSuccess = onNavegateToLogin) },
                enabled = !isLoading
            ) {
                Text(
                    text = "¿Ya tienes cuenta? Inicia sesión",
                    color = TextSecondary
                )
            }




        }
    }
}