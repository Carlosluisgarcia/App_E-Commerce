package com.example.app_e_commercev10.ui.screens.home

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_e_commercev10.R

import com.example.app_e_commercev10.viewmodel.HomeViewModel
import com.losluis.ecommerce.ui.theme.BackgroundGray

import com.losluis.ecommerce.ui.theme.GoldPrimary
import com.losluis.ecommerce.ui.theme.TextPrimary
import com.losluis.ecommerce.ui.theme.TextSecondary
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.font.FontWeight

import com.example.app_e_commercev10.model.Product




@Composable
fun HomeScreenPlaceholder(
    viewModel: HomeViewModel,
    onLogout: () -> Unit,
    onNavigateToAddProduct: () -> Unit,

) {

    // Observar los estados del ViewModel

    val products by viewModel.filteredProducts.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()  // 👈 NUEVO: Query del ViewModel


    //  visibilidad de búsqueda
    var isSearchVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.outlineVariant)
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            // TopBar normal (siempre visible)
            HomeTopBar(
                onSearchClick = {
                    isSearchVisible = !isSearchVisible
                }
            )


            // BARRA DE BÚSQUEDA

            AnimatedVisibility(
                visible = isSearchVisible,
                enter = expandVertically(
                    animationSpec = tween(durationMillis = 300)
                ) + fadeIn(
                    animationSpec = tween(durationMillis = 300)
                ),
                exit = shrinkVertically(
                    animationSpec = tween(durationMillis = 300)
                ) + fadeOut(
                    animationSpec = tween(durationMillis = 300)
                )
            ) {
                SearchBarComponent(
                    searchText = searchQuery,  // de el viewmodel
                    onSearchTextChange = { viewModel.updateSearchQuery(it) }  //se catualiza aka
                )
            }


            // CONTENIDO PRINCIPAL

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                // Mostrar según el estado
                when {
                    // Estado: Cargando
                    isLoading -> {
                        LoadingScreen()
                    }
                    // Estado: Error
                    errorMessage != null -> {
                        ErrorScreen(
                            message = errorMessage ?: "Error desconocido",
                            onRetry = { viewModel.refreshProducts() }
                        )
                    }
                    // Estado: Contenido exitoso
                    else -> {
                        ContentScreen(
                            products = products,
                            searchText = searchQuery
                        )
                    }
                }
            }
        }

        // Botón flotante
        FloatingActionButton(
            onClick = onNavigateToAddProduct,
            containerColor = GoldPrimary,
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
                .size(64.dp),
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Agregar",
                modifier = Modifier.size(28.dp)
            )
        }
    }
}






@Composable
fun HomeTopBar(
    onSearchClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 4.dp,
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.outlineVariant)

                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notificaciones",
                    tint = GoldPrimary,
                    modifier = Modifier.size(30.dp)
                )
            }

            Row(modifier = Modifier.width(120.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.isotipo_sin_fondo),
                    contentDescription = "isotipo",
                    modifier = Modifier.size(60.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.nombre__sensillo_sin_fondo),
                    contentDescription = "nombre sensillo logo ",
                    modifier = Modifier
                        .size(60.dp)


                )
            }


            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar",
                    tint = GoldPrimary,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}






@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CircularProgressIndicator(
                color = GoldPrimary,
                modifier = Modifier.size(48.dp)
            )

            Text(
                text = "Cargando productos...",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )
        }
    }
}






@Composable
fun ErrorScreen(
    message: String,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "Error",
                tint = Color(0xFFD32F2F),
                modifier = Modifier.size(64.dp)
            )

            Text(
                text = "¡Oops!",
                style = MaterialTheme.typography.titleLarge,
                color = TextPrimary
            )

            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = GoldPrimary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Reintentar",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Reintentar")
            }
        }
    }
}




@Composable
fun ContentScreen(
    products: List<Product>,
    searchText: String
) {
    // Filtrar productos si hay texto de búsqueda (recalcula con remember si hay cambios)
    val filteredProducts = remember(products, searchText) {
        if (searchText.isBlank()) {
            products
        } else {
            products.filter { product ->
                product.name.contains(searchText, ignoreCase = true) ||
                        product.category.contains(searchText, ignoreCase = true)
            }
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 120.dp)
    ) {

        // --- seccion de favoritos ---
        item {
            Spacer(modifier = Modifier.height(20.dp))
            SectionTitle(title = "Seccion de Favoritos")
            Spacer(modifier = Modifier.height(12.dp))

            if (filteredProducts.isEmpty()) {
                // Mensaje cuando no hay productos
                EmptyProductsMessage(searchText)
            } else {
                // Mostrar productos reales
                FeaturedGrid(products = filteredProducts.take(5))
            }
        }

        // --- seccion de categorias ---
        item {
            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle(title = "Seccion de Categorias")
            Spacer(modifier = Modifier.height(12.dp))
            CategoryIcons()
        }

        // --- seccion de nuevos agregados ---
        item {
            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle(title = "Nuevos Agregados")
            Spacer(modifier = Modifier.height(12.dp))
            NewProductsGrid(products = products)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// ============================================
// COMPONENTE: MENSAJE DE PRODUCTOS VACÍOS
// ============================================

@Composable
fun EmptyProductsMessage(searchText: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "Sin resultados",
                tint = TextSecondary,
                modifier = Modifier.size(48.dp)
            )

            Text(
                text = if (searchText.isBlank()) {
                    "No hay productos disponibles"
                } else {
                    "No se encontraron productos para \"$searchText\""
                },
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}













@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(
            fontSize = 16.sp,
            letterSpacing = 1.sp
        ),
        color = GoldPrimary,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}

@Composable
fun FeaturedGrid(products: List<Product>) {
    Row(modifier = Modifier.padding(horizontal = 20.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            if (products.isEmpty()) {
                // Si no hay productos, mostrar mensaje
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay productos destacados",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )
                }
            } else {

                LazyHorizontalGrid(
                    rows = GridCells.Fixed(1),
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {

                    items(
                        count = products.size
                    ) { index ->
                        ProductCard(product = products[index])
                    }
                }
            }
        }
    }
}



@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .width(120.dp)
            .height(120.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = BackgroundGray
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Imagen/Icono del producto
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(
                        color = Color(0xFFE8E8E8),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Imagen del producto",
                    tint = Color(0xFFAAAAAA),
                    modifier = Modifier.size(28.dp)
                )
            }

            // Nombre y precio del producto
            Column {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextPrimary,
                    maxLines = 1,
                    fontSize = 11.sp
                )
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = GoldPrimary,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun CategoryIcons() {
    val categories = listOf(
        Pair("Aceo", Icons.Default.Home),
        Pair("Tegnologia", Icons.Default.Star),
        Pair("Ropa", Icons.Default.Favorite),
        Pair("Más", Icons.Default.Add)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        categories.forEach { (label, icon) ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(64.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .background(
                            color = Color(0xFFF5F0E0),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = GoldPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = TextSecondary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
@Composable
fun NewProductsGrid(products: List<Product>) {
    Row(modifier = Modifier.padding(horizontal = 20.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        ) {
            if (products.isEmpty()) {
                // Si no hay productos
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay productos nuevos",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )
                }
            } else {
                // 👇 CAMBIO: Grid con productos reales
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {

                    items(
                        count = products.size
                    ) { index ->
                        ProductCard(product = products[index])
                    }
                }
            }
        }
    }
}



// ============================================
// COMPONENTE: BARRA DE BÚSQUEDA
// ============================================
// Se muestra/oculta cuando se toca el icono de búsqueda

@Composable
fun SearchBarComponent(
    searchText: String,
    onSearchTextChange: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 2.dp,
        color = MaterialTheme.colorScheme.outlineVariant
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextField(
                value = searchText,
                onValueChange = onSearchTextChange,
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                placeholder = {
                    Text(
                        text = "Buscar productos, marcas o categorías",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary,
                        maxLines = 1
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar",
                        tint = TextSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = BackgroundGray,
                    unfocusedContainerColor = BackgroundGray,
                    disabledContainerColor = BackgroundGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = GoldPrimary,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary
                ),
                shape = RoundedCornerShape(12.dp),
                textStyle = MaterialTheme.typography.bodyMedium
            )

            IconButton(
                onClick = { /* Acción de cámara */ },
                modifier = Modifier
                    .size(50.dp)
                    .background(
                        color = BackgroundGray,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Escanear código",
                    tint = TextPrimary,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}




