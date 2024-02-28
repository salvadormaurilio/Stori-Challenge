package mx.android.storichallenge.ui.singin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mx.android.storichallenge.R
import mx.android.storichallenge.ui.composable.EmailTextField
import mx.android.storichallenge.ui.composable.PasswordTextField
import mx.android.storichallenge.ui.theme.Blue500
import mx.android.storichallenge.ui.theme.Space24
import mx.android.storichallenge.ui.theme.Space32
import mx.android.storichallenge.ui.theme.Space48
import mx.android.storichallenge.ui.theme.StoriChallengeTheme
import mx.android.storichallenge.ui.theme.White800

@Composable
fun SigInScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = Space32),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sign_in),
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(Space24))
        EmailTextField()
        Spacer(modifier = Modifier.height(Space24))
        PasswordTextField()
        Spacer(modifier = Modifier.height(Space32))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(Space48),
            onClick = {}
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .height(Space32)
                    .width(Space32),
                color = White800
            )
        }
        Text(
            text = stringResource(id = R.string.sign_in),
            color = White800
        )
        Spacer(modifier = Modifier.height(Space32))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(Space48),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue500
            ),
            onClick = {}
        ) {
            Text(
                text = stringResource(id = R.string.sing_up),
                color = White800
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SingInScreenPreview() {
    StoriChallengeTheme {
        SigInScreen()
    }
}