
package com.example.app_e_commercev10.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Crear el DataStore (una sola vez, a nivel de Context)
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "los_luis_session"  // Nombre del archivo de preferencias
)

class SessionManager(private val context: Context) {

     // Cada dato que guardas necesita una clave única

    companion object {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val USER_EMAIL   = stringPreferencesKey("user_email")
        val USER_NAME    = stringPreferencesKey("user_name")
        val USER_ID      = stringPreferencesKey("user_id")
    }
// lee si esta logueado !
   val isLoggedIn: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_LOGGED_IN] ?: false  // Si no , false
        }

  // guarda sesion en preferencias
       suspend fun saveSession(userId: String, email: String, name: String) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = true
            preferences[USER_ID]      = userId
            preferences[USER_EMAIL]   = email
            preferences[USER_NAME]    = name
        }
    }
//borra la sesion al ir pal loguin
      suspend fun clearSession() {
        context.dataStore.edit { preferences ->
            preferences.clear()  // Borra todo
        }
    }
}