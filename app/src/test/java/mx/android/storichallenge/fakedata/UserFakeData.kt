package mx.android.storichallenge.fakedata

import mx.android.storichallenge.domain.model.EMAIL_NAME
import mx.android.storichallenge.domain.model.FIRST_NAME
import mx.android.storichallenge.domain.model.LAST_NAME
import mx.android.storichallenge.domain.model.UserDataSubmit

const val ANY_USER_ID = "MaKNkzR0ySOFAd9PS4IFxnW5rpX2"
private const val ANY_FIRST_NAME = "Salvador"
private const val ANY_INVALID_FIRST_NAME = "Sal"
private const val ANY_LAST_LAST_NAME = "Maurilio"
private const val ANY_INVALID_NAME = "Sal"
const val ANY_USER_EMAIL = "salvador@google.mx"
const val ANY_INVALID_USER_EMAIL = "salvadorbuapap.mx"
const val ANY_PASSWORD = "Admin1234_1"
const val ANY_INVALID_PASSWORD = "Admi"
private const val ANY_OTHER_PASSWORD = "Admin1234_12"

fun givenUserDataMap() = mapOf(
    FIRST_NAME to ANY_FIRST_NAME,
    LAST_NAME to ANY_LAST_LAST_NAME,
    EMAIL_NAME to ANY_USER_EMAIL
)

fun givenUserDataSubmit() = UserDataSubmit(
    fistName = ANY_FIRST_NAME,
    lastName = ANY_LAST_LAST_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD
)
//
//fun givenUserData() = UserData(
//    name = ANY_NAME,
//    email = ANY_USER_EMAIL
//)
//
//fun givenUserCredentialsUi() = UserCredentialsUi(
//    name = ANY_NAME,
//    email = ANY_USER_EMAIL,
//    password = ANY_PASSWORD,
//    confirmPassword = ANY_PASSWORD
//)
//
//fun givenUserCredentialsUiWithInvalidName() = UserCredentialsUi(
//    name = ANY_INVALID_NAME,
//    email = ANY_USER_EMAIL,
//    password = ANY_PASSWORD,
//    confirmPassword = ANY_PASSWORD
//)
//
//fun givenUserCredentialsUiWithInvalidEmail() = UserCredentialsUi(
//    name = ANY_NAME,
//    email = ANY_INVALID_USER_EMAIL,
//    password = ANY_PASSWORD,
//    confirmPassword = ANY_PASSWORD
//)
//
//fun givenUserCredentialsUiWithInvalidPassword() = UserCredentialsUi(
//    name = ANY_NAME,
//    email = ANY_USER_EMAIL,
//    password = ANY_INVALID_PASSWORD,
//    confirmPassword = ANY_PASSWORD
//)
//
//fun givenUserCredentialsUiWithInvalidConfirmPassword() = UserCredentialsUi(
//    name = ANY_NAME,
//    email = ANY_USER_EMAIL,
//    password = ANY_PASSWORD,
//    confirmPassword = ANY_INVALID_PASSWORD
//)
//
//fun givenUserCredentialsUiWithDifferentPasswords() = UserCredentialsUi(
//    name = ANY_NAME,
//    email = ANY_USER_EMAIL,
//    password = ANY_PASSWORD,
//    confirmPassword = ANY_OTHER_PASSWORD
//)
