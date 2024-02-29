package mx.android.storichallenge.ui.singup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mx.android.storichallenge.R
import mx.android.storichallenge.core.ui.empty
import mx.android.storichallenge.ui.composable.EmailTextField
import mx.android.storichallenge.ui.composable.NameTextField
import mx.android.storichallenge.ui.composable.PasswordTextField
import mx.android.storichallenge.ui.composable.ProgressButton
import mx.android.storichallenge.ui.theme.BlueGrey500
import mx.android.storichallenge.ui.theme.BlueGrey800
import mx.android.storichallenge.ui.theme.Space12
import mx.android.storichallenge.ui.theme.Space16
import mx.android.storichallenge.ui.theme.Space24
import mx.android.storichallenge.ui.theme.Space32
import mx.android.storichallenge.ui.theme.Space80
import mx.android.storichallenge.ui.theme.StoriChallengeTheme
import mx.android.storichallenge.ui.theme.White800

@Composable
fun SigUpScreen(modifier: Modifier = Modifier) {
    Scaffold(topBar = { SigUpTopAppBar() }) {
        SigUpContent(modifier = modifier.padding(paddingValues = it))
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SigUpTopAppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        title = {
            Text(
                text = stringResource(id = R.string.sing_up),
                color = White800
            )
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = BlueGrey500,
                    contentDescription = String.empty()
                )
            }
        }
    )
}

@Composable
fun SigUpContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(all = Space32),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var firstName by rememberSaveable { mutableStateOf(String.empty()) }
        var lastName by rememberSaveable { mutableStateOf(String.empty()) }
        var email by rememberSaveable { mutableStateOf(String.empty()) }
        var password by rememberSaveable { mutableStateOf(String.empty()) }
        var confirmPassword by rememberSaveable { mutableStateOf(String.empty()) }

        Icon(
            modifier = Modifier
                .size(size = Space80)
                .clip(shape = RoundedCornerShape(Space80))
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = true),
                    onClick = {}
                ),
            imageVector = Icons.Filled.AccountCircle,
            tint = BlueGrey800,
            contentDescription = String.empty()
        )
        Text(
            text = stringResource(id = R.string.profile_picture),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(Space16))
        NameTextField(
            label = R.string.first_name,
            name = firstName,
            onValueChange = { firstName = it }
        )
        Spacer(modifier = Modifier.height(Space12))
        NameTextField(
            label = R.string.last_name,
            name = lastName,
            onValueChange = { firstName = it }
        )
        Spacer(modifier = Modifier.height(Space12))
        EmailTextField(
            email = email,
            onValueChange = { email = it }
        )
        Spacer(modifier = Modifier.height(Space12))
        PasswordTextField(
            label = R.string.password,
            password = password,
            onValueChange = { password = it }
        )
        Spacer(modifier = Modifier.height(Space12))
        PasswordTextField(
            label = R.string.confirm_password,
            password = confirmPassword,
            onValueChange = { confirmPassword = it }
        )
        Spacer(modifier = Modifier.height(Space24))
        ProgressButton(
            isLoading = false,
            text = R.string.sing_up,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SingUpScreenPreview() {
    StoriChallengeTheme {
        SigUpScreen()
    }
}
