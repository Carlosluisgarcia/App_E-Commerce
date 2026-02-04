package com.example.app_e_commercev10.data

import androidx.room.*
import com.example.app_e_commercev10.model.Product
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDAO {


    //obtener todos los productos
    @Query("SELECT * FROM products ORDER BY name ASC")
    fun getAllProducts(): Flow<List<Product>>

   // obtener productos por categoria
    @Query("SELECT * FROM products WHERE category = :category ORDER BY name ASC")
    fun getProductsByCategory(category: String): Flow<List<Product>>

    // buscar productos por nombre
    @Query("SELECT * FROM products WHERE name LIKE '%' || :searchQuery || '%' ORDER BY name ASC")
    fun searchProducts(searchQuery: String): Flow<List<Product>>

    // obtener producto por id
    @Query("SELECT * FROM products WHERE id = :productId LIMIT 1")
    suspend fun getProductById(productId: String): Product?

    // obtener categorias
    @Query("SELECT DISTINCT category FROM products ORDER BY category ASC")
    suspend fun getAllCategories(): List<String>





    // incertar productos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    // incertar varios productos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<Product>)

    // actualizar productos
    @Update
    suspend fun updateProduct(product: Product)

// eliminar productos
    @Delete
    suspend fun deleteProduct(product: Product)

// eliminar todos los productos
    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()
}

