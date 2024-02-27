package mx.android.storichallenge.ui.movement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.android.storichallenge.core.coroutines.CoroutinesDispatchers
import mx.android.storichallenge.domain.GetMovementDetailUseCase
import mx.android.storichallenge.domain.model.MovementDetail
import javax.inject.Inject

class MovementDetailViewModel @Inject constructor(
    private val getMovementDetailUseCase: GetMovementDetailUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val _movementDetailUiState = MutableStateFlow<MovementDetailUiState?>(null)

    val movementDetailUiState: StateFlow<MovementDetailUiState?>
        get() = _movementDetailUiState

    fun getMovementDetail(movementId: String) = viewModelScope.launch(coroutinesDispatchers.io) {
        emitUserDataUiState(MovementDetailUiState.Loading)

        getMovementDetailUseCase.getMovementDetail(movementId).collect {
            getMovementDetailSuccess(it)
            getMovementDetailError(it)
        }
    }

    private fun getMovementDetailSuccess(result: Result<MovementDetail>) = result.onSuccess {
        emitUserDataUiState(MovementDetailUiState.Success(it.toMovementDetailUi()))
    }

    private fun getMovementDetailError(result: Result<MovementDetail>) = result.onFailure {
        it.printStackTrace()
        emitUserDataUiState(MovementDetailUiState.Error(it))
    }

    private fun emitUserDataUiState(signUpUiState: MovementDetailUiState) {
        _movementDetailUiState.value = signUpUiState
    }

}
