@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.postvision.camerax

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.video.FileOutputOptions
import androidx.camera.video.Recording
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.video.AudioConfig
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.postvision.ui.theme.PostVisionTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.File
import androidx.lifecycle.ViewModel // Necessário para compilar a dummy
import androidx.lifecycle.viewModelScope // Se você for usar

// =========================================================================
// INTERFACES E CLASSES DE ABSTRAÇÃO (PARA CORRIGIR O PREVIEW)
// =========================================================================

/**
 * Interface para abstrair a criação do LifecycleCameraController.
 * O Preview usará uma implementação que retorna null (Mock),
 * e a Activity usará a implementação Real.
 */
interface CameraControllerFactory {
    @Composable
    fun create(context: Context): LifecycleCameraController?
}

/**
 * Implementação real usada no Runtime da Activity.
 */
class RealCameraControllerFactory(private val applicationContext: Context) : CameraControllerFactory {
    @Composable
    override fun create(context: Context): LifecycleCameraController {
        return remember {
            LifecycleCameraController(applicationContext).apply {
                setEnabledUseCases(
                    CameraController.IMAGE_CAPTURE or
                            CameraController.VIDEO_CAPTURE
                )
            }
        }
    }
}

/**
 * Implementação mockada para o Preview. Sempre retorna null para evitar a falha
 * do CameraX no ambiente de design.
 */
class MockCameraControllerFactory : CameraControllerFactory {
    @Composable
    override fun create(context: Context): LifecycleCameraController? {
        return null // Retorna null no Preview
    }
}

// =========================================================================
// DUMMY CLASSES E COMPOSE AUXILIARES (Para compilar o código principal)
// =========================================================================

// Classe DUMMY ViewModel (remova se já tiver uma)
class MainViewModel : ViewModel() {
    val bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    fun onTakePhoto(bitmap: Bitmap) {}
}

// Composable DUMMY CameraPreview (Seu CameraPreview real)
@Composable
fun CameraPreviewA(controller: LifecycleCameraController, modifier: Modifier) {
    // Conteúdo real da Câmera (Viewfinder)
    Box(modifier = modifier.background(Color.DarkGray)) {
        Text("CameraPreview Real", Modifier.align(Alignment.Center), color = Color.White)
    }
}

// Composable DUMMY PhotoBottomSheetContent (Seu Bottom Sheet real)
@Composable
fun PhotoBottomSheetContentA(bitmaps: List<Bitmap>, modifier: Modifier) {
    Box(modifier = modifier.background(Color.White).padding(32.dp)) {
        Text("Bottom Sheet de Fotos", Modifier.align(Alignment.Center), color = Color.Black)
    }
}
// =========================================================================


class CameraMainActivity : ComponentActivity() {

    private var recording: Recording? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(
                this, CAMERAX_PERMISSIONS, 0
            )
        }
        setContent {
            PostVisionTheme {
                val viewModel = viewModel<MainViewModel>()

                WrapperCamera(
                    // Passando a implementação REAL do Factory
                    controllerFactory = RealCameraControllerFactory(applicationContext),
                    viewModel = viewModel,
                    takePhotoAction = ::takePhoto,
                    recordVideoAction = ::recordVideo
                )
            }
        }
    }

    private fun takePhoto(
        controller: LifecycleCameraController,
        onPhotoTaken: (Bitmap) -> Unit
    ) {
        if(!hasRequiredPermissions()) {
            return
        }

        controller.takePicture(
            ContextCompat.getMainExecutor(applicationContext),
            object : OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)

                    val matrix = Matrix().apply {
                        postRotate(image.imageInfo.rotationDegrees.toFloat())
                    }
                    val rotatedBitmap = Bitmap.createBitmap(
                        image.toBitmap(),
                        0,
                        0,
                        image.width,
                        image.height,
                        matrix,
                        true
                    )

                    onPhotoTaken(rotatedBitmap)
                }

                override fun onError(exception: ImageCaptureException) {
                    super.onError(exception)
                    Log.e("Camera", "Couldn't take photo: ", exception)
                }
            }
        )
    }

    @SuppressLint("MissingPermission")
    private fun recordVideo(controller: LifecycleCameraController) {
        if(recording != null) {
            recording?.stop()
            recording = null
            return
        }

        if(!hasRequiredPermissions()) {
            return
        }

        val outputFile = File(filesDir, "my-recording.mp4")
        recording = controller.startRecording(
            FileOutputOptions.Builder(outputFile).build(),
            AudioConfig.create(true),
            ContextCompat.getMainExecutor(applicationContext),
        ) { event ->
            when(event) {
                is VideoRecordEvent.Finalize -> {
                    if(event.hasError()) {
                        recording?.close()
                        recording = null

                        Toast.makeText(
                            applicationContext,
                            "Video capture failed",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Video capture succeeded",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun hasRequiredPermissions(): Boolean {
        return CAMERAX_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                applicationContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        private val CAMERAX_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
        )
    }
}


// =========================================================================
// COMPOSABLE CORRIGIDO (MOVIMENTADO PARA FORA DA ACTIVITY)
// =========================================================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WrapperCamera(
    // Recebe a Factory abstrata
    controllerFactory: CameraControllerFactory,
    viewModel: MainViewModel,
    takePhotoAction: (LifecycleCameraController, (Bitmap) -> Unit) -> Unit,
    recordVideoAction: (LifecycleCameraController) -> Unit,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()
    val bitmaps by viewModel.bitmaps.collectAsState()

    // Tenta criar o controller. Será NULL no Preview!
    val controller = controllerFactory.create(context)

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            PhotoBottomSheetContentA(
                bitmaps = bitmaps,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Condicional: Renderiza o CameraPreview apenas se o Controller for real (runtime)
            if (controller != null) {
                CameraPreviewA(
                    controller = controller,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                // Conteúdo de fallback para o Preview/Design
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "VISUALIZAÇÃO DE UI (CÂMERA DESABILITADA)",
                        color = Color.White
                    )
                }
            }

            // Os botões e a UI de controle funcionam em ambos os casos
            if (controller != null) {
                IconButton(
                    onClick = {
                        controller.cameraSelector =
                            if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                                CameraSelector.DEFAULT_FRONT_CAMERA
                            } else CameraSelector.DEFAULT_BACK_CAMERA
                    },
                    modifier = Modifier
                        .offset(50.dp, 50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Cameraswitch,
                        contentDescription = "Switch camera"
                    )
                }
            }


            Card(modifier = Modifier
                .fillMaxWidth()
                .height(78.dp),
                shape = RectangleShape,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {

            }

            Card(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
                shape = RectangleShape,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
                ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround,

            )
                {
                    IconButton(
                        onClick = {
                            scope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Photo,
                            contentDescription = "Open gallery"
                        )
                    }
                    IconButton(
                        // As ações só serão executadas se o controller não for null
                        onClick = {
                            if (controller != null) {
                                takePhotoAction(
                                    controller,
                                    viewModel::onTakePhoto
                                )
                            } else {
                                Log.d("Preview", "Ação de foto mockada")
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.PhotoCamera,
                            contentDescription = "Take photo"
                        )
                    }
                    IconButton(
                        onClick = {
                            if (controller != null) {
                                recordVideoAction(controller)
                            } else {
                                Log.d("Preview", "Ação de vídeo mockada")
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.FiberManualRecord,
                            contentDescription = "Record video"
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WrapperCameraPreview() {
    PostVisionTheme {
        WrapperCamera(
            // Passando a implementação MOCKADA para o Preview
            controllerFactory = MockCameraControllerFactory(),
            viewModel = viewModel<MainViewModel>(),
            takePhotoAction = { _, _ -> /* Mock */ },
            recordVideoAction = { _ -> /* Mock */ }
        )
    }
}