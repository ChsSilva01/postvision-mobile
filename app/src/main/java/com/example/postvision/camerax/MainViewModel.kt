package com.example.postvision.camerax

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.mediapipe.tasks.vision.core.RunningMode
import androidx.camera.core.CameraSelector // IMPORT NECESSÁRIO

/**
 * ViewModel para manejar la lógica de la aplicación, incluyendo la configuración del PoseLandmarker
 * y el estado de la pantalla de galería.
 */
class MainViewModel : ViewModel() {
    var currentModel by mutableIntStateOf(PoseLandmarkerHelper.MODEL_POSE_LANDMARKER_LITE)
        private set

    var currentDelegate by mutableIntStateOf(PoseLandmarkerHelper.DELEGATE_GPU)
        private set

    var currentMinPoseDetectionConfidence by mutableFloatStateOf(PoseLandmarkerHelper.DEFAULT_POSE_DETECTION_CONFIDENCE)
        private set

    var currentMinPoseTrackingConfidence by mutableFloatStateOf(PoseLandmarkerHelper.DEFAULT_POSE_TRACKING_CONFIDENCE)
        private set

    var currentMinPosePresenceConfidence by mutableFloatStateOf(PoseLandmarkerHelper.DEFAULT_POSE_PRESENCE_CONFIDENCE)
        private set

    // NOVO: Estado para armazenar o CameraSelector atual (inicia com câmera frontal)
    var cameraSelector: CameraSelector by mutableStateOf(CameraSelector.DEFAULT_FRONT_CAMERA)
        private set

    // NOVO: Função para alternar a câmera
    fun switchCamera() {
        cameraSelector = if (cameraSelector == CameraSelector.DEFAULT_FRONT_CAMERA) {
            CameraSelector.DEFAULT_BACK_CAMERA
        } else {
            CameraSelector.DEFAULT_FRONT_CAMERA
        }
    }

    // Funções de configuração dos parâmetros do Pose Landmarker
    fun setModel(model: Int) {
        currentModel = model
    }

    fun setDelegate(delegate: Int) {
        currentDelegate = delegate
    }

    fun setMinPoseDetectionConfidence(confidence: Float) {
        currentMinPoseDetectionConfidence = confidence.coerceIn(0.1f, 0.9f)
    }

    fun setMinPoseTrackingConfidence(confidence: Float) {
        currentMinPoseTrackingConfidence = confidence.coerceIn(0.1f, 0.9f)
    }

    fun setMinPosePresenceConfidence(confidence: Float) {
        currentMinPosePresenceConfidence = confidence.coerceIn(0.1f, 0.9f)
    }

    fun createPoseLandmarkerHelper(runningMode: RunningMode, context: Context): PoseLandmarkerHelper {
        return PoseLandmarkerHelper(
            context = context,
            runningMode = runningMode,
            minPoseDetectionConfidence = currentMinPoseDetectionConfidence,
            minPoseTrackingConfidence = currentMinPoseTrackingConfidence,
            minPosePresenceConfidence = currentMinPosePresenceConfidence,
            currentDelegate = currentDelegate,
            currentModel = currentModel
        )
    }
}