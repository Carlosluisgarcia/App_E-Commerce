package com.losluis.ecommerce.ui.theme

import androidx.compose.ui.graphics.Color

// ============================================
// COLORES PRINCIPALES - DORADOS DE LA MARCA
// ============================================
// Estos son los colores extraídos del logo de Los Luis
// Los usaremos para botones, elementos destacados e iconos activos

val GoldPrimary = Color(0xFFD4AF37)      // Dorado principal - Botones y elementos clave
val GoldLight = Color(0xFFE8C547)        // Dorado claro - Highlights y degradados superiores
val GoldDark = Color(0xFFB8941F)         // Dorado oscuro - Sombras y degradados inferiores

// ============================================
// COLORES DE FONDO
// ============================================
// Fondos claros para máxima legibilidad en una app de e-commerce

val BackgroundWhite = Color(0xFFFFFFFF)  // Fondo principal de pantallas
val BackgroundGray = Color(0xFFBDB8B8)   // Fondo secundario para secciones alternadas
val SurfaceGray = Color(0xFFFAFAFA)      // Fondo de tarjetas (cards) de productos

// ============================================
// COLORES DE TEXTO
// ============================================
// Jerarquía de textos para buena legibilidad

val TextPrimary = Color(0xFF1A1A1A)      // Títulos y textos principales (casi negro)
val TextSecondary = Color(0xFF666666)    // Descripciones y textos secundarios (gris medio)
val TextOnGold = Color(0xFFFFFFFF)       // Texto sobre botones dorados (blanco)
val TextDisabled = Color(0xFFBDBDBD)     // Texto deshabilitado (gris claro)

// ============================================
// COLORES OSCUROS (Para Splash y elementos dark)
// ============================================
// Usados en pantalla de inicio y elementos con fondo oscuro

val BlackElegant = Color(0xFF1A1A1A)     // Negro elegante del logo
val GrayDark = Color(0xFF2D2D2D)         // Gris oscuro para variaciones

// ============================================
// COLORES DE ESTADO (Feedback al usuario)
// ============================================
// Colores semánticos para comunicar estados de la aplicación

val SuccessGreen = Color(0xFF4CAF50)     // Confirmaciones exitosas (ej: "Producto agregado")
val ErrorRed = Color(0xFFD32F2F)         // Errores y validaciones fallidas
val WarningOrange = Color(0xFFFF9800)    // Advertencias (ej: "Stock bajo")
val InfoBlue = Color(0xFF2196F3)         // Información general

// ============================================
// COLORES AUXILIARES
// ============================================
// Otros colores que podríamos necesitar

val DividerGray = Color(0xFFE0E0E0)      // Líneas divisorias
val IconGray = Color(0xFF9E9E9E)         // Iconos inactivos
val RippleGray = Color(0x1F000000)       // Efecto de presionado (ripple) - 12% negro

/*
---

### **🧠 EXPLICACIÓN LÍNEA POR LÍNEA (Secciones importantes):**

1. **`package com.losluis.ecommerce.ui.theme`**
- Define dónde vive este archivo en la estructura del proyecto
- Permite importarlo desde otros archivos con `import com.losluis.ecommerce.ui.theme.GoldPrimary`

2. **`val GoldPrimary = Color(0xFFD4AF37)`**
- `val` = valor inmutable (no se puede cambiar después de definirse)
- `Color(0xFF...)` = Constructor de color en formato hexadecimal
- `0xFF` = Canal alfa (opacidad 100%). Si quisieras 50% transparente sería `0x80`
- `D4AF37` = Código hexadecimal RGB del dorado

3. **¿Por qué tantos colores si solo tenemos dorado?**
- Una app necesita **jerarquía visual**: no todo puede ser dorado
- Los textos necesitan **alto contraste** con el fondo (negro sobre blanco)
- Los estados (éxito, error) necesitan colores **universalmente reconocibles**

---

## 🔤 **PASO 3: DEFINIR TIPOGRAFÍAS (Type.kt)**

Ahora definiremos los estilos de texto que usaremos en toda la app.

### **📖 CONCEPTOS IMPORTANTES:**

**¿Qué es Typography en Compose?**
- Es un objeto que agrupa TODOS los estilos de texto de tu app
- Material Design 3 define roles como: `displayLarge`, `headlineMedium`, `bodySmall`, etc.
- Cada rol tiene un propósito específico

**¿Por qué usar roles predefinidos?**
- ✅ **Consistencia automática:** Un `headlineMedium` siempre se ve igual
- ✅ **Accesibilidad:** Los tamaños están pensados para buena legibilidad
- ✅ **Adaptabilidad:** Funcionan bien en diferentes tamaños de pantalla

### **Jerarquía de Texto que usaremos:**
```
displayLarge    → Pantallas de bienvenida, números grandes (34sp)
headlineMedium  → Títulos de secciones principales (28sp)
titleLarge      → Nombres de productos destacados (22sp)
titleMedium     → Títulos de cards, categorías (16sp)
bodyLarge       → Descripciones de productos (16sp)
bodyMedium      → Textos generales (14sp)
labelLarge      → Textos de botones (14sp, uppercase)

*/
