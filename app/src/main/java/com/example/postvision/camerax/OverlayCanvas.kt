package com.example.postvision.camerax

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarker
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarkerResult
import kotlin.math.min

/**
 * Dibuja los resultados de PoseLandmarker sobre un lienzo.
 *
 * @param results Resultados de PoseLandmarker a dibujar.
 * @param imageWidth Ancho de la imagen original.
 * @param imageHeight Alto de la imagen original.
 * @param runningMode Modo de ejecución actual (LIVE_STREAM o IMAGE).
 * @param isFrontCamera Si es true, invierte horizontalmente los puntos para el modo espejo.
 */
@Composable
fun OverlayCanvas(
    results: PoseLandmarkerResult?,
    imageWidth: Int,
    imageHeight: Int,
    runningMode: RunningMode,
    isFrontCamera: Boolean = false
) {
    val pointColor = Color.Yellow
    val lineColor = Color(0xFF9C27B0)

    if (results != null) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val scaleFactor = min(size.width / imageWidth, size.height / imageHeight)
            val scaledImageWidth = imageWidth * scaleFactor
            val scaledImageHeight = imageHeight * scaleFactor

            val offsetX: Float
            val offsetY: Float

            if (runningMode == RunningMode.LIVE_STREAM) {
                // Esta mierda se desfasa a la izquierda alachingada
                offsetX = (size.width - scaledImageWidth) / 2 + 150
                offsetY = (size.height - scaledImageHeight) / 2
            } else {
                // Para la galería
                offsetX = (size.width - scaledImageWidth) / 2
                offsetY = (size.height - scaledImageHeight) / 2
            }

            for (landmarkList in results.landmarks()) {
                // Dibuja las líneas de conexión
                PoseLandmarker.POSE_LANDMARKS.forEach {
                    val start = landmarkList[it!!.start()]
                    val end = landmarkList[it.end()]

                    val startX = transformX(start.x(), scaledImageWidth, offsetX, isFrontCamera, runningMode)
                    val startY = start.y() * scaledImageHeight + offsetY
                    val endX = transformX(end.x(), scaledImageWidth, offsetX, isFrontCamera, runningMode)
                    val endY = end.y() * scaledImageHeight + offsetY

                    drawLine(color = lineColor, start = Offset(startX, startY), end = Offset(endX, endY), strokeWidth = 8f)
                }

                // Dibuja los puntos
                for (lm in landmarkList) {
                    val pointX = transformX(lm.x(), scaledImageWidth, offsetX, isFrontCamera, runningMode)
                    val pointY = lm.y() * scaledImageHeight + offsetY

                    drawCircle(color = pointColor, radius = 12f, center = Offset(pointX, pointY))
                }
            }
        }
    }
}

/**
 * Ajusta la coordenada X. Para la cámara frontal en vivo, la invierte (efecto espejo).
 */
private fun transformX(
    normalizedX: Float,
    scaledImageWidth: Float,
    offsetX: Float,
    isFrontCamera: Boolean,
    runningMode: RunningMode
): Float {
    val x = normalizedX * scaledImageWidth + offsetX

    if (isFrontCamera && runningMode == RunningMode.LIVE_STREAM) {
        return scaledImageWidth - x + 2 * offsetX
    }

    return x
}