package com.example.app_e_commercev10.ui.navegation

sealed class Screen(val route: String){
    // Pantalla de bienvenida !
    object Splash :Screen ("splash")
    // Pantallas de login o autenticacion
          //Pantalla de login
    object Login : Screen ("login")
         //Pantalla de registro
    object Register : Screen("register")
    // Pantalla principal o home
    object Home : Screen ("home")

    // Pantalla de aggProductos
    object AddProduct : Screen("add_product?productId={productId}") {
        fun createRouteForAdd(): String = "add_product"
        fun createRouteForEdit(productId: String): String =
            "add_product?productId=$productId"
    }
}