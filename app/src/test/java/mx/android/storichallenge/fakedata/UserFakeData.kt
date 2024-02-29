package mx.android.storichallenge.fakedata

import mx.android.storichallenge.data.datasource.model.UserDataResponse
import mx.android.storichallenge.domain.model.EMAIL_NAME
import mx.android.storichallenge.domain.model.FIRST_NAME
import mx.android.storichallenge.domain.model.LAST_NAME
import mx.android.storichallenge.domain.model.UserData
import mx.android.storichallenge.domain.model.UserDataSubmit
import mx.android.storichallenge.ui.home.UserDataUi
import mx.android.storichallenge.ui.singin.UserDataSubmitUi

const val ANY_USER_ID = "MaKNkzR0ySOFAd9PS4IFxnW5rpX2"
private const val ANY_FIRST_NAME = "Salvador"
private const val ANY_INVALID_FIRST_NAME = "Sal"
private const val ANY_LAST_NAME = "Maurilio"
private const val ANY_INVALID_LAST_NAME = "Mau"
const val ANY_USER_EMAIL = "salvador@google.mx"
const val ANY_INVALID_USER_EMAIL = "salvadorbuapap.mx"
const val ANY_PASSWORD = "Admin1234_1"
const val ANY_INVALID_PASSWORD = "Admi"
private const val ANY_OTHER_PASSWORD = "Admin1234_12"
const val ANY_PICTURE_IDENTIFICATION =
    "https://previews.123rf.com/images/alexutemov/alexutemov1605/alexutemov160500480/56958565-id-identidad-corporativa-medicina-identificador-identificaci%C3%B3n-del-m%C3%A9dico-y-la-tarjeta-de.jpg"
const val ANY_LOCAL_PICTURE_IDENTIFICATION =
    "content://images/alexutemov/alexutemov1605/alexutemov160500480/temps.jpg"

fun givenUserDataMap() = mapOf(
    FIRST_NAME to ANY_FIRST_NAME,
    LAST_NAME to ANY_LAST_NAME,
    EMAIL_NAME to ANY_USER_EMAIL
)

fun givenUserDataSubmit() = UserDataSubmit(
    fistName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD,
    pictureIdentification = ANY_PICTURE_IDENTIFICATION,
    )

fun givenUserDataSubmitWithLocalPictureIdentification() = UserDataSubmit(
    fistName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD,
    pictureIdentification = ANY_LOCAL_PICTURE_IDENTIFICATION,
)

fun givenUserDataResponse() = UserDataResponse(
    firstName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    movements = givenMovementsResponseArray()
)

fun givenUserData() = UserData(
    firstName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    movements = givenMovementList()
)

fun givenUserDataSubmitUi() = UserDataSubmitUi(
    fistName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD,
    confirmPassword = ANY_PASSWORD,
    pictureIdentification = ANY_PICTURE_IDENTIFICATION,
)

fun givenUserDataSubmitUiWithInvalidFirstName() = UserDataSubmitUi(
    fistName = ANY_INVALID_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD,
    confirmPassword = ANY_PASSWORD,
    pictureIdentification = ANY_PICTURE_IDENTIFICATION,
)

fun givenUserDataSubmitUiWithInvalidLastName() = UserDataSubmitUi(
    fistName = ANY_FIRST_NAME,
    lastName = ANY_INVALID_LAST_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD,
    confirmPassword = ANY_PASSWORD,
    pictureIdentification = ANY_PICTURE_IDENTIFICATION,
)

fun givenUserDataSubmitUiWithInvalidEmail() = UserDataSubmitUi(
    fistName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_INVALID_USER_EMAIL,
    password = ANY_PASSWORD,
    confirmPassword = ANY_PASSWORD,
    pictureIdentification = ANY_PICTURE_IDENTIFICATION,
)

fun givenUserDataSubmitUiWithInvalidPassword() = UserDataSubmitUi(
    fistName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_INVALID_PASSWORD,
    confirmPassword = ANY_PASSWORD,
    pictureIdentification = ANY_PICTURE_IDENTIFICATION,
)

fun givenUserDataSubmitUiWithInvalidConfirmPassword() = UserDataSubmitUi(
    fistName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD,
    confirmPassword = ANY_INVALID_PASSWORD,
    pictureIdentification = ANY_PICTURE_IDENTIFICATION,
)

fun givenUserDataSubmitUiWithDifferentPasswords() = UserDataSubmitUi(
    fistName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD,
    confirmPassword = ANY_OTHER_PASSWORD,
    pictureIdentification = ANY_PICTURE_IDENTIFICATION,
)

fun givenUserDataUi() = UserDataUi(
    firstName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    movements = givenMovementUiList()
)
