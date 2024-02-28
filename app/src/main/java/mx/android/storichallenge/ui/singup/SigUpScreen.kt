package mx.android.storichallenge.ui.singup

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
import mx.android.storichallenge.ui.composable.NameTextField
import mx.android.storichallenge.ui.composable.PasswordTextField
import mx.android.storichallenge.ui.composable.ProgressButton
import mx.android.storichallenge.ui.theme.Space12
import mx.android.storichallenge.ui.theme.Space24
import mx.android.storichallenge.ui.theme.Space32
import mx.android.storichallenge.ui.theme.StoriChallengeTheme

@Composable
fun SigUpScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = Space32),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sing_up),
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(Space24))
        NameTextField(text = R.string.first_name)
        Spacer(modifier = Modifier.height(Space12))
        NameTextField(text = R.string.last_name)
        Spacer(modifier = Modifier.height(Space12))
        EmailTextField()
        Spacer(modifier = Modifier.height(Space12))
        PasswordTextField()
        Spacer(modifier = Modifier.height(Space12))
        PasswordTextField(text = R.string.confirm_password)
        Spacer(modifier = Modifier.height(Space24))
        ProgressButton(isLoading = false,
            text = R.string.sing_up)
    }
}

@Preview(showBackground = true)
@Composable
fun SingUpScreenPreview() {
    StoriChallengeTheme {
        SigUpScreen()
    }
}