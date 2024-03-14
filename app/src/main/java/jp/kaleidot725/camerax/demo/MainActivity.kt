package jp.kaleidot725.camerax.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.camerax.demo.ui.feature.HomeScreen
import jp.kaleidot725.camerax.demo.ui.feature.PreviewScreen
import jp.kaleidot725.camerax.demo.ui.model.Mode
import jp.kaleidot725.camerax.demo.ui.theme.CameraXDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var mode by remember { mutableStateOf(Mode.Home) }
            CameraXDemoTheme {
                when (mode) {
                    Mode.Home -> {
                        HomeScreen(
                            onChangeMode = { mode = it },
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                    Mode.Preview -> {
                        PreviewScreen(
                            onClose = { mode = Mode.Home },
                            modifier = Modifier.fillMaxSize().padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}
