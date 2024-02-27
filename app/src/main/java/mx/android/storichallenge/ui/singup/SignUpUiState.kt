package mx.android.storichallenge.ui.singup

sealed class SignUpUiState {
    data object Loading : SignUpUiState()
    data object Success : SignUpUiState()
    data class Error(val error: Throwable) : SignUpUiState()
}
