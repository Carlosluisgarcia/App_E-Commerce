package com.example.app_e_commercev10.ui.navegation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.app_e_commercev10.data.EcommerceDatabase
import com.example.app_e_commercev10.ui.screens.auth.LoginScreenPlaceholder
import com.example.app_e_commercev10.ui.screens.auth.RegisterScreenPlaceholder
import com.example.app_e_commercev10.ui.screens.home.HomeScreenPlaceholder
import com.example.app_e_commercev10.ui.screens.product.AddProductScreen
import com.example.app_e_commercev10.ui.screens.splash.SplashScreenPlaceholder
import com.example.app_e_commercev10.viewmodel.AddProductViewModel
import com.example.app_e_commercev10.viewmodel.HomeViewModel
import com.example.app_e_commercev10.viewmodel.LoginViewModel
import com.example.app_e_commercev10.viewmodel.RegisterViewModel
import com.example.app_e_commercev10.data.SessionManager
import com.example.app_e_commercev10.viewmodel.SplashViewModel
import com.example.app_e_commercev10.data.dataStore
import kotlinx.coroutines.launch

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route
) {


    val context = LocalContext.current
    val database = EcommerceDatabase.getDatabase(context)
    val sessionManager = SessionManager(context)

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {



        composable(route = Screen.Splash.route) {

            val splashViewModel: SplashViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    @Suppress("UNCHECKED_CAST")
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return SplashViewModel(sessionManager) as T
                    }
                }
            )

            SplashScreenPlaceholder(
                viewModel = splashViewModel,
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }




        composable(route = Screen.Login.route) {


            val loginViewModel: LoginViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    @Suppress("UNCHECKED_CAST")
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        // Crear LoginViewModel pasándole el UserDAO
                        return LoginViewModel(database.userDAO() , sessionManager = SessionManager(context)) as T
                    }
                }
            )


            LoginScreenPlaceholder(
                viewModel = loginViewModel,
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                },
                onNavigateToHome = {
                    // Limpiar stack: no poder volver al login con back
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }
                    }
                },
                onGuestLogin = {
                    // Mismo comportamiento que login normal
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }


        composable(route = Screen.Register.route) {


            val registerViewModel: RegisterViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    @Suppress("UNCHECKED_CAST")
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return RegisterViewModel(database.userDAO()) as T
                    }
                }
            )


            RegisterScreenPlaceholder(
                viewModel = registerViewModel,
                onNavegateToLogin = {
                    navController.popBackStack()
                },
                onNavigateToHome = { navController.navigate(Screen.Home.route) }
            )



        }


        composable(route = Screen.Home.route) {
            val homeViewModel: HomeViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    @Suppress("UNCHECKED_CAST")
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return HomeViewModel(database.productDAO() , sessionManager) as T
                    }
                }
            )

            HomeScreenPlaceholder(
                viewModel = homeViewModel,
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onNavigateToAddProduct = {
                    navController.navigate(Screen.AddProduct.createRouteForAdd())
                },
                onNavigateToEditProduct = { productId ->
                    navController.navigate(Screen.AddProduct.createRouteForEdit(productId))
                }
            )
        }







        composable(
            route = Screen.AddProduct.route,  // "add_product?productId={productId}"

            // ───────────────────────────────────────────────────
            // DEFINIR ARGUMENTOS QUE ACEPTA
            // ───────────────────────────────────────────────────
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType  // Tipo de dato
                    nullable = true            // Puede ser null
                    defaultValue = null        // Valor por defecto
                }
            )
        ) { backStackEntry ->

             val productId = backStackEntry.arguments?.getString("productId")



            val addProductViewModel: AddProductViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    @Suppress("UNCHECKED_CAST")
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return AddProductViewModel(database.productDAO()) as T
                    }
                }
            )


            LaunchedEffect(productId) {

                if (productId != null) {

                    addProductViewModel.loadProduct(productId)
                } else {

                    addProductViewModel.clearForm()
                }
            }

            AddProductScreen(
                viewModel = addProductViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }



    }
}



