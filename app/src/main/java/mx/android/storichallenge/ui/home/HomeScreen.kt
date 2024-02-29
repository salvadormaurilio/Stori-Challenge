package mx.android.storichallenge.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import mx.android.storichallenge.R
import mx.android.storichallenge.data.datasource.exception.UserException
import mx.android.storichallenge.ui.composable.CircularProgressIndicatorFixMax
import mx.android.storichallenge.ui.composable.LabelMovement
import mx.android.storichallenge.ui.composable.LaunchSnackbar
import mx.android.storichallenge.ui.composable.SnackbarBlue
import mx.android.storichallenge.ui.theme.Space16
import mx.android.storichallenge.ui.theme.Space2
import mx.android.storichallenge.ui.theme.Space24
import mx.android.storichallenge.ui.theme.StoriChallengeTheme
import mx.android.storichallenge.ui.theme.White800

@Composable
fun HomeScreen(userDataUiState: UserDataUiState, onMovementClick: (id: String) -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(topBar = { HomeTopAppBar(userDataUiState) },
        snackbarHost = { SnackbarBlue(snackbarHostState = snackbarHostState) }) {
        HomeUiState(
            userDataUiState = userDataUiState,
            paddingValues = it,
            snackbarHostState = snackbarHostState,
            onMovementClick = onMovementClick
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeTopAppBar(userDataUiState: UserDataUiState) {
    val userDataUi = (userDataUiState as? UserDataUiState.Success)?.userDataUi
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        title = {
            userDataUi?.run {
                Column {
                    Text(
                        text = stringResource(id = R.string.user_name, firstName, lastName),
                        color = White800,
                        style = MaterialTheme.typography.titleMedium

                    )
                    Spacer(modifier = Modifier.height(Space2))
                    Text(
                        text = stringResource(id = R.string.user_email, email),
                        color = White800,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    )
}

@Composable
private fun HomeUiState(
    userDataUiState: UserDataUiState, paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
    onMovementClick: (id: String) -> Unit
) {
    when (userDataUiState) {
        is UserDataUiState.Loading -> {
            CircularProgressIndicatorFixMax()
        }

        is UserDataUiState.Success -> {
            HomeContent(
                modifier = Modifier.padding(paddingValues = paddingValues),
                userDataUi = userDataUiState.userDataUi,
                onMovementClick = onMovementClick
            )
        }

        is UserDataUiState.Error -> {
            LaunchSnackbar(snackbarHostState = snackbarHostState)
        }
    }
}

@Composable
fun HomeContent(modifier: Modifier = Modifier, userDataUi: UserDataUi, onMovementClick: (id: String) -> Unit) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = Space24,
                bottom = Space24
            )
    ) {
        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.movements),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(Space16))
        }
        items(
            items = userDataUi.movements,
            key = { it.id }
        ) {
            LabelMovement(
                movementUi = it,
                onMovementClick = onMovementClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenUiStateLoadingPreview() {
    StoriChallengeTheme {
        HomeScreen(
            userDataUiState = UserDataUiState.Loading,
            onMovementClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenUiStateSuccessPreview() {
    StoriChallengeTheme {
        HomeScreen(
            userDataUiState = UserDataUiState.Success(givenUserDataUi()),
            onMovementClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenUiStateErrorPreview() {
    StoriChallengeTheme {
        HomeScreen(
            userDataUiState = UserDataUiState.Error(UserException.GetUserDataException()),
            onMovementClick = {}
        )
    }
}
