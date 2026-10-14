package com.logue.frontend.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.logue.frontend.data.DatosEjemplo

@Composable
fun AdministradorScreen(nombre: String, onCerrarSesion: () -> Unit) {
    Scaffold(topBar = { LogueTopBar("Administrador", nombre, onCerrarSesion) }) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Text("Resumen", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            }
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    ResumenCard(
                        titulo = "Incidentes activos",
                        valor = DatosEjemplo.incidentes.size,
                        modifier = Modifier.weight(1f),
                    )
                    ResumenCard(
                        titulo = "Unidades disponibles",
                        valor = DatosEjemplo.unidadesDisponibles,
                        total = DatosEjemplo.unidades.size,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
            item {
                Text("Unidades", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            items(DatosEjemplo.unidades) { unidad ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(Modifier.padding(16.dp)) {
                        Text(unidad.clave, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        Text(
                            unidad.estado,
                            color = if (unidad.estado == "Disponible") Color(0xFF1B7F3B) else MaterialTheme.colorScheme.secondary,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ResumenCard(titulo: String, valor: Int, modifier: Modifier = Modifier, total: Int? = null) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier,
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                if (total != null) "$valor / $total" else "$valor",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Text(titulo, color = Color.White)
        }
    }
}
