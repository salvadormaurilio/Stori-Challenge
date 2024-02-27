package mx.android.storichallenge.domain.model

data class UserDataSubmit(
    val fistName: String,
    val lastName: String,
    val email: String,
    val password: String
)

data class UserData(
    val fistName: String,
    val lastName: String,
    val email: String,
    val movements: List<Movement>
)

fun UserDataSubmit.toUserDataMap() = mapOf(
    FIRST_NAME to fistName,
    LAST_NAME to lastName,
    EMAIL_NAME to email
)

fun Result<Map<String, Any>>.toResultUserData() = map { it.toUserData() }

fun Map<String, Any>.toUserData() = UserData(
    fistName = this[FIRST_NAME].toString(),
    lastName = this[LAST_NAME].toString(),
    email = this[EMAIL_NAME].toString(),
    movements = (this[MOVEMENTS] as? ArrayList<HashMap<String, String>>).toMovements(),
)

private fun ArrayList<HashMap<String, String>>?.toMovements(): List<Movement> = this?.map { it.toMovement() }.orEmpty()

private fun HashMap<String, String>.toMovement() = Movement(
    id = this["id"].orEmpty(),
    name = this["name"].orEmpty(),
    date = this["name"].orEmpty(),
    amount = this["name"].orEmpty())

private const val FIRST_NAME = "first_name"
private const val LAST_NAME = "last_name"
private const val EMAIL_NAME = "email"
private const val MOVEMENTS = "movements"
