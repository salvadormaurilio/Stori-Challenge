package mx.android.storichallenge.ui.movement

sealed class MovementDetailUiState {
    data object Loading : MovementDetailUiState()
    data class Success(val movementDetailUi: MovementDetailUi) : MovementDetailUiState()
    data class Error(val error: Throwable) : MovementDetailUiState()
}
