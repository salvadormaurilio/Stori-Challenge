package mx.android.storichallenge.ui.home

import mx.android.storichallenge.domain.model.UserData

data class UserDataUi(
    val firstName: String,
    val lastName: String,
    val email: String
)

fun UserData.toUserDataUi() = UserDataUi(
    firstName = firstName,
    lastName = lastName,
    email = email
)
