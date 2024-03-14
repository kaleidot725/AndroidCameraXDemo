package jp.kaleidot725.camerax.demo.ui.feature

import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    Box(modifier = modifier) {
        if (multiplePermissionRequest.allPermissionsGranted) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                AndroidView(
                    factory = { context ->
                        return@AndroidView PreviewView(context)
                    },
                    update = { /* TODO */ },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Capture")
                }

                Button(
                    onClick = onClose,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Close")
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