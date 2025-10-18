package com.example.postvision.camerax

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.mediapipe.tasks.vision.core.RunningMode
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import com.example.postvision.R
import com.example.postvision.ui.theme.Raleway


class CameraScreen {

    /**
     * Para mostrar la pantalla de la cámara con detección de pose en vivo.
     * Solicita permiso de la cámara y muestra la vista previa junto con los resultados de la pose.
     *
     * @param viewModel El ViewModel que contiene la lógica de la aplicación y el estado.
     */

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CameraScreenContent(viewModel: MainViewModel) {
        var hasPermission by remember { mutableStateOf(false) }

        val permissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            hasPermission = isGranted
        }

        LaunchedEffect(Unit) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }

        var poseResults by remember { mutableStateOf<PoseLandmarkerHelper.ResultBundle?>(null) }
        val scaffoldState = rememberBottomSheetScaffoldState()

        /*BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContent = {
                InfoBottomSheet().InfoBottomSheetContent(viewModel = viewModel, inferenceTime = poseResults?.inferenceTime ?: 0L)
            },
            sheetPeekHeight = 50.dp
        ) {*/
            if (hasPermission) {

                Box(modifier = Modifier.fillMaxSize()) {
                    CameraPreview(
                        onPoseResult = { resultBundle ->
                            poseResults = resultBundle
                        },
                        viewModel = viewModel
                    )

                    poseResults?.let {
                        OverlayCanvas(
                            results = it.results.first(),
                            imageHeight = it.inputImageHeight,
                            imageWidth = it.inputImageWidth,
                            runningMode = RunningMode.LIVE_STREAM,
                            isFrontCamera = true
                        )
                    }

                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(78.dp),
                        shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 16.dp, bottom = 16.dp),
                                verticalAlignment = Alignment.Bottom
                        ){
                            Text(
                                "Câmera",
                                fontFamily = Raleway,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(107.dp)
                        .align(Alignment.BottomCenter),
                        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                            )
                        {
                            IconButton(
                                onClick = {},
                                modifier = Modifier
                                    .width(36.dp)
                                    .height(39.36.dp)

                            ){
                                Icon(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    painter = painterResource(R.drawable.icon_galery),
                                    contentDescription = "Icon from record"
                                )
                            }

                            IconButton(
                                onClick = {},
                                modifier = Modifier
                                    .width(68.dp)
                                    .height(68.dp)

                            ){
                                Icon(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    painter = painterResource(R.drawable.icon_record),
                                    contentDescription = "Icon from record"
                                )
                            }

                            IconButton(
                                onClick = {},
                                modifier = Modifier
                                    .width(39.dp)
                                    .height(39.dp)

                            ){
                                Icon(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    painter = painterResource(R.drawable.icon_switch_cam),
                                    contentDescription = "Icon from record"
                                )
                            }
                        }
                    }




                }

            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Camera permission is required.")
                }
            }
        }
    }

    /**
     * Muestra la vista previa de la cámara y analiza las imágenes en tiempo real para detectar poses.
     *
     * @param onPoseResult Callback que recibe los resultados de la detección de pose.
     * @param viewModel El ViewModel que contiene la configuración actual de detección de pose
     */
    @Composable
    fun CameraPreview(
        onPoseResult: (PoseLandmarkerHelper.ResultBundle) -> Unit,
        viewModel: MainViewModel
    ) {
        val context = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current
        val cameraExecutor = remember { Executors.newSingleThreadExecutor() }

        val poseLandmarkerHelper = remember(
            viewModel.currentMinPoseDetectionConfidence,
            viewModel.currentMinPoseTrackingConfidence,
            viewModel.currentMinPosePresenceConfidence,
            viewModel.currentModel,
            viewModel.currentDelegate
        ) {
            PoseLandmarkerHelper(
                context = context,
                runningMode = RunningMode.LIVE_STREAM,

                minPoseDetectionConfidence = viewModel.currentMinPoseDetectionConfidence,
                minPoseTrackingConfidence = viewModel.currentMinPoseTrackingConfidence,
                minPosePresenceConfidence = viewModel.currentMinPosePresenceConfidence,
                currentModel = viewModel.currentModel,
                currentDelegate = viewModel.currentDelegate,
                poseLandmarkerHelperListener = object : PoseLandmarkerHelper.LandmarkerListener {
                    override fun onError(error: String, errorCode: Int) {
                        Log.e("CameraPreview", "Error: $error")
                    }
                    override fun onResults(resultBundle: PoseLandmarkerHelper.ResultBundle) {
                        onPoseResult(resultBundle)
                    }
                }
            )
        }

        DisposableEffect(Unit) {
            onDispose {
                poseLandmarkerHelper.clearPoseLandmarker()
                cameraExecutor.shutdown()
            }
        }

        AndroidView(
            factory = { ctx ->
                val previewView = PreviewView(ctx).apply {
                    scaleType = PreviewView.ScaleType.FILL_START
                }
                val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
                cameraProviderFuture.addListener({
                    val cameraProvider = cameraProviderFuture.get()

                    val preview = Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }

                    val imageAnalyzer = ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                        .build()
                        .also {
                            it.setAnalyzer(cameraExecutor) { imageProxy ->
                                poseLandmarkerHelper.detectLiveStream(imageProxy, false)
                            }
                        }

                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            CameraSelector.DEFAULT_FRONT_CAMERA,
                            preview,
                            imageAnalyzer
                        )
                    } catch (exc: Exception) {
                        Log.e("CameraScreen", "Use case binding failed", exc)
                    }
                }, ContextCompat.getMainExecutor(ctx))
                previewView
            },
            modifier = Modifier.fillMaxSize()
        )
    }
/*}*/