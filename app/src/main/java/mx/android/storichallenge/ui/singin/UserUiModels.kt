package mx.android.storichallenge.ui.singin

import mx.android.storichallenge.domain.model.UserDataSubmit

data class UserDataSubmitUi(
        val fistName: String,
        val lastName: String,
        val email: String,
        val password: String,
        val confirmPassword: String)

fun UserDataSubmitUi.toUserCredentials() = UserDataSubmit(
        fistName = fistName,
        lastName = lastName,
        email = email,
        password = password)
