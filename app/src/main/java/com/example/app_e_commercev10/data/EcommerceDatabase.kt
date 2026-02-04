package com.example.app_e_commercev10.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.app_e_commercev10.model.Product
import com.example.app_e_commercev10.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

/**
 * ═══════════════════════════════════════════════════════════════
 * 🗄️ DATABASE CENTRAL - LOS LUIS E-COMMERCE
 * ═══════════════════════════════════════════════════════════════
 *
 * Esta clase es el CORAZÓN de Room Database.
 * Define la estructura completa de la base de datos local.
 */
@Database(
    entities = [Product::class, User::class],  // 👈 Tablas que tendrá la BD
    version = 1,  // 👈 Versión inicial de la BD
    exportSchema = false  // 👈 No exportar esquema JSON (útil para debug)
)
abstract class EcommerceDatabase : RoomDatabase() {

    // ═══════════════════════════════════════════════════════════
    // 📍 DECLARACIÓN DE DAOs
    // ═══════════════════════════════════════════════════════════
    // Room implementará estas funciones automáticamente

    abstract fun productDAO(): ProductDAO
    abstract fun userDAO(): UserDAO

    // ═══════════════════════════════════════════════════════════
    // 🏗️ SINGLETON PATTERN (UNA SOLA INSTANCIA)
    // ═══════════════════════════════════════════════════════════

    companion object {

        /**
         * @Volatile asegura que los cambios a INSTANCE sean visibles
         * para todos los threads inmediatamente.
         *
         * Es crítico en aplicaciones multi-thread (como Android)
         */
        @Volatile
        private var INSTANCE: EcommerceDatabase? = null

        /**
         * OBTENER LA INSTANCIA DE LA BASE DE DATOS
         *
         * @param context Contexto de Android (necesario para crear la BD)
         * @return La única instancia de EcommerceDatabase
         *
         * ═══════════════════════════════════════════════════════
         * 🎓 ¿POR QUÉ SINGLETON?
         * ═══════════════════════════════════════════════════════
         *
         * Crear una instancia de Room es COSTOSO:
         * - Lee archivos del disco
         * - Inicializa SQLite
         * - Valida esquema de tablas
         *
         * Si cada pantalla crea su propia instancia:
         * ❌ Consumo excesivo de memoria
         * ❌ Posibles inconsistencias de datos
         * ❌ Bloqueos entre transacciones
         *
         * Con Singleton:
         * ✅ Una sola instancia compartida
         * ✅ Mejor rendimiento
         * ✅ Datos consistentes
         */
        fun getDatabase(context: Context): EcommerceDatabase {
            // Si INSTANCE ya existe, devolverla inmediatamente
            return INSTANCE ?: synchronized(this) {
                // synchronized = solo un thread puede ejecutar esto a la vez

                // Builder de Room Database
                val instance = Room.databaseBuilder(
                    context.applicationContext,  // Usar applicationContext (no se destruye)
                    EcommerceDatabase::class.java,  // Clase de la BD
                    "los_luis_ecommerce.db"  // Nombre del archivo de BD
                )

                    // ═══════════════════════════════════════════════════
                    // ⚙️ CONFIGURACIONES OPCIONALES
                    // ═══════════════════════════════════════════════════

                    /**
                     * fallbackToDestructiveMigration()
                     *
                     * ⚠️ SI CAMBIAS LA VERSIÓN DE LA BD, BORRARÁ TODO
                     *
                     * Ejemplo:
                     * - Versión 1: tabla users con 4 campos
                     * - Versión 2: agregaste campo 'isAdmin'
                     *
                     * SIN fallback → App crashea si no defines migración
                     * CON fallback → Borra BD antigua y crea nueva (pierdes datos)
                     *
                     * 💡 Para producción, debes usar migraciones reales:
                     *    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                     *
                     * Para desarrollo/aprendizaje, fallback está bien.
                     */
                    .fallbackToDestructiveMigration()

                    /**
                     * addCallback() - EJECUTAR CÓDIGO AL CREAR/ABRIR LA BD
                     *
                     * Útil para:
                     * - Insertar datos iniciales (productos de ejemplo)
                     * - Crear usuario admin por defecto
                     * - Logging de debug
                     */
                    .addCallback(object : RoomDatabase.Callback() {

                        /**
                         * onCreate() - SE EJECUTA SOLO LA PRIMERA VEZ
                         *
                         * Cuando la app se instala y la BD no existe,
                         * Room crea las tablas y luego llama a este método.
                         */
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            // Lanzar corutina en hilo de I/O (no bloquea UI)
                            CoroutineScope(Dispatchers.IO).launch {

                                // ══════════════════════════════════════════
                                // 👤 INSERTAR USUARIO DE PRUEBA
                                // ══════════════════════════════════════════
                                val testUser = User(
                                    id = UUID.randomUUID().toString(),
                                    name = "Carlos Test",
                                    email = "test@losluis.com",
                                    password = "1234",
                                    phone = "+53 12345678",
                                    address = "Pinar del Río, Cuba"
                                )

                                INSTANCE?.userDAO()?.insertUser(testUser)

                                // ══════════════════════════════════════════
                                // 📦 INSERTAR PRODUCTOS DE EJEMPLO
                                // ══════════════════════════════════════════
                                val sampleProducts = listOf(
                                    Product(
                                        id = UUID.randomUUID().toString(),
                                        name = "Laptop HP Pavilion 15",
                                        description = "Laptop de alto rendimiento con procesador Intel Core i5, 8GB RAM, 256GB SSD",
                                        price = 650.00,
                                        imageUrl = "https://example.com/laptop.jpg",
                                        category = "Electrónica",
                                        stock = 5,

                                    ),
                                    Product(
                                        id = UUID.randomUUID().toString(),
                                        name = "Mouse Logitech MX Master 3",
                                        description = "Mouse inalámbrico ergonómico con 7 botones programables",
                                        price = 99.99,
                                        imageUrl = "https://example.com/mouse.jpg",
                                        category = "Accesorios",
                                        stock = 15,

                                    ),
                                    Product(
                                        id = UUID.randomUUID().toString(),
                                        name = "Teclado Mecánico RGB",
                                        description = "Teclado mecánico con switches Cherry MX Blue e iluminación RGB",
                                        price = 129.99,
                                        imageUrl = "https://example.com/keyboard.jpg",
                                        category = "Accesorios",
                                        stock = 8,

                                    ),
                                    Product(
                                        id = UUID.randomUUID().toString(),
                                        name = "Monitor Samsung 27\" 4K",
                                        description = "Monitor 4K UHD con tecnología HDR y panel IPS",
                                        price = 399.99,
                                        imageUrl = "https://example.com/monitor.jpg",
                                        category = "Electrónica",
                                        stock = 3,

                                    ),
                                    Product(
                                        id = UUID.randomUUID().toString(),
                                        name = "Audífonos Sony WH-1000XM4",
                                        description = "Audífonos Bluetooth con cancelación de ruido premium",
                                        price = 349.99,
                                        imageUrl = "https://example.com/headphones.jpg",
                                        category = "Audio",
                                        stock = 12,

                                    ),
                                    Product(
                                        id = UUID.randomUUID().toString(),
                                        name = "Camiseta Los Luis Edition",
                                        description = "Camiseta de algodón 100% con logo bordado",
                                        price = 24.99,
                                        imageUrl = "https://example.com/shirt.jpg",
                                        category = "Ropa",
                                        stock = 50,

                                    )
                                )

                                INSTANCE?.productDAO()?.insertProducts(sampleProducts)

                                println("✅ Base de datos inicializada con datos de prueba")
                            }
                        }

                        /**
                         * onOpen() - SE EJECUTA CADA VEZ QUE SE ABRE LA BD
                         *
                         * Útil para logging o validaciones.
                         */
                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                            println("📂 Base de datos abierta: ${db.path}")
                        }
                    })

                    .build()  // Construir la instancia final

                // Guardar la instancia en INSTANCE para futuros usos
                INSTANCE = instance

                // Devolver la instancia
                instance
            }
        }

       //cerrar base de datos
        fun closeDatabase() {
            INSTANCE?.close()
            INSTANCE = null
        }
    }
}

