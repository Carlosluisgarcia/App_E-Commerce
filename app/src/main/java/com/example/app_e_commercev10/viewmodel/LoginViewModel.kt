package com.example.app_e_commercev10.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_e_commercev10.data.UserDAO
import com.example.app_e_commercev10.model.User
import kotlinx.coroutines.launch


class LoginViewModel(
    private val userDAO: UserDAO // desde el navgraph
) : ViewModel() {


    var email by mutableStateOf("")
        private set


    var password by mutableStateOf("")
        private set


    var isLoading by mutableStateOf(false)
        private set


    var errorMessage by mutableStateOf<String?>(null)
        private set


    var authenticatedUser by mutableStateOf<User?>(null)
        private set


    fun updateEmail(value: String) {
        email = value
        if (errorMessage != null) errorMessage = null
    }


    fun updatePassword(value: String) {
        password = value
        if (errorMessage != null) errorMessage = null
    }


    fun login(onSuccess: () -> Unit) {


        // Validar que el email no esté vacío
        if (email.isBlank()) {
            errorMessage = "Por favor ingresa tu email"
            return
        }

        // Validar que la contraseña no esté vacía
        if (password.isBlank()) {
            errorMessage = "Por favor ingresa tu contraseña"
            return
        }


        // validar email (usuario@dominio.com)
        val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")

        if (!emailPattern.matches(email)) {
            errorMessage = "El formato del email no es válido"
            return
        }


        viewModelScope.launch {
            try {
                // Activar estado de carga
                isLoading = true
                errorMessage = null

                // Existe un usuario con ese email y password
                val user = userDAO.login(
                    email = email.trim().lowercase(),  // Normalizar email
                    password = password
                )


                if (user != null) {
                    //  LOGIN EXITOSO

                    authenticatedUser = user
                    isLoading = false


                    println("✅ Login exitoso: ${user.name} (${user.email})")

                    // 💾 AQUÍ DEBERÍAS GUARDAR LA SESIÓN
                    // En producción, guarda el user.id en SharedPreferences
                    // o DataStore para mantener la sesión activa
                    // saveUserSession(user.id)

                    // Navegar a Home
                    onSuccess()

                } else {
                    //  CREDENCIALES INCORRECTAS

                    isLoading = false
                    errorMessage = "Email o contraseña incorrectos"

                    // Log del intento fallido
                    println(" Login fallido para: $email")
                }

            } catch (e: Exception) {
                //  ERROR EN LA BASE DE DATOS

                isLoading = false
                errorMessage = "Error al iniciar sesión: ${e.message}"

                // Log del error
                println(" Error en login: ${e.message}")
                e.printStackTrace()
            }
        }
    }


    fun loginAsGuest(onSuccess: () -> Unit) {
        // Crear un usuario "invitado" temporal
        authenticatedUser = User(
            id = "guest",
            name = "Invitado",
            email = "guest@losluis.com",
            password = "",
            phone = "",
            address = ""
        )

        println("👤 Ingreso como invitado")

        // Navegar a Home inmediatamente
        onSuccess()
    }


    fun logout() {
        authenticatedUser = null
        email = ""
        password = ""
        errorMessage = null

        // 💾 AQUÍ DEBERÍAS LIMPIAR LA SESIÓN
        // clearUserSession()

        println("👋 Sesión cerrada")
    }


    fun clearError() {
        errorMessage = null
    }


    fun clearForm() {
        email = ""
        password = ""
        errorMessage = null
    }
}