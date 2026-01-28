package com.example.app_e_commercev10.ui.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

// Grafo de navegacion !
/*
@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route
) {
    // NavHost -> contenedor donde se muestran las pantalla !
    NavHost(
        navController = navController, // es le que controla la navegacion
        startDestination = startDestination // empesamos aki
    ) {

        // Pantalla de inicio la precentacion (Ruta : Splash)
        composable(route = Screen.Splash.route) {
            //  Pantalla temporal (la haremos después)
            SplashScreenPlaceholder(
                onNavigateToLogin = {
                    // Navegar al login y limpiar el stack
                    navController.navigate(Screen.Login.route) {
                        //  limpia todas las pantallas anteriores
                        popUpTo(Screen.Splash.route) {
                            inclusive = true  // Incluye el splash en la limpieza
                        }
                    }
                }
            )
        }

        //Pantalla de login (Ruta : login )
        composable(route = Screen.Login.route) {
            LoginScreenPlaceholder(
                onNavigateToRegister = {
                    // navegar al registro
                    navController.navigate(Screen.Register.route)
                },
                onNavigateToHome = {
                    // navegar al home
                    navController.navigate(Screen.Home.route) {
                        // limpiar pantallas anterires la de login
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }

                    }

                },
                onGuestLogin = {
                    // login como invitado sin autenticarce
                    navController.navigate(Screen.Home.route)
                    {
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }

                    }

                }

            )
        }

        //Pantalla de registro (Ruta : Register)
        composable(route = Screen.Register.route){
            RegisterScreenPlaceholder(
                onNavigateToLogin ={
                    // volver al login
                    navController.popBackStack() // solo retrocede de a la anterior (Login )
                },

                onNavigateToHome={
                    // ir a home despues de un buen registro
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route){
                            inclusive=true
                        }
                    }
                }

            )
        }


        //Pantalla de home (Ruta : Home)
        composable(route =Screen.Home.route){
            HomeScreenPlaceholder(
                onLogout = {
                    // al cerrar sesion volver al login
                    navController.navigate(Screen.Login.route){
                        popUpTo(Screen.Home.route){
                            inclusive=true
                        }
                    }
                }
            )
        }



    }
}*/