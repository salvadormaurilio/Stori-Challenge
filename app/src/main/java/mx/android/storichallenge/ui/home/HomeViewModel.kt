package mx.android.storichallenge.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.android.storichallenge.core.coroutines.CoroutinesDispatchers
import mx.android.storichallenge.domain.GetUserDataUseCase
import mx.android.storichallenge.domain.model.UserData
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val _userDataUiState = MutableStateFlow<UserDataUiState?>(null)

    val userDataUiState: StateFlow<UserDataUiState?>
        get() = _userDataUiState

    private val _navigateToMovementDetail = MutableSharedFlow<String>()

    val navigateToMovementDetail: SharedFlow<String>
        get() = _navigateToMovementDetail

    fun getUserData() = viewModelScope.launch(coroutinesDispatchers.io) {
        emitUserDataUiState(UserDataUiState.Loading)

        getUserDataUseCase.getUserData().collect {
            getUserDataSuccess(it)
            getUserDataError(it)
        }
    }

    private fun getUserDataSuccess(result: Result<UserData>) = result.onSuccess {
        emitUserDataUiState(UserDataUiState.Success(it.toUserDataUi()))
    }

    private fun getUserDataError(result: Result<UserData>) = result.onFailure {
        it.printStackTrace()
        emitUserDataUiState(UserDataUiState.Error(it))
    }

    private fun emitUserDataUiState(signUpUiState: UserDataUiState) {
        _userDataUiState.value = signUpUiState
    }

    fun navigateToSingUp(movementId: String) {
        _navigateToMovementDetail.tryEmit(movementId)
    }
}
