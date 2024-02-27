package mx.android.storichallenge.ui.home

import mx.android.storichallenge.domain.model.UserData

data class UserDataUi(
    val fistName: String,
    val lastName: String,
    val email: String
)

fun UserData.toUserDataUi() = UserDataUi(
    fistName = fistName,
    lastName = lastName,
    email = email
)
