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

fun UserDataResponse?.toUserData() = UserData(
    firstName = this?.firstName.orEmpty(),
    lastName = this?.lastName.orEmpty(),
    email = this?.email.orEmpty(),
    movements = this?.movements.toMovements()
)

private fun ArrayList<Map<String, String>>?.toMovements() = this?.map { it.toMovement() }.orEmpty()

private const val FIRST_NAME = "first_name"
private const val LAST_NAME = "last_name"
private const val EMAIL_NAME = "email"
