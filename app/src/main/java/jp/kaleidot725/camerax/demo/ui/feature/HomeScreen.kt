package jp.kaleidot725.camerax.demo.ui.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.kaleidot725.camerax.demo.ui.model.Mode

@Composable
fun HomeScreen(
    onChangeMode: (Mode) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.weight(1.0f))
        Button(
            onClick = { onChangeMode(Mode.Preview) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Preview")
        }
        Spacer(modifier = Modifier.weight(1.0f))
    }
}