package mx.android.storichallenge.data.datasource.model

data class UserDataResponse(
    val firstName: String? = null,
    val lastName: String?= null,
    val email: String? = null,
    val movements: ArrayList<Map<String, String>>? = null
)
