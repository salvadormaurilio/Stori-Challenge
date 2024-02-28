package mx.android.storichallenge.ui.singin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import mx.android.storichallenge.ui.theme.Space24
import mx.android.storichallenge.ui.theme.Space32
import mx.android.storichallenge.ui.theme.StoriChallengeTheme

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
    }
}


@Preview(showBackground = true)
@Composable
fun SingInScreenPreview() {
    StoriChallengeTheme {
        SigInScreen()
    }
}