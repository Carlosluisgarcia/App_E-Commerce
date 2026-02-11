package com.example.app_e_commercev10.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_e_commercev10.data.ProductDAO
import com.example.app_e_commercev10.data.SessionManager
import com.example.app_e_commercev10.model.Product
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class HomeViewModel(
    private val productDAO: ProductDAO , // DAO inyectado desde NavGraph
    private val sessionManager: SessionManager
) : ViewModel() {


    val products: StateFlow<List<Product>> = productDAO.getAllProducts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),  // Se mantiene activo 5s después de que nadie observe
            initialValue = emptyList()  // Lista vacía mientras carga
        )


    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()


    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> = _categories.asStateFlow()


    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory.asStateFlow()


    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()


    val filteredProducts: StateFlow<List<Product>> = combine(
        products,
        searchQuery,
        selectedCategory
    ) { productList, query, category ->
        productList
            .filter { product ->
                // Filtrar por categoría
                val matchesCategory = category == null || product.category == category

                // Filtrar por búsqueda (case-insensitive)
                val matchesSearch = query.isBlank() ||
                        product.name.contains(query, ignoreCase = true)

                matchesCategory && matchesSearch
            }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )


    init {
        // Cargar categorías al iniciar
        loadCategories()
    }


    fun loadCategories() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _errorMessage.value = null

                // Obtener categorías únicas de la BD
                val categoriesList = productDAO.getAllCategories()
                _categories.value = categoriesList

                _isLoading.value = false

            } catch (e: Exception) {
                _isLoading.value = false
                _errorMessage.value = "Error al cargar categorías: ${e.message}"
            }
        }
    }


    fun refreshProducts() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _errorMessage.value = null

                // El Flow ya está activo, solo reseteamos el loading
                kotlinx.coroutines.delay(500) // Delay cosmético para UX

                _isLoading.value = false

            } catch (e: Exception) {
                _isLoading.value = false
                _errorMessage.value = "Error al recargar: ${e.message}"
            }
        }
    }


    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            try {
                _errorMessage.value = null

                productDAO.deleteProduct(product)

            } catch (e: Exception) {
                _errorMessage.value = "Error al eliminar: ${e.message}"
            }
        }
    }


    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }


    fun selectCategory(category: String?) {
        _selectedCategory.value = category
    }


    fun clearError() {
        _errorMessage.value = null
    }


//    fun logout(onSuccess: () -> Unit) {
//        viewModelScope.launch {
//            sessionManager.clearSession()
//            onSuccess()
//            }
//    }
}

