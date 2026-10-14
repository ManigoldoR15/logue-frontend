package com.logue.frontend.data

// Datos fijos de ejemplo para las vistas de paramédico y administrador.
data class Incidente(
    val tipo: String,
    val ubicacion: String,
    val etaMinutos: Int,
    val prioridad: String,
)

data class Unidad(
    val clave: String,
    val estado: String,
)

object DatosEjemplo {
    val incidentes = listOf(
        Incidente("Accidente vial", "Av. Insurgentes Sur 1602, CDMX", 6, "Alta"),
        Incidente("Persona inconsciente", "Parque México, Col. Condesa", 9, "Alta"),
        Incidente("Disparos", "Calle Moneda 12, Centro", 4, "Crítica"),
        Incidente("Múltiples heridos", "Metro Pantitlán, Línea 1", 12, "Crítica"),
        Incidente("Accidente vial", "Periférico Norte km 8", 15, "Media"),
    )

    val unidades = listOf(
        Unidad("AMB-101", "Disponible"),
        Unidad("AMB-102", "En servicio"),
        Unidad("AMB-103", "Disponible"),
        Unidad("AMB-104", "En servicio"),
        Unidad("AMB-105", "Disponible"),
        Unidad("AMB-106", "En taller"),
        Unidad("AMB-107", "Disponible"),
    )

    val unidadesDisponibles get() = unidades.count { it.estado == "Disponible" }
}
