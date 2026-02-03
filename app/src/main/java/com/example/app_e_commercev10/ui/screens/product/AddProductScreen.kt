package com.example.app_e_commercev10.ui.screens.product



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.losluis.ecommerce.ui.theme.TextPrimary
import com.losluis.ecommerce.ui.theme.GoldPrimary
import com.losluis.ecommerce.ui.theme.TextDisabled

// ============================================
// ADD PRODUCT SCREEN
// ============================================
// Pantalla placeholder para agregar productos
// Tu compañero trabajará aquí

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Agregar Producto",
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = GoldPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "📦",
                style = MaterialTheme.typography.displayLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Pantalla de Agregar Producto",
                style = MaterialTheme.typography.titleLarge,
                color = TextDisabled
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "para gustavo ! ",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}