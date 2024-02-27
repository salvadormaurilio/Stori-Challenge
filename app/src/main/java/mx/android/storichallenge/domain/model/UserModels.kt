package mx.android.storichallenge.domain.model

data class UserDataSubmit(
        val fistName: String,
        val lastName: String,
        val email: String,
        val password: String)

data class UserDataResponse(
        val fistName: String,
        val lastName: String,
        val email: String)

fun UserDataSubmit.toUserDataMap() = mapOf(
        FIRST_NAME to fistName,
        LAST_NAME to lastName,
        EMAIL_NAME to email)

fun Result<Map<String, Any>>.toResultUserResponseData() = map { it.toUserResponseData() }

private fun Map<String, Any>.toUserResponseData() = UserDataResponse(
        fistName = this[FIRST_NAME].toString(),
        lastName = this[LAST_NAME].toString(),
        email =  this[EMAIL_NAME].toString())

private const val FIRST_NAME = "first_name"
private const val LAST_NAME = "first_name"
private const val EMAIL_NAME = "email_name"
