package mx.android.storichallenge.ui.home

sealed class UserDataUiState {
    data object Loading : UserDataUiState()
    data class Success(val userData: UserDataUi) : UserDataUiState()
    data class Error(val error: Throwable) : UserDataUiState()
}
