package mx.android.storichallenge.ui.movement

import mx.android.storichallenge.domain.model.MovementDetail

sealed class MovementDetailUiState {
    data object Loading : MovementDetailUiState()
    data class Success(val movementDetail: MovementDetail) : MovementDetailUiState()
    data class Error(val error: Throwable) : MovementDetailUiState()
}
