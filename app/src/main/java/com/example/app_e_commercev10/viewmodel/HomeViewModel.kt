package com.example.app_e_commercev10.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_e_commercev10.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


// maneja la logica de la pantalla principal (lo de productos y tal )
class HomeViewModel : ViewModel() {
    //Solo los puede modificar el viewModel
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    private val _isLoading = MutableStateFlow(false)
    private val _errorMessage = MutableStateFlow<String?>(null)


    //Lo que la pantalla puede leer
    val products: StateFlow<List<Product>> = _products
    val isLoading: StateFlow<Boolean> = _isLoading
    val errorMessage: StateFlow<String?> = _errorMessage


    init {
        loadProducts() // cargar prudictos
    }

    private fun loadProducts() {
        viewModelScope.launch {
             _isLoading.value =true
            _errorMessage.value=null

            try {
                val listaejemplo = listOf(
                    Product(
                        id = "1",
                        name = "Laptop HP Pavilion",
                        description = "Laptop de alto rendimiento",
                        price = 650.00,
                        imageUrl = "",
                        category = "Electrónica",
                        stock = 5
                    ),
                    Product(
                        id = "2",
                        name = "Mouse Logitech",
                        description = "Mouse inalámbrico",
                        price = 25.00,
                        imageUrl = "",
                        category = "Accesorios",
                        stock = 15
                    ),
                    Product(
                        id = "3",
                        name = "Teclado Mecánico",
                        description = "Teclado RGB",
                        price = 80.00,
                        imageUrl = "",
                        category = "Accesorios",
                        stock = 8
                    )
                )

                _products.value = listaejemplo
            } catch (e: Exception){
                _errorMessage.value = "Error al cargar los datos del producto : ${e.message}"
            } finally {
                _isLoading.value = false
            }

        }
    }
}
