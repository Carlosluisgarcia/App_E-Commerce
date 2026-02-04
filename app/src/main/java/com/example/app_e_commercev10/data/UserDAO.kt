package com.example.app_e_commercev10.data

import androidx.room.*
import com.example.app_e_commercev10.model.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDAO {


   //verifica credenciales en el loguin
    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    suspend fun login(email: String, password: String): User?




    //verifica di exixte imail
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    //obtener usuario por id
    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    suspend fun getUserById(userId: String): User?


   //registrar nuevo usuario
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User)

    //actualizar datos de usuario
    @Update
    suspend fun updateUser(user: User)

   //eliminar cuenta de usuario
    @Delete
    suspend fun deleteUser(user: User)


    //obtener todos los usuarios
    @Query("SELECT * FROM users ORDER BY name ASC")
    fun getAllUsers(): Flow<List<User>>


    //contar todos los usuarios
    @Query("SELECT COUNT(*) FROM users")
    suspend fun getUserCount(): Int
}

