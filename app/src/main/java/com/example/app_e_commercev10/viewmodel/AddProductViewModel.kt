package com.example.app_e_commercev10.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_e_commercev10.model.Product
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID

class AddProductViewModel : ViewModel() {

    // Estado de los campos del formulario
    var name by mutableStateOf("")
    var description by mutableStateOf("")
    var price by mutableStateOf("")
    var imageUrl by mutableStateOf("")
    var category by mutableStateOf("")
    var stock by mutableStateOf("")

    // Estado de la interfaz
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    // Lógica para guardar el producto
    fun saveProduct(onSuccess: () -> Unit) {
        // 1. Validaciones básicas
        if (name.isBlank() || description.isBlank() || category.isBlank()) {
            errorMessage = "Por favor completa los campos de texto."
            return
        }

        val priceValue = price.toDoubleOrNull()
        val stockValue = stock.toIntOrNull()

        if (priceValue == null || priceValue < 0) {
            errorMessage = "El precio debe ser un número válido."
            return
        }
        if (stockValue == null || stockValue < 0) {
            errorMessage = "El stock debe ser un número entero válido."
            return
        }

        // 2. Crear el objeto Product según tu modelo
        // Usamos UUID para generar un ID único automáticamente
        val newProduct = Product(
            id = UUID.randomUUID().toString(),
            name = name.trim(),
            description = description.trim(),
            price = priceValue,
            imageUrl = imageUrl.trim(),
            category = category.trim(),
            stock = stockValue,
            isAvailabel = stockValue > 0 // Lógica automática: si hay stock, está disponible
        )

        // 3. Proceso de "Exportación a Base de Datos"
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                // ============================================================
                // TODO: AQUÍ VA LA LLAMADA A TU BASE DE DATOS (FIREBASE/ROOM)
                // Ejemplo: firestore.collection("products").add(newProduct)
                // ============================================================

                // Simulamos un retraso de red de 2 segundos
                delay(2000)

                println("Producto guardado exitosamente: $newProduct")

                isLoading = false
                onSuccess() // Navegar atrás si todo salió bien

            } catch (e: Exception) {
                isLoading = false
                errorMessage = "Error al guardar: ${e.message}"
            }
        }
    }
}