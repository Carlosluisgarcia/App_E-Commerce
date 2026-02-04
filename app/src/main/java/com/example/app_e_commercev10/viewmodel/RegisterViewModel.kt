
package com.example.app_e_commercev10.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_e_commercev10.data.UserDAO
import com.example.app_e_commercev10.model.User
import kotlinx.coroutines.launch
import java.util.UUID


class RegisterViewModel(
    private val userDAO: UserDAO  //desde el navgraph
) : ViewModel() {


    //nombre
    var name by mutableStateOf("")
        private set

    //email
    var email by mutableStateOf("")
        private set

    //contrase;a
    var password by mutableStateOf("")
        private set

    //contraseña de confirmacion
    var confirmPassword by mutableStateOf("")
        private set


    var isLoading by mutableStateOf(false)
        private set

    //mensaje de error
    var errorMessage by mutableStateOf<String?>(null)
        private set

    //usuario registrado
    var registeredUser by mutableStateOf<User?>(null)
        private set

    // contarse;a visible o no
    var isPasswordVisible by mutableStateOf(false)
        private set

    // para actualizar campos
    fun updateName(value: String) {
        name = value
        if (errorMessage != null) errorMessage = null
    }

    fun updateEmail(value: String) {
        email = value
        if (errorMessage != null) errorMessage = null
    }

    fun updatePassword(value: String) {
        password = value
        if (errorMessage != null) errorMessage = null
    }

    fun updateConfirmPassword(value: String) {
        confirmPassword = value
        if (errorMessage != null) errorMessage = null
    }

   //visibilidad de contrase;a si o no
    fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
    }


    fun register(onSuccess: () -> Unit) {

       //validar nombre
        if (name.isBlank()) {
            errorMessage = "Por favor ingresa tu nombre completo"
            return
        }

        if (name.length < 3) {
            errorMessage = "El nombre debe tener al menos 3 caracteres"
            return
        }

        // Validar que el nombre solo contenga letras y espacios
        val namePattern = Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")
        if (!namePattern.matches(name)) {
            errorMessage = "El nombre solo puede contener letras"
            return
        }

        //validar email
        if (email.isBlank()) {
            errorMessage = "Por favor ingresa tu email"
            return
        }

        //  validar formato de email
        val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
        if (!emailPattern.matches(email)) {
            errorMessage = "El formato del email no es válido"
            return
        }

        //validar contrase;a

        if (password.isBlank()) {
            errorMessage = "Por favor ingresa una contraseña"
            return
        }

        if (password.length < 6) {
            errorMessage = "La contraseña debe tener al menos 6 caracteres"
            return
        }


        //validar confirmacion de contrase;a
        if (confirmPassword.isBlank()) {
            errorMessage = "Por favor confirma tu contraseña"
            return
        }

        if (password != confirmPassword) {
            errorMessage = "Las contraseñas no coinciden"
            return
        }


        viewModelScope.launch {
            try {
                // Activar estado de carga
                isLoading = true
                errorMessage = null


                val existingUser = userDAO.getUserByEmail(
                    email = email.trim().lowercase()
                )

                if (existingUser != null) {
                    //  EMAIL YA REGISTRADO
                    isLoading = false
                    errorMessage = "Este email ya está registrado. ¿Deseas iniciar sesión?"
                    return@launch
                }


                // Generar UUID único para el usuario
                val userId = UUID.randomUUID().toString()

                // Crear objeto User
                val newUser = User(
                    id = userId,
                    name = name.trim(),
                    email = email.trim().lowercase(),
                    password = password,
                    phone = "",
                    address = ""
                )

                //  INSERTAR EN ROOM DATABASE
                userDAO.insertUser(newUser)

                // Guardar referencia al usuario registrado
                registeredUser = newUser

                // Log de éxito
                println(" Usuario registrado exitosamente: ${newUser.name} (${newUser.email})")

                // Desactivar loading
                isLoading = false

                 onSuccess()



            } catch (e: Exception) {
                //  ERROR AL REGISTRAR

                isLoading = false
                errorMessage = "Error al crear la cuenta: ${e.message}"

                // Log del error
                println(" Error en registro: ${e.message}")
                e.printStackTrace()
            }
        }
    }


    fun clearForm() {
        name = ""
        email = ""
        password = ""
        confirmPassword = ""
        errorMessage = null
        registeredUser = null
        isPasswordVisible = false
    }


    fun clearError() {
        errorMessage = null
    }


    fun getPasswordStrength(): Int {
        if (password.isEmpty()) return 0

        var strength = 0

        // +1 si tiene más de 6 caracteres
        if (password.length >= 6) strength++

        // +1 si tiene más de 10 caracteres
        if (password.length >= 10) strength++

        // +1 si tiene mayúsculas y minúsculas
        if (password.any { it.isUpperCase() } && password.any { it.isLowerCase() }) {
            strength++
        }

        // +1 si tiene números
        if (password.any { it.isDigit() }) strength++

        // +1 si tiene símbolos
        if (password.any { !it.isLetterOrDigit() }) strength++

        return strength.coerceAtMost(4)  // Máximo 4
    }
}



