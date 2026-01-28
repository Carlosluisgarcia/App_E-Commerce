package com.losluis.ecommerce.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// ============================================
// SISTEMA DE TIPOGRAFÍA - LOS LUIS
// ============================================
// Define todos los estilos de texto usados en la aplicación
// Seguimos Material Design 3 Typography Scale

val Typography = Typography(

    // ========================================
    // DISPLAY - Para textos muy grandes y destacados
    // ========================================
    // Uso: Pantallas de bienvenida, splash screen
    displayLarge = TextStyle(
        fontFamily = FontFamily.Serif,       // Tipografía Serif elegante (simula tu logo)
        fontWeight = FontWeight.Bold,        // Peso: Negrita
        fontSize = 35.sp,                    // Tamaño: 57sp (muy grande)
        lineHeight = 64.sp,                  // Altura de línea para buena legibilidad
        letterSpacing = (-0.25).sp           // Espaciado entre letras (negativo = más apretado)
    ),

    // ========================================
    // HEADLINE - Para títulos principales
    // ========================================
    // Uso: "Los Luis" en header, títulos de pantallas
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Serif,       // Serif para títulos importantes
        fontWeight = FontWeight.SemiBold,    // Peso: Semi-negrita (600)
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),

    headlineSmall = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),

    // ========================================
    // TITLE - Para títulos de secciones
    // ========================================
    // Uso: "Featured", "Búsquedas Recientes", nombres de productos
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,     // Sans-serif (Roboto en Android)
        fontWeight = FontWeight.Bold,        // Peso: Negrita
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),

    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,      // Peso: Medio (500)
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),

    // ========================================
    // BODY - Para textos de contenido
    // ========================================
    // Uso: Descripciones de productos, párrafos informativos
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,      // Peso: Normal (400)
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),

    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),

    // ========================================
    // LABEL - Para textos de interfaz
    // ========================================
    // Uso: Botones, tabs, chips
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),

    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)