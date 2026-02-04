package com.example.app_e_commercev10.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    val id: String , // id unico de producto
    val name: String , // nombre del producto
    val description: String , // descripcion breve del producto
    val price: Double , // precio del producto
    val imageUrl: String , // url de la imagen del producto
    val category: String , // categoria del producto
    val stock : Int = 0 , //cantidad disponible inicialmente
     // si esta disponible o no , inicialmente si
)