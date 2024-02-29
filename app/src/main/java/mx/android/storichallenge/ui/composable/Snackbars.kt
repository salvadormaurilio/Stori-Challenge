package mx.android.storichallenge.ui.composable

import android.annotation.SuppressLint
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch
import mx.android.storichallenge.R

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SnackbarBlue(snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()
    val message = stringResource(id = R.string.error_get_data)
    scope.launch {
        snackbarHostState.showSnackbar(message = message)
    }
}

