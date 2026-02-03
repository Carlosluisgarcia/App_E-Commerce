package com.example.app_e_commercev10.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.losluis.ecommerce.ui.theme.*


// ============================================
// ESQUEMA DE COLORES - MODO CLARO
// ============================================
// Define cómo se mapean nuestros colores a los roles de Material Design
// Este será el tema principal de Los Luis (fondo blanco)

private val LightColorScheme = lightColorScheme(
    // COLORES PRINCIPALES
    primary = GoldPrimary,              // Color principal de la marca (dorado)
    onPrimary = Color(0xFFFFFFFF),             // Texto SOBRE el color principal (blanco)
    primaryContainer = GoldLight,       // Variante más clara del principal
    onPrimaryContainer = TextPrimary,   // Texto sobre el contenedor principal

    // COLORES SECUNDARIOS (para acentos adicionales)
    secondary = GoldDark,               // Dorado oscuro para acentos
    onSecondary = TextOnGold,           // Texto sobre secundario

    // FONDOS
    background = BackgroundWhite,       // Fondo principal de pantallas
    onBackground = TextPrimary,         // Texto sobre el fondo principal

    // SUPERFICIES (Cards, modales, etc.)
    surface = SurfaceGray,              // Color de tarjetas y elementos elevados
    onSurface = TextPrimary,            // Texto sobre superficies
    surfaceVariant = BackgroundGray,    // Variante de superficie (más gris)
    onSurfaceVariant = TextSecondary,   // Texto sobre variante de superficie

    // COLORES DE ESTADO
    error = ErrorRed,                   // Color para errores
    onError = TextOnGold,               // Texto sobre error (blanco)

    // BORDES Y DIVISORES
    outline = DividerGray,              // Líneas divisorias y bordes
    outlineVariant = IconGray           // Variante más sutil de bordes
)

// ============================================
// ESQUEMA DE COLORES - MODO OSCURO (OPCIONAL)
// ============================================
// Por ahora usaremos un tema oscuro básico
// Más adelante puedes personalizarlo para modo noche

private val DarkColorScheme = darkColorScheme(
    primary = GoldLight,                // En modo oscuro usamos dorado más claro
    onPrimary = BlackElegant,           // Texto sobre dorado (negro)
    primaryContainer = GoldDark,
    onPrimaryContainer = TextOnGold,

    secondary = GoldPrimary,
    onSecondary = BlackElegant,

    background = BlackElegant,          // Fondo oscuro
    onBackground = TextOnGold,          // Texto claro sobre fondo oscuro

    surface = GrayDark,
    onSurface = TextOnGold,
    surfaceVariant = Color(0xFF3D3D3D),
    onSurfaceVariant = Color(0xFFCCCCCC),

    error = ErrorRed,
    onError = TextOnGold,

    outline = Color(0xFF4D4D4D),
    outlineVariant = Color(0xFFDCDADA)
)

// ============================================
// FUNCIÓN DEL TEMA PRINCIPAL
// ============================================
// Esta función envuelve toda tu aplicación
// Parámetros:
//   - darkTheme: Si usar modo oscuro (detecta automáticamente del sistema)
//   - content: El contenido de tu app (pantallas)

@Composable
fun LosLuisTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),  // Detecta si el sistema está en modo oscuro
    content: @Composable () -> Unit              // El contenido de tu app
) {
    // Selecciona el esquema de colores según el modo
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    // Configura la barra de estado del sistema (arriba del teléfono)
    val view = LocalView.current
    if (!view.isInEditMode) {  // Solo si NO estamos en preview de Android Studio
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()  // Color de barra de estado
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    // Aplica el tema a todo el contenido
    MaterialTheme(
        colorScheme = colorScheme,  // Esquema de colores
        typography = Typography,     // Tipografías que definimos
        content = content            // Tu app
    )
}