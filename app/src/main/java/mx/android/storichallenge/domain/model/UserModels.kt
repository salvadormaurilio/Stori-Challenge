package mx.android.storichallenge.domain.model

import mx.android.storichallenge.data.datasource.model.UserDataResponse

data class UserDataSubmit(
    val fistName: String,
    val lastName: String,
    val email: String,
    val password: String
)

data class UserData(
    val firstName: String,
    val lastName: String,
    val email: String,
    val movements: List<Movement>
)

fun UserDataSubmit.toUserDataMap() = mapOf(
    FIRST_NAME to fistName,
    LAST_NAME to lastName,
    EMAIL_NAME to email
)

fun Result<UserDataResponse?>.toResultUserData() = map { it.toUserData() }

private fun UserDataResponse?.toUserData() = UserData(
    firstName = this?.firstName.orEmpty(),
    lastName = this?.lastName.orEmpty(),
    email = this?.email.orEmpty(),
    movements = this?.movements.toMovementList()
)


const val FIRST_NAME = "first_name"
const val LAST_NAME = "last_name"
const val EMAIL_NAME = "email"
