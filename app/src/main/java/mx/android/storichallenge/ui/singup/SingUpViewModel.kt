package mx.android.storichallenge.ui.singup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.android.storichallenge.core.coroutines.CoroutinesDispatchers
import mx.android.storichallenge.domain.SignUpUseCase
import mx.android.storichallenge.ui.exception.AuthExceptionHandler
import mx.android.storichallenge.ui.singin.UserDataSubmitUi
import mx.android.storichallenge.ui.singin.toUserDataSubmit
import javax.inject.Inject

@HiltViewModel
class SingUpViewModel @Inject constructor(
    private val authExceptionHandler: AuthExceptionHandler,
    private val signUpUseCase: SignUpUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val _signUpUiState = MutableStateFlow<SignUpUiState?>(null)

    val signUpUiState: StateFlow<SignUpUiState?>
        get() = _signUpUiState

    fun singUp(userDataSubmitUi: UserDataSubmitUi) = viewModelScope.launch(coroutinesDispatchers.io) {
        emitSignInUiState(SignUpUiState.Loading)

        val (areInvalidCredentials, exception) = authExceptionHandler.areInvalidSingUpCredentials(userDataSubmitUi)
        if (areInvalidCredentials) return@launch emitSignInUiState(SignUpUiState.Error(exception))

        signUpUseCase.signUp(userDataSubmitUi.toUserDataSubmit()).collect {
            signUpSuccess(it)
            signUpError(it)
        }
    }

    private fun signUpSuccess(result: Result<String>) = result.onSuccess {
        emitSignInUiState(SignUpUiState.Success)
    }

    private fun signUpError(result: Result<String>) = result.onFailure {
        it.printStackTrace()
        emitSignInUiState(SignUpUiState.Error(it))
    }

    private fun emitSignInUiState(signUpUiState: SignUpUiState) {
        _signUpUiState.value = signUpUiState
    }
}
