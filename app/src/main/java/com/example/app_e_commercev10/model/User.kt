package com.example.app_e_commercev10.model

data class User(
    val id: String, // id unico
    val name:String, // nombre de ususario
    val email:String, // email de el usuario
    val password:String =" ", // contras;a
    val phone:String = " ", // numero de cel
    val address:String =" ",// direccion
)


