package mx.android.storichallenge.ui.exception

import mx.android.storichallenge.core.ui.isValidEmail
import mx.android.storichallenge.core.ui.isValidPassword
import mx.android.storichallenge.ui.singin.UserDataSubmitUi
import javax.inject.Inject

class AuthExceptionHandler @Inject constructor() {

    fun areInvalidSingUpCredentials(userDataSubmitUi: UserDataSubmitUi) = userDataSubmitUi.run {
        when {
            fistName.isBlank() || fistName.length < MIN_CHARACTERS_NAMES -> Pair(true, AuthUiException.FirstNameException)
            lastName.isBlank() || lastName.length < MIN_CHARACTERS_NAMES -> Pair(true, AuthUiException.LastNameException)
            !email.isValidEmail() -> Pair(true, AuthUiException.EmailException)
            !password.isValidPassword() -> Pair(true, AuthUiException.PasswordException)
            !confirmPassword.isValidPassword() -> Pair(true, AuthUiException.ConfirmPasswordException)
            password != confirmPassword -> Pair(true, AuthUiException.DifferentPasswordException)
            else -> Pair(false, AuthUiException.NoValidationNeededException)
        }
    }

    fun areInvalidSingInCredentials(email: String, password: String) = when {
        !email.isValidEmail() -> Pair(true, AuthUiException.EmailException)
        !password.isValidPassword() -> Pair(true, AuthUiException.PasswordException)
        else -> Pair(false, AuthUiException.NoValidationNeededException)
    }


    companion object {
        private const val MIN_CHARACTERS_NAMES = 4
    }
}
