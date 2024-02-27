package mx.android.storichallenge.ui.singin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.android.storichallenge.core.coroutines.CoroutinesDispatchers
import mx.android.storichallenge.domain.SignInUseCase
import mx.android.storichallenge.ui.exception.AuthExceptionHandler
import mx.android.storichallenge.ui.singin.SignInUiState.Error
import mx.android.storichallenge.ui.singin.SignInUiState.Loading
import javax.inject.Inject

class SingInViewModel @Inject constructor(
    private val authExceptionHandler: AuthExceptionHandler,
    private val signInUseCase: SignInUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val _signInUiState = MutableStateFlow<SignInUiState?>(null)

    val signInUiState: StateFlow<SignInUiState?>
        get() = _signInUiState

    fun signIn(email: String, password: String) = viewModelScope.launch(coroutinesDispatchers.io) {
        emitSignInUiState(Loading)

        val (areInvalidCredentials, exception) = authExceptionHandler.areInvalidSingInCredentials(email, password)
        if (areInvalidCredentials) return@launch emitSignInUiState(Error(exception))

        signInUseCase.signIn(email, password).collect {
            signInSuccess(it)
            signInError(it)
        }
    }

    private fun signInSuccess(result: Result<String>) = result.onSuccess {
        emitSignInUiState(SignInUiState.Success)
    }

    private fun signInError(result: Result<String>) = result.onFailure {
        it.printStackTrace()
        emitSignInUiState(Error(it))
    }

    private fun emitSignInUiState(signInUiState: SignInUiState) {
        _signInUiState.value = signInUiState
    }
}