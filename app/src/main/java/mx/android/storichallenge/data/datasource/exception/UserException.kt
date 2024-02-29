package mx.android.storichallenge.data.datasource.exception

sealed class UserException(message: String) : Exception(message) {
    data class StorePictureIdentificationException(override val message: String = "Some error happened when store picture identification") : Exception(message)
    data class StoreUserDataException(override val message: String = "Some error happened when store Use Data") : Exception(message)
    data class GetUserDataException(override val message: String = "Some error happened when get Use Data") : Exception(message)
}
