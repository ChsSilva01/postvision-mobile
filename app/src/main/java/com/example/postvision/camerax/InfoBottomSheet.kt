package com.example.postvision.camerax

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.util.Locale
import com.example.postvision.R

/**
 * Clase que define el contenido del BottomSheet de Información.
 * Contiene filas para mostrar el tiempo de inferencia, controles de confianza de detección,
 * seguimiento y presencia, así como spinners para seleccionar el modelo y el delegado.
 *
 * @param viewModel El ViewModel que contiene la lógica de la aplicación y el estado.
 * @param inferenceTime El tiempo de inferencia actual en milisegundos.
 */
class InfoBottomSheet {
    @SuppressLint("DefaultLocale")
    @Composable
    fun InfoBottomSheetContent(viewModel: MainViewModel, inferenceTime: Long) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Fila de Inference Time
            InfoRow(
                label = "Inference Time",
                value = String.format(Locale.US, "%d ms", inferenceTime)
            )

            // Fila de Control de Confianza de Detección
            ControlRow(
                label = "Detection Confidence",
                value = String.format(Locale.US, "%.2f", viewModel.currentMinPoseDetectionConfidence),
                onMinusClick = { viewModel.setMinPoseDetectionConfidence(viewModel.currentMinPoseDetectionConfidence - 0.1f) },
                onPlusClick = { viewModel.setMinPoseDetectionConfidence(viewModel.currentMinPoseDetectionConfidence + 0.1f) }
            )

            // Fila de Control de Confianza de Seguimiento
            ControlRow(
                label = "Tracking Confidence",
                value = String.format(Locale.US, "%.2f", viewModel.currentMinPoseTrackingConfidence),
                onMinusClick = { viewModel.setMinPoseTrackingConfidence(viewModel.currentMinPoseTrackingConfidence - 0.1f) },
                onPlusClick = { viewModel.setMinPoseTrackingConfidence(viewModel.currentMinPoseTrackingConfidence + 0.1f) }
            )

            // Fila de Control de Confianza de Presencia
            ControlRow(
                label = "Presence Confidence",
                value = String.format(Locale.US, "%.2f", viewModel.currentMinPosePresenceConfidence),
                onMinusClick = { viewModel.setMinPosePresenceConfidence(viewModel.currentMinPosePresenceConfidence - 0.1f) },
                onPlusClick = { viewModel.setMinPosePresenceConfidence(viewModel.currentMinPosePresenceConfidence + 0.1f) }
            )

            // Spinner de Modelo
            ModelSpinner(
                currentModel = viewModel.currentModel,
                onModelSelected = { viewModel.setModel(it) }
            )

            // Spinner de Delegado
            DelegateSpinner(
                currentDelegate = viewModel.currentDelegate,
                onDelegateSelected = { viewModel.setDelegate(it) }
            )
        }
    }

    @Composable
    fun ControlRow(label: String, value: String, onMinusClick: () -> Unit, onPlusClick: () -> Unit) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = MaterialTheme.typography.bodyMedium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onMinusClick) {
                    Icon(painterResource(R.drawable.ic_minus), contentDescription = "Decrease")
                }
                Text(text = value, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(horizontal = 8.dp))
                IconButton(onClick = onPlusClick) {
                    Icon(painterResource(R.drawable.ic_plus), contentDescription = "Increase")
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DelegateSpinner(currentDelegate: Int, onDelegateSelected: (Int) -> Unit) {
        val options = listOf("CPU", "GPU")
        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            TextField(
                readOnly = true,
                value = options.getOrElse(currentDelegate) { "CPU" },
                onValueChange = {},
                label = { Text("Delegate") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEachIndexed { index, selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            onDelegateSelected(index)
                            expanded = false
                        }
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ModelSpinner(currentModel: Int, onModelSelected: (Int) -> Unit) {
        val options = listOf("Full", "Lite", "Heavy")
        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            TextField(
                readOnly = true,
                value = options.getOrElse(currentModel) { "Full" },
                onValueChange = {},
                label = { Text("Model") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEachIndexed { index, selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            onModelSelected(index)
                            expanded = false
                        }
                    )
                }
            }
        }
    }

    @Composable
    private fun InfoRow(label: String, value: String) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = MaterialTheme.typography.bodyMedium)
            Text(text = value, style = MaterialTheme.typography.bodyLarge)
        }
    }
}