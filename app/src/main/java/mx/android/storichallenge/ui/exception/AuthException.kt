package mx.android.storichallenge.ui.exception

import androidx.annotation.StringRes
import mx.android.storichallenge.R

sealed class AuthUiException(@StringRes val error: Int? = null) : Exception() {
    data object FirstNameException : AuthUiException(R.string.error_first_name_invalid)
    data object LastNameException : AuthUiException(R.string.error_last_name_invalid)
    data object EmailException : AuthUiException(R.string.error_email_invalid)
    data object PasswordException : AuthUiException(R.string.error_password_invalid)
    data object ConfirmPasswordException : AuthUiException(R.string.error_password_invalid)
    data object DifferentPasswordException : AuthUiException(R.string.error_password_different)
    data object NoValidationNeededException : AuthUiException()
}
