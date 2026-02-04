package com.example.app_e_commercev10.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_e_commercev10.data.ProductDAO
import com.example.app_e_commercev10.model.Product
import kotlinx.coroutines.launch
import java.util.UUID


class AddProductViewModel(
    private val productDAO: ProductDAO  // 👈 DAO inyectado desde NavGraph
) : ViewModel() {



    var name by mutableStateOf("")
        private set  // Solo el ViewModel puede modificarlo

    var description by mutableStateOf("")
        private set

    var price by mutableStateOf("")
        private set

    var imageUrl by mutableStateOf("")
        private set

    var category by mutableStateOf("")
        private set

    var stock by mutableStateOf("")
        private set

    // ═══════════════════════════════════════════════════════════
    // 🎨 ESTADOS DE LA UI
    // ═══════════════════════════════════════════════════════════


    var isLoading by mutableStateOf(false)
        private set


    var errorMessage by mutableStateOf<String?>(null)
        private set



    fun updateName(value: String) {
        name = value
        // Limpiar error al editar
        if (errorMessage != null) errorMessage = null
    }

    fun updateDescription(value: String) {
        description = value
        if (errorMessage != null) errorMessage = null
    }

    fun updatePrice(value: String) {
        price = value
        if (errorMessage != null) errorMessage = null
    }

    fun updateImageUrl(value: String) {
        imageUrl = value
        if (errorMessage != null) errorMessage = null
    }

    fun updateCategory(value: String) {
        category = value
        if (errorMessage != null) errorMessage = null
    }

    fun updateStock(value: String) {
        stock = value
        if (errorMessage != null) errorMessage = null
    }


    fun saveProduct(onSuccess: () -> Unit) {


        // Validar que los campos obligatorios no estén vacíos
        if (name.isBlank()) {
            errorMessage = "El nombre del producto es obligatorio"
            return
        }

        if (description.isBlank()) {
            errorMessage = "La descripción es obligatoria"
            return
        }

        if (category.isBlank()) {
            errorMessage = "La categoría es obligatoria"
            return
        }


        val priceValue = price.toDoubleOrNull()

        if (priceValue == null) {
            errorMessage = "El precio debe ser un número válido (usa punto para decimales)"
            return
        }

        if (priceValue < 0) {
            errorMessage = "El precio no puede ser negativo"
            return
        }


        val stockValue = stock.toIntOrNull()

        if (stockValue == null) {
            errorMessage = "El stock debe ser un número entero válido"
            return
        }

        if (stockValue < 0) {
            errorMessage = "El stock no puede ser negativo"
            return
        }


        // Si el usuario ingresó una URL, validar formato básico
        if (imageUrl.isNotBlank()) {
            val urlPattern = Regex("^(http|https)://.*")
            if (!urlPattern.matches(imageUrl)) {
                errorMessage = "La URL de imagen debe empezar con http:// o https://"
                return
            }
        }


        // Lanzar corutina para operación asíncrona
        viewModelScope.launch {

            try {
                // Activar estado de carga
                isLoading = true
                errorMessage = null

                // Generar ID único para el producto
                val productId = UUID.randomUUID().toString()

                // Crear objeto Product con todos los datos validados
                val newProduct = Product(
                    id = productId,
                    name = name.trim(),  // trim() elimina espacios al inicio/final
                    description = description.trim(),
                    price = priceValue,
                    imageUrl = imageUrl.trim(),
                    category = category.trim(),
                    stock = stockValue,

                )

                // ✨ INSERTAR EN ROOM DATABASE ✨
                productDAO.insertProduct(newProduct)

                // Log de éxito (útil para debug)
                println("✅ Producto guardado exitosamente: $newProduct")

                // Desactivar loading
                isLoading = false

                // Navegar atrás (el callback cierra la pantalla)
                onSuccess()

            } catch (e: Exception) {
                // Si algo falla, capturar error y mostrar mensaje
                isLoading = false
                errorMessage = "Error al guardar el producto: ${e.message}"

                // Log del error para debug
                println("❌ Error al guardar producto: ${e.message}")
                e.printStackTrace()
            }
        }
    }


    fun clearForm() {
        name = ""
        description = ""
        price = ""
        imageUrl = ""
        category = ""
        stock = ""
        errorMessage = null
    }


    fun clearError() {
        errorMessage = null
    }
}

