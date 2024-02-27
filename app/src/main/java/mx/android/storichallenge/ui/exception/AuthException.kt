package mx.android.storichallenge.ui.exception

sealed class AuthUiException : Exception() {
    data object FirstNameException : AuthUiException()
    data object LastNameException : AuthUiException()
    data object EmailException : AuthUiException()
    data object PasswordException : AuthUiException()
    data object ConfirmPasswordException : AuthUiException()
    data object DifferentPasswordException : AuthUiException()
    data object NoValidationNeededException : AuthUiException()
}