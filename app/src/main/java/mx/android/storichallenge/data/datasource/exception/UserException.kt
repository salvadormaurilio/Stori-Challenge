package mx.android.storichallenge.data.datasource.exception

sealed class UserException(message: String) : Exception(message) {
    data class StoreUserException(override val message: String = "Invalid Credentials at Sing Up") : Exception(message)
    data class GetUserException(override val message: String = "Some error happened when get fat") : Exception(message)
}

