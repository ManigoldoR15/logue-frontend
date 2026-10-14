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
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.logue.frontend.data.DatosEjemplo
import com.logue.frontend.data.Incidente

@Composable
fun ParamedicoScreen(nombre: String, onCerrarSesion: () -> Unit) {
    Scaffold(topBar = { LogueTopBar("Paramédico", nombre, onCerrarSesion) }) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Text(
                    "Incidentes asignados (${DatosEjemplo.incidentes.size})",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
            items(DatosEjemplo.incidentes) { IncidenteCard(it) }
        }
    }
}

@Composable
private fun IncidenteCard(incidente: Incidente) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(incidente.tipo, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(incidente.ubicacion, color = MaterialTheme.colorScheme.secondary)
                AssistChip(onClick = {}, label = { Text("Prioridad ${incidente.prioridad}") })
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "${incidente.etaMinutos}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                )
                Text("min ETA", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
