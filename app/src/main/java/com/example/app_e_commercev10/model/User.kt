package com.example.app_e_commercev10.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String, // id unico
    val name:String, // nombre de ususario
    val email:String, // email de el usuario
    val password:String =" ", // contras;a
    val phone:String = " ", // numero de cel
    val address:String =" ",// direccion
)


