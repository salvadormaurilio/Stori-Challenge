package mx.android.storichallenge.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import mx.android.storichallenge.R
import mx.android.storichallenge.ui.theme.BlueGrey500
import mx.android.storichallenge.ui.theme.Space32
import mx.android.storichallenge.ui.theme.Space48
import mx.android.storichallenge.ui.theme.StoriChallengeTheme
import mx.android.storichallenge.ui.theme.White800

@Composable
fun ProgressButton(isLoading: Boolean) {
    Button(
        colors = ButtonDefaults.buttonColors(disabledContainerColor = BlueGrey500),
        modifier = Modifier
            .fillMaxWidth()
            .height(Space48),
        enabled = !isLoading,
        onClick = {}
    ) {
        if (isLoading)
            CircularProgressIndicator(
                modifier = Modifier
                    .height(Space32)
                    .width(Space32),
                color = White800
            )
        else
            Text(
                text = stringResource(id = R.string.sign_in),
                color = White800
            )
    }
}

class IsLoadingParameterProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(false, true)
}

@Preview(device = Devices.NEXUS_5, showBackground = true)
@Composable
fun ProgressButtonPreview(@PreviewParameter(IsLoadingParameterProvider::class) isLoading: Boolean) {
    StoriChallengeTheme {
        ProgressButton(isLoading = isLoading)
    }
}