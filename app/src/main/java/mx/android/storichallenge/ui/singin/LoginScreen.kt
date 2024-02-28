package mx.android.storichallenge.ui.singin

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mx.android.storichallenge.ui.theme.StoriChallengeTheme

@Composable
fun LoginScreen(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    StoriChallengeTheme {
        LoginScreen("Android")
    }
}