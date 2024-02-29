package mx.android.storichallenge.ui.composable

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch
import mx.android.storichallenge.R
import mx.android.storichallenge.ui.theme.BlueGrey500

@Composable
fun SnackbarBlue(snackbarHostState: SnackbarHostState) {
    SnackbarHost(hostState = snackbarHostState) {
        Snackbar(
            snackbarData = it,
            containerColor = BlueGrey500,
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LaunchSnackbar(snackbarHostState: SnackbarHostState, @StringRes error: Int = R.string.error_get_data) {
    val scope = rememberCoroutineScope()
    val message = stringResource(id = error)
    scope.launch {
        snackbarHostState.showSnackbar(message = message)
    }
}

