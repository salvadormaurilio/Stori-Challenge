package mx.android.storichallenge.ui.exception

import mx.android.storichallenge.core.ui.isValidEmail
import mx.android.storichallenge.core.ui.isValidPassword
import mx.android.storichallenge.ui.exception.AuthUiException.ConfirmPasswordException
import mx.android.storichallenge.ui.exception.AuthUiException.DifferentPasswordException
import mx.android.storichallenge.ui.exception.AuthUiException.EmailException
import mx.android.storichallenge.ui.exception.AuthUiException.FirstNameException
import mx.android.storichallenge.ui.exception.AuthUiException.LastNameException
import mx.android.storichallenge.ui.exception.AuthUiException.NoValidationNeededException
import mx.android.storichallenge.ui.exception.AuthUiException.PasswordException
import mx.android.storichallenge.ui.singin.UserDataSubmitUi
import javax.inject.Inject

class AuthExceptionHandler @Inject constructor() {

    fun areInvalidSingUpCredentials(userDataSubmitUi: UserDataSubmitUi) = userDataSubmitUi.run {
        when {
            fistName.isBlank() || fistName.length < MIN_CHARACTERS_NAMES -> Pair(true, FirstNameException)
            lastName.isBlank() || lastName.length < MIN_CHARACTERS_NAMES -> Pair(true, LastNameException)
            !email.isValidEmail() -> Pair(true, EmailException)
            !password.isValidPassword() -> Pair(true, PasswordException)
            !confirmPassword.isValidPassword() -> Pair(true, ConfirmPasswordException)
            password != confirmPassword -> Pair(true, DifferentPasswordException)
            else -> Pair(false, NoValidationNeededException)
        }
    }

    fun areInvalidSingInCredentials(email: String, password: String) = when {
        !email.isValidEmail() -> Pair(true, EmailException)
        !password.isValidPassword() -> Pair(true, PasswordException)
        else -> Pair(false, NoValidationNeededException)
    }


    companion object {
        private const val MIN_CHARACTERS_NAMES = 4
    }
}