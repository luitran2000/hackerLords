package com.example.hackerlordapp.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@OptIn(ExperimentalPermissionsApi::class, ExperimentalComposeUiApi::class)
class test : ComponentActivity() {
    private lateinit var cameraExecutor: Executor
    private var imageCapture: ImageCapture? = null
    private var imageCaptureOutputOptions: ImageCapture.OutputFileOptions? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CameraApp()
        }

        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    @Composable
    fun CameraApp() {
        val context = LocalContext.current
        val permissionState = rememberPermissionState(Manifest.permission.CAMERA)
        val isPermissionGranted = permissionState.hasPermission
        val imageCaptureOptions = ImageCapture.Builder().build()
        val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        if (isPermissionGranted) {
            val cameraProvider = cameraProviderFuture.get()

            LaunchedEffect(key1 = cameraProvider) {
                val preview = Preview.Builder().build()
                imageCapture = imageCaptureOptions

                try {
                    cameraProvider?.bindToLifecycle(
                        this@test,
                        cameraSelector,
                        preview,
                        imageCapture
                    )
                } catch (exc: Exception) {
                    Toast.makeText(context, "Camera binding failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (isPermissionGranted) {
                AndroidView(
                    factory = { context ->
                        PreviewView(context).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Text(
                    text = "Camera permission required",
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    color = Color.Red
                )
            }

            if (isPermissionGranted) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    modifier = Modifier
                        .size(96.dp)
                        .background(MaterialTheme.colorScheme.primary)
                        .clickable {
                            imageCaptureOutputOptions = ImageCapture.OutputFileOptions.Builder(
                                createImageFile(context)
                            ).build()
                            imageCapture?.takePicture(
                                imageCaptureOutputOptions!!,
                                cameraExecutor,
                                object : ImageCapture.OnImageSavedCallback {
                                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                                        Toast.makeText(
                                            context,
                                            "Photo saved: ${outputFileResults.savedUri}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                    override fun onError(exc: ImageCaptureException) {
                                        Toast.makeText(
                                            context,
                                            "Photo capture failed: ${exc.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            )
                        }
                )
            }
        }
    }

    private fun createImageFile(context: android.content.Context): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val storageDir: File? = context.getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir).apply {
            // Save a file: path for use with ACTION_VIEW intents
            absolutePath
        }
    }
}
