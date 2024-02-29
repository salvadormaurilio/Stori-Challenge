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
import mx.android.storichallenge.ui.theme.BlueGrey800
import mx.android.storichallenge.ui.theme.Space12
import mx.android.storichallenge.ui.theme.Space16
import mx.android.storichallenge.ui.theme.Space24
import mx.android.storichallenge.ui.theme.Space32
import mx.android.storichallenge.ui.theme.Space80
import mx.android.storichallenge.ui.theme.StoriChallengeTheme
import mx.android.storichallenge.ui.theme.White800

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SigUpScreen(modifier: Modifier = Modifier) {
    Scaffold(topBar = {
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
                        contentDescription = String.empty()
                    )
                }
            }
        )
    }) {
        SigUpContent(modifier = modifier.padding(paddingValues = it))
    }
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
        Icon(
            modifier = Modifier
                .size(Space80)
                .clip(RoundedCornerShape(Space80))
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = true)
                ) {},
            imageVector = Icons.Filled.AccountCircle,
            tint = BlueGrey800,
            contentDescription = String.empty()
        )
        Text(
            text = stringResource(id = R.string.profile_picture),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(Space16))
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
        ProgressButton(
            isLoading = false,
            text = R.string.sing_up
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