package mx.android.storichallenge.ui.singup

import android.annotation.SuppressLint
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import mx.android.storichallenge.R
import mx.android.storichallenge.core.ui.empty
import mx.android.storichallenge.data.datasource.exception.AuthException
import mx.android.storichallenge.ui.composable.EmailTextField
import mx.android.storichallenge.ui.composable.LaunchSnackbar
import mx.android.storichallenge.ui.composable.NameTextField
import mx.android.storichallenge.ui.composable.PasswordTextField
import mx.android.storichallenge.ui.composable.ProgressButton
import mx.android.storichallenge.ui.composable.SnackbarBlue
import mx.android.storichallenge.ui.exception.AuthUiException
import mx.android.storichallenge.ui.home.ANY_PICTURE_IDENTIFICATION
import mx.android.storichallenge.ui.singin.UserDataSubmitUi
import mx.android.storichallenge.ui.theme.BlueGrey500
import mx.android.storichallenge.ui.theme.BlueGrey800
import mx.android.storichallenge.ui.theme.Space12
import mx.android.storichallenge.ui.theme.Space16
import mx.android.storichallenge.ui.theme.Space24
import mx.android.storichallenge.ui.theme.Space32
import mx.android.storichallenge.ui.theme.Space8
import mx.android.storichallenge.ui.theme.Space80
import mx.android.storichallenge.ui.theme.StoriChallengeTheme
import mx.android.storichallenge.ui.theme.White800

@Composable
fun SigUpScreen(
    modifier: Modifier = Modifier,
    pictureIdentification: String,
    signInUiState: SignUpUiState? = null,
    onProfileIdentification: () -> Unit,
    onSignUpButtonClick: (userDataSubmitUi: UserDataSubmitUi) -> Unit,
    onSingUpSuccess: () -> Unit,
    onBackPressedClick: () -> Unit
) {
    val isLoading = signInUiState is SignUpUiState.Loading
    val isSuccess = signInUiState is SignUpUiState.Success
    val errorException = (signInUiState as? SignUpUiState.Error)?.error

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(topBar = { SigUpTopAppBar(onBackPressedClick = onBackPressedClick) },
        snackbarHost = { SnackbarBlue(snackbarHostState) }) {
        SigUpContent(
            modifier = modifier.padding(paddingValues = it),
            pictureIdentification = pictureIdentification,
            isLoading = isLoading,
            errorException = errorException,
            onProfileIdentification = onProfileIdentification,
            onSignUpButtonClick = onSignUpButtonClick
        )
        AlertDialogSuccess(isSuccess, onSingUpSuccess)
        SnackbarError(errorException, snackbarHostState)
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SigUpTopAppBar(onBackPressedClick: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        title = {
            Text(
                text = stringResource(id = R.string.sing_up),
                color = White800
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPressedClick) {
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
fun SigUpContent(
    modifier: Modifier = Modifier,
    pictureIdentification: String,
    isLoading: Boolean,
    errorException: Throwable?,
    onProfileIdentification: () -> Unit,
    onSignUpButtonClick: (userDataSubmitUi: UserDataSubmitUi) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(all = Space32),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val firstNameError = (errorException as? AuthUiException.FirstNameException)?.error
        val lastNameError = (errorException as? AuthUiException.LastNameException)?.error
        val emailError = (errorException as? AuthUiException.EmailException)?.error
        val passwordError = (errorException as? AuthUiException.PasswordException)?.error
        val confirmPasswordError = (errorException as? AuthUiException.ConfirmPasswordException)?.error
        val differentPasswordError = (errorException as? AuthUiException.DifferentPasswordException)?.error

        var firstName by rememberSaveable { mutableStateOf(String.empty()) }
        var lastName by rememberSaveable { mutableStateOf(String.empty()) }
        var email by rememberSaveable { mutableStateOf(String.empty()) }
        var password by rememberSaveable { mutableStateOf(String.empty()) }
        var confirmPassword by rememberSaveable { mutableStateOf(String.empty()) }

        PictureIdentification(
            pictureIdentification = pictureIdentification,
            onProfileIdentification = onProfileIdentification
        )
        Spacer(modifier = Modifier.height(Space8))
        Text(
            text = stringResource(id = R.string.picture_identification),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(Space16))
        NameTextField(
            label = R.string.first_name,
            name = firstName,
            error = firstNameError,
            onValueChange = { firstName = it }
        )
        Spacer(modifier = Modifier.height(Space12))
        NameTextField(
            label = R.string.last_name,
            name = lastName,
            error = lastNameError,
            onValueChange = { lastName = it }
        )
        Spacer(modifier = Modifier.height(Space12))
        EmailTextField(
            email = email,
            error = emailError,
            onValueChange = { email = it }
        )
        Spacer(modifier = Modifier.height(Space12))
        PasswordTextField(
            label = R.string.password,
            password = password,
            error = passwordError,
            onValueChange = { password = it }
        )
        Spacer(modifier = Modifier.height(Space12))
        PasswordTextField(
            label = R.string.confirm_password,
            password = confirmPassword,
            error = confirmPasswordError ?: differentPasswordError,
            onValueChange = { confirmPassword = it }
        )
        Spacer(modifier = Modifier.height(Space24))
        ProgressButton(
            isLoading = isLoading,
            text = R.string.sing_up,
            onClick = {
                onSignUpButtonClick(
                    buildUserDataSubmitUi(
                        firstName,
                        lastName,
                        email,
                        password,
                        confirmPassword,
                        pictureIdentification
                    )
                )
            }
        )
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
private fun PictureIdentification(modifier: Modifier = Modifier, pictureIdentification: String, onProfileIdentification: () -> Unit) {
    if (pictureIdentification.isNotEmpty()) {
        AsyncImage(
            modifier = modifier
                .size(size = Space80),
            model = pictureIdentification,
            contentDescription = String.empty()
        )
    } else {
        Icon(
            modifier = modifier
                .size(size = Space80)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = true),
                    onClick = onProfileIdentification
                ),
            imageVector = Icons.Filled.AccountBox,
            tint = BlueGrey800,
            contentDescription = String.empty()
        )
    }
}

private fun buildUserDataSubmitUi(
    firstName: String,
    lastName: String,
    email: String,
    password: String,
    confirmPassword: String,
    pictureIdentification: String
) =
    UserDataSubmitUi(
        fistName = firstName,
        lastName = lastName, email,
        password = password,
        confirmPassword = confirmPassword,
        pictureIdentification = pictureIdentification,
    )

@Composable
private fun AlertDialogSuccess(isSuccess: Boolean, onSingUpSuccess: () -> Unit) {
    if (isSuccess) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text(stringResource(id = R.string.success_sign_up)) },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = onSingUpSuccess) {
                    Text(stringResource(id = R.string.accept))
                }
            })
    }
}

@Composable
private fun SnackbarError(errorException: Throwable?, snackbarHostState: SnackbarHostState) {
    if (errorException != null && errorException !is AuthUiException) {
        LaunchSnackbar(snackbarHostState = snackbarHostState, message = getMessageError(errorException))
    }
}

@Composable
private fun getMessageError(errorException: Throwable) = when (errorException) {
    is AuthException.SignUpException -> stringResource(id = R.string.error_sign_in)
    is AuthException.UserAlreadyExistException -> stringResource(id = R.string.error_user_already_exit)
    else -> errorException.message.orEmpty()
}

@Preview(showBackground = true)
@Composable
fun SingUpScreenPreview() {
    StoriChallengeTheme {
        SigUpScreen(
            pictureIdentification = String.empty(),
            onProfileIdentification = {},
            onSignUpButtonClick = {},
            onSingUpSuccess = {},
            onBackPressedClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SingUpScreenPictureIdentificationPreview() {
    StoriChallengeTheme {
        SigUpScreen(
            pictureIdentification = ANY_PICTURE_IDENTIFICATION,
            onProfileIdentification = {},
            onSignUpButtonClick = {},
            onSingUpSuccess = {},
            onBackPressedClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SingUpScreenUiStateLoadingPreview() {
    StoriChallengeTheme {
        SigUpScreen(
            pictureIdentification = String.empty(),
            signInUiState = SignUpUiState.Loading,
            onProfileIdentification = {},
            onSignUpButtonClick = {},
            onSingUpSuccess = {},
            onBackPressedClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SingUpScreenUiStateSuccessPreview() {
    StoriChallengeTheme {
        SigUpScreen(
            pictureIdentification = String.empty(),
            signInUiState = SignUpUiState.Success,
            onProfileIdentification = {},
            onSignUpButtonClick = {},
            onSingUpSuccess = {},
            onBackPressedClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SingUpScreenFirstNameUiStateErrorPreview() {
    StoriChallengeTheme {
        SigUpScreen(
            pictureIdentification = String.empty(),
            signInUiState = SignUpUiState.Error(AuthUiException.FirstNameException),
            onProfileIdentification = {},
            onSignUpButtonClick = {},
            onSingUpSuccess = {},
            onBackPressedClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SingUpScreenLastNameUiStateErrorPreview() {
    StoriChallengeTheme {
        SigUpScreen(
            pictureIdentification = String.empty(),
            signInUiState = SignUpUiState.Error(AuthUiException.LastNameException),
            onProfileIdentification = {},
            onSignUpButtonClick = {},
            onSingUpSuccess = {},
            onBackPressedClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SingUpScreenEmailUiStateErrorPreview() {
    StoriChallengeTheme {
        SigUpScreen(
            pictureIdentification = String.empty(),
            signInUiState = SignUpUiState.Error(AuthUiException.EmailException),
            onProfileIdentification = {},
            onSignUpButtonClick = {},
            onSingUpSuccess = {},
            onBackPressedClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SingUpScreenPasswordUiStateErrorPreview() {
    StoriChallengeTheme {
        SigUpScreen(
            pictureIdentification = String.empty(),
            signInUiState = SignUpUiState.Error(AuthUiException.PasswordException),
            onProfileIdentification = {},
            onSignUpButtonClick = {},
            onSingUpSuccess = {},
            onBackPressedClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SingUpScreenConfirmPasswordUiStateErrorPreview() {
    StoriChallengeTheme {
        SigUpScreen(
            pictureIdentification = String.empty(),
            signInUiState = SignUpUiState.Error(AuthUiException.ConfirmPasswordException),
            onProfileIdentification = {},
            onSignUpButtonClick = {},
            onSingUpSuccess = {},
            onBackPressedClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SingUpScreenDifferentPasswordUiStateErrorPreview() {
    StoriChallengeTheme {
        SigUpScreen(
            pictureIdentification = String.empty(),
            signInUiState = SignUpUiState.Error(AuthUiException.DifferentPasswordException),
            onProfileIdentification = {},
            onSignUpButtonClick = {},
            onSingUpSuccess = {},
            onBackPressedClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SingUpScreenUiStateErrorPreview() {
    StoriChallengeTheme {
        SigUpScreen(
            pictureIdentification = String.empty(),
            signInUiState = SignUpUiState.Error(AuthException.UserAlreadyExistException()),
            onProfileIdentification = {},
            onSignUpButtonClick = {},
            onSingUpSuccess = {},
            onBackPressedClick = {}
        )
    }
}
