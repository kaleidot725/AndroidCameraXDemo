package jp.kaleidot725.camerax.demo.ui.feature

import androidx.camera.core.CameraSelector
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PreviewScreen(
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val multiplePermissionRequest = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.RECORD_AUDIO
        )
    )
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val cameraController by remember(lifecycleOwner) {
        mutableStateOf(
            LifecycleCameraController(context).apply {
                bindToLifecycle(lifecycleOwner)
                cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            }
        )
    }

    Box(modifier = modifier) {
        if (multiplePermissionRequest.allPermissionsGranted) {
            Box(modifier = Modifier.fillMaxSize()) {
                AndroidView(
                    factory = { context ->
                        return@AndroidView PreviewView(context).apply {
                            controller = cameraController
                            scaleType = PreviewView.ScaleType.FIT_CENTER
                        }
                    },
                    update = { /* TODO */ },
                    modifier = Modifier.fillMaxSize()
                )

                IconButton(
                    onClick = onClose,
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            }

        } else {
            Button(
                onClick = { multiplePermissionRequest.launchMultiplePermissionRequest() },
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(text = "Request camera permission")
            }
        }
    }
}